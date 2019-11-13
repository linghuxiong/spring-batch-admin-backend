package org.linghuxiong.spring.batch.admin.service;

import org.linghuxiong.spring.batch.admin.model.JobExecutionHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author linghuxiong
 * @date 2019/11/13 10:46 下午
 */
public interface JobExecutionHistoryService {

    Page<JobExecutionHistoryEntity> loadJobHistoryPageable(Pageable pageable, String jobName, String userName, Integer status, Long runId);
}
