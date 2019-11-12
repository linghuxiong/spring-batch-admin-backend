package org.linghuxiong.spring.batch.admin.dao;

import org.linghuxiong.spring.batch.admin.model.JobEntity;
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
public interface JobDao extends JpaRepository<JobEntity,Long> {

    /**
     *
     * @param queryCondition
     * @param pageable
     * @return
     */
    Page<JobEntity> findAll(Specification<JobEntity> queryCondition, Pageable pageable);
}
