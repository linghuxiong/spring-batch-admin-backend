package org.linghuxiong.spring.batch.admin.dao;

import org.linghuxiong.spring.batch.admin.model.QuartzJobFireHistoryEntity;
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
public interface QuartzJobFireHistoryDao extends JpaRepository<QuartzJobFireHistoryEntity,String> {

    /**
     *
     * @param queryCondition
     * @param pageable
     * @return
     */
    Page<QuartzJobFireHistoryEntity> findAll(Specification<QuartzJobFireHistoryEntity> queryCondition, Pageable pageable);

}
