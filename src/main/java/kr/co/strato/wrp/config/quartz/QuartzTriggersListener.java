package kr.co.strato.wrp.config.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QuartzTriggersListener implements TriggerListener {


    @Override
    public String getName() {
        return this.getClass().getName();
    }

    /**
     * Trigger가 실행된 상태
     * 리스너 중에서 가장 먼저 실행됨
     */
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        JobKey jobKey = trigger.getJobKey();
        log.info("triggerFired at {} :: jobKey : {}", trigger.getStartTime(), jobKey);
    }

    /**
     * Trigger 중단 여부를 확인하는 메소드
     * Job을 수행하기 전 상태
     *
     * 반환값이 false인 경우, Job 수행
     * 반환값이 true인 경우, Job을 수행하지않고 'QuartzJobsListener.jobExecutionVetoed'로 넘어감
     */
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return false;
    }

    /**
     * Trigger가 중단된 상태
     */
    @Override
    public void triggerMisfired(Trigger trigger) {
        JobKey jobKey = trigger.getJobKey();
        log.info("triggerMisfired at {} :: jobKey : {}", trigger.getStartTime(), jobKey);
    }

    /**
     * Trigger가 완료된 상태
     */
    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        JobKey jobKey = trigger.getJobKey();
        log.info("triggerComplete at {} :: jobKey : {}", trigger.getStartTime(), jobKey);
    }
}
