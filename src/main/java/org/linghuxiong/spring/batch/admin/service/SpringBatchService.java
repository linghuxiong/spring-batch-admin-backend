package org.linghuxiong.spring.batch.admin.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobParametersNotFoundException;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author linghuxiong
 * @date 2019/11/5 10:53 下午
 */
public interface SpringBatchService {

    Page<JSONObject> loadBatchPageable(Pageable pageable,String jobName,String status);

    void stopBatch(Long jobExecutionId) throws NoSuchJobExecutionException, JobExecutionNotRunningException;
    void abandonBatch(Long jobExecutionId) throws NoSuchJobExecutionException, JobExecutionAlreadyRunningException;
    void restartBatch(Long jobExecutionId) throws JobParametersInvalidException, JobRestartException, JobInstanceAlreadyCompleteException, NoSuchJobExecutionException, NoSuchJobException;
    void startNextInstanceBatch(String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, JobParametersNotFoundException, NoSuchJobException;
}
