package org.linghuxiong.spring.batch.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.linghuxiong.spring.batch.admin.dao.SpringBatchDao;
import org.linghuxiong.spring.batch.admin.service.SpringBatchService;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
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
    public Page<JSONObject> loadBatchPageable(Pageable pageable,String jobName,String status) {
        return batchDao.loadBatchPageable(pageable,jobName,status);
    }

    @Override
    public void stopBatch(Long jobExecutionId) {
        try {
            jobOperator.stop(jobExecutionId);
        } catch (NoSuchJobExecutionException e) {
            e.printStackTrace();
        } catch (JobExecutionNotRunningException e) {
            e.printStackTrace();
        }
    }
}
