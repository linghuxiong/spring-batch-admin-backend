package org.linghuxiong.spring.batch.admin.service;

import org.linghuxiong.spring.batch.admin.model.JobEntity;
import org.linghuxiong.spring.batch.admin.model.TriggerEntity;
import org.quartz.SchedulerException;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author linghuxiong
 * @date 2019/11/8 4:34 下午
 */
public interface JobService {

    JobEntity save(JobEntity jobEntity);
    void delete(Long jobId);
    JobEntity toggleStatus(Long jobId,Integer status) throws SchedulerException;
    Page<JobEntity> loadJobPageable(Pageable pageable, String name,Integer type,Integer status,String triggerName,String springJobName);
    void launch(Long jobId) throws NoSuchJobException, JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException;
}
