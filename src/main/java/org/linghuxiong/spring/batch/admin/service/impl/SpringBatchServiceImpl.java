package org.linghuxiong.spring.batch.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.linghuxiong.spring.batch.admin.dao.SpringBatchDao;
import org.linghuxiong.spring.batch.admin.service.SpringBatchService;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.*;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author linghuxiong
 * @date 2019/11/5 10:53 下午
 */
@Service
public class SpringBatchServiceImpl implements SpringBatchService {

    @Autowired
    SpringBatchDao batchDao;

    @Autowired
    JobOperator jobOperator;

    @Override
    public Page<JSONObject> loadBatchPageable(Pageable pageable, String jobName, String status) {
        return batchDao.loadBatchPageable(pageable, jobName, status);
    }

    @Override
    public void stopBatch(Long jobExecutionId) throws NoSuchJobExecutionException, JobExecutionNotRunningException {
        jobOperator.stop(jobExecutionId);
    }

    @Override
    public void abandonBatch(Long jobExecutionId) throws NoSuchJobExecutionException, JobExecutionAlreadyRunningException {
        jobOperator.abandon(jobExecutionId);
    }

    @Override
    public void restartBatch(Long jobExecutionId) throws JobParametersInvalidException, JobRestartException, JobInstanceAlreadyCompleteException, NoSuchJobExecutionException, NoSuchJobException {
        jobOperator.restart(jobExecutionId);
    }

    @Override
    public void startNextInstanceBatch(String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, JobParametersNotFoundException, NoSuchJobException {
        jobOperator.startNextInstance(jobName);
    }
}
