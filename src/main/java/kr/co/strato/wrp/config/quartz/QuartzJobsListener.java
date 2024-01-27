package kr.co.strato.wrp.config.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QuartzJobsListener implements JobListener {


    @Override
    public String getName() {
        return this.getClass().getName();
    }

    /**
     * Job이 수행되기 전 상태
     *   - TriggerListener.vetoJobExecution == false
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        JobKey jobKey = context.getJobDetail().getKey();
        log.info("jobToBeExecuted :: jobKey : {}", jobKey);
    }

    /**
     * Job이 중단된 상태
     *   - TriggerListener.vetoJobExecution == true
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        JobKey jobKey = context.getJobDetail().getKey();
        log.info("jobExecutionVetoed :: jobKey : {}", jobKey);
    }

    /**
     * Job 수행이 완료된 상태
     */
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        JobKey jobKey = context.getJobDetail().getKey();
        log.info("jobWasExecuted :: jobKey : {}", jobKey);
    }


}
