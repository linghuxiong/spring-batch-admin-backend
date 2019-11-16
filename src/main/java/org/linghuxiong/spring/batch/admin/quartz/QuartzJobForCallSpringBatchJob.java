package org.linghuxiong.spring.batch.admin.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    JobRegistry jobRegistry;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        try {
            jobLauncher.run(jobRegistry.getJob(context.getJobDetail().getJobDataMap().getString("SPRING_JOB_NAME")),new JobParameters());
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        } catch (NoSuchJobException e) {
            e.printStackTrace();
        }

        log.info("Execution Job Success.");
	}
}
