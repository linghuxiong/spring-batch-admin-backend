package org.linghuxiong.spring.batch.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.linghuxiong.spring.batch.admin.dao.QuartzDao;
import org.linghuxiong.spring.batch.admin.dao.QuartzJobFireHistoryDao;
import org.linghuxiong.spring.batch.admin.model.JobEntity;
import org.linghuxiong.spring.batch.admin.model.QuartzJobFireHistoryEntity;
import org.linghuxiong.spring.batch.admin.service.QuartzService;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linghuxiong
 * @date 2019/11/8 1:51 下午
 */
@Service
@Slf4j
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    QuartzDao quartzDao;

    @Autowired
    QuartzJobFireHistoryDao quartzJobFireHistoryDao;

    @Autowired
    private Scheduler scheduler ;

    @Override
    public Page<JSONObject> loadQuartzPageable(Pageable pageable, String schedName, String triggerGroup, String triggerName, String jobGroup, String jobName, String triggerStatus) {
        return quartzDao.loadQuartzPageable(pageable,schedName,triggerGroup,triggerName,jobGroup,jobName,triggerStatus);
    }

    @Override
    public Page<QuartzJobFireHistoryEntity> loadQuartzJobHistoryPageable(Pageable pageable, String jobGroup, String jobName, String triggerGroup, String triggerName, Integer status) {
        Specification<QuartzJobFireHistoryEntity> queryCondition = new Specification<QuartzJobFireHistoryEntity>() {
            @Override
            public Predicate toPredicate(Root<QuartzJobFireHistoryEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (jobName != null) {
                    predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("jobName")), "%" + jobName.toUpperCase() + "%"));
                }

                if (jobGroup != null) {
                    predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("jobGroup")), "%" + jobGroup.toUpperCase() + "%"));
                }

                if (triggerGroup != null) {
                    predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("triggerGroup")), "%" + triggerGroup.toUpperCase() + "%"));
                }

                if (triggerName != null) {
                    predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("triggerName")), "%" + triggerName.toUpperCase() + "%"));
                }

                if (status != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("status"), status));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        return quartzJobFireHistoryDao.findAll(queryCondition, pageable);
    }

    @Override
    public void pausedTrigger(String key) throws SchedulerException {
        if(!StringUtils.isEmpty(key)){
            String[] params =  key.split("#");
            log.info("TRIGGER_GROUP" + params[1]);
            log.info("TRIGGER_NAME" + params[2]);
            scheduler.pauseTrigger(TriggerKey.triggerKey(params[2],params[1]));
        }
    }

    @Override
    public void resumeTrigger(String key) throws SchedulerException {
        if(!StringUtils.isEmpty(key)){
            String[] params = key.split("#");
            log.info("TRIGGER_GROUP" + params[1]);
            log.info("TRIGGER_NAME" + params[2]);
            scheduler.resumeTrigger(TriggerKey.triggerKey(params[2],params[1]));
        }
    }
}
