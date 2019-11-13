package org.linghuxiong.spring.batch.admin.service.impl;

import org.linghuxiong.spring.batch.admin.dao.JobExecutionHistoryDao;
import org.linghuxiong.spring.batch.admin.model.JobExecutionHistoryEntity;
import org.linghuxiong.spring.batch.admin.model.TriggerEntity;
import org.linghuxiong.spring.batch.admin.service.JobExecutionHistoryService;
import org.springframework.batch.core.repository.dao.JobExecutionDao;
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
 * @date 2019/11/13 10:48 下午
 */
@Service
public class JobExecutionHistoryServiceImpl implements JobExecutionHistoryService {

    @Autowired
    JobExecutionHistoryDao jobExecutionHistoryDao;

    @Override
    public Page<JobExecutionHistoryEntity> loadJobHistoryPageable(Pageable pageable, String jobName, String userName, Integer status, Long runId) {
        Specification<JobExecutionHistoryEntity> queryCondition = new Specification<JobExecutionHistoryEntity>() {
            @Override
            public Predicate toPredicate(Root<JobExecutionHistoryEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (jobName != null) {
                    predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("jobName")), "%"+jobName.toUpperCase()+"%"));
                }

                if (userName != null) {
                    predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("userName")), "%"+userName.toUpperCase()+"%"));
                }

                if (status != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("status"), status));
                }

                if (runId != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("runId"), runId));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        return jobExecutionHistoryDao.findAll(queryCondition,pageable);
    }
}
