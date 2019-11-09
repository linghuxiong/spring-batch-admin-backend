package org.linghuxiong.spring.batch.admin.service.impl;

import org.linghuxiong.spring.batch.admin.dao.TriggerDao;
import org.linghuxiong.spring.batch.admin.model.TriggerEntity;
import org.linghuxiong.spring.batch.admin.service.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linghuxiong
 * @date 2019/11/8 11:13 下午
 */
@Service
public class TriggerServiceImpl implements TriggerService {

    @Autowired
    TriggerDao triggerDao;

    @Override
    public TriggerEntity save(TriggerEntity triggerEntity) {
        return triggerDao.save(triggerEntity);
    }

    @Override
    public void delete(Long triggerId) {
        triggerDao.deleteById(triggerId);
    }

    @Override
    public TriggerEntity toggleStatus(Long triggerId, Integer status) {
        TriggerEntity entity = triggerDao.getOne(triggerId);
        entity.setStatus(status);
        triggerDao.save(entity);
        return entity;
    }

    @Override
    public Page<TriggerEntity> loadTriggerPageable(Pageable pageable, String triggerName, String triggerGroup, Integer triggerStatus) {
        Specification<TriggerEntity> queryCondition = new Specification<TriggerEntity>() {
            @Override
            public Predicate toPredicate(Root<TriggerEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (triggerName != null) {
                    predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%"+triggerName.toUpperCase()+"%"));
                }
                if (triggerGroup != null) {
                    predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("group")), "%"+triggerGroup.toUpperCase()+"%"));
                }
                if (triggerStatus != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("status"), triggerStatus));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        return triggerDao.findAll(queryCondition,pageable);
    }
}
