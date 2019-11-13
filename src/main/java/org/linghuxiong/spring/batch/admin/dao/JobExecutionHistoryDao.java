package org.linghuxiong.spring.batch.admin.dao;

import org.linghuxiong.spring.batch.admin.model.JobEntity;
import org.linghuxiong.spring.batch.admin.model.JobExecutionHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author linghuxiong
 * @date 2019/11/8 4:34 下午
 */
@Repository
public interface JobExecutionHistoryDao extends JpaRepository<JobExecutionHistoryEntity,Long> {

    /**
     *
     * @param queryCondition
     * @param pageable
     * @return
     */
    Page<JobExecutionHistoryEntity> findAll(Specification<JobExecutionHistoryEntity> queryCondition, Pageable pageable);
}
