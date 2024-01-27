package kr.co.strato.wrp.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuartzHandler {

    private final Scheduler scheduler;

    public <T extends Job> void addJob(Class<? extends Job> job, String name, String desc, Map params, String cron) throws SchedulerException {
        JobDetail jobDetail = buildJobDetail(job, name, desc, params);
        Trigger trigger = buildCronTrigger(cron);
        if(scheduler.checkExists(jobDetail.getKey())) scheduler.deleteJob(jobDetail.getKey());
        scheduler.scheduleJob(jobDetail, trigger);
    }

    public <T extends Job> void deleteJob(String name) throws SchedulerException {
        JobKey jobKey = new JobKey(name);
        if(scheduler.checkExists(jobKey)) scheduler.deleteJob(jobKey);
    }

    public <T extends Job> JobDetail buildJobDetail(Class<? extends Job> job, String name, String desc, Map params){

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(params);

        return JobBuilder
                .newJob(job)
                .withIdentity(name)
                .withDescription(desc)
                .usingJobData(jobDataMap)
                .build();
    }

    private Trigger buildCronTrigger(String cronExp){
        return TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExp))
                .build();
    }
}
