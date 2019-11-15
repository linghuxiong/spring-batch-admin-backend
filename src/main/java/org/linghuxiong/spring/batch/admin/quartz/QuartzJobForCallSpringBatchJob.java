package org.linghuxiong.spring.batch.admin.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;


/**
 * give a jms message to remote application
 * @author shawn.yang
 *
 */
@Component
@DisallowConcurrentExecution
@Slf4j
public class QuartzJobForCallSpringBatchJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("Execution Job Success.");
	}
}
