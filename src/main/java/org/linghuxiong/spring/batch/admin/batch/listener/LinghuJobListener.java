package org.linghuxiong.spring.batch.admin.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.linghuxiong.spring.batch.admin.dao.JobExecutionHistoryDao;
import org.linghuxiong.spring.batch.admin.model.JobExecutionHistoryEntity;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author linghuxiong
 * @date 2019/11/16 11:56 下午
 */
@Component
@Slf4j
public class LinghuJobListener implements JobExecutionListener {

    @Autowired
    JobExecutionHistoryDao jobExecutionHistoryDao;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        JobExecutionHistoryEntity entity = new JobExecutionHistoryEntity();
        entity.setCreatedAt(jobExecution.getCreateTime());
        entity.setRunId(jobExecution.getId());
        entity.setStartAt(jobExecution.getStartTime());
        entity.setJobName(jobExecution.getJobInstance().getJobName());
        entity.setStatus(jobExecution.getStatus().name());
        entity.setUpdatedAt(new Date());
        jobExecutionHistoryDao.save(entity);
        log.info("############## BeforeJob ######################");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        JobExecutionHistoryEntity entity = jobExecutionHistoryDao.findByRunId(jobExecution.getId());
        if(entity == null){
            entity = new JobExecutionHistoryEntity();
            entity.setCreatedAt(jobExecution.getCreateTime());
            entity.setRunId(jobExecution.getId());
            entity.setStartAt(jobExecution.getStartTime());
            entity.setJobName(jobExecution.getJobInstance().getJobName());
        }
        entity.setEndAt(jobExecution.getEndTime());
        entity.setUpdatedAt(new Date());
        entity.setExitCode(jobExecution.getExitStatus().getExitCode());
        entity.setExitMessage(jobExecution.getExitStatus().getExitDescription());
        entity.setStatus(jobExecution.getStatus().name());
        jobExecutionHistoryDao.save(entity);
        log.info("############## afterJob ######################");
    }
}
