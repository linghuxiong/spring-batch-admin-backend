package org.linghuxiong.spring.batch.admin.dao;

import org.linghuxiong.spring.batch.admin.model.TriggerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author linghuxiong
 * @date 2019/11/8 4:36 下午
 */
@Repository
public interface TriggerDao extends JpaRepository<TriggerEntity,Long> {

    //long count(Specification<TriggerEntity> queryCondition);

    /**
     *
     * @param queryCondition
     * @param pageable
     * @return
     */
    Page<TriggerEntity> findAll(Specification<TriggerEntity> queryCondition,Pageable pageable);

    TriggerEntity getByNameEquals(String name);

}