package org.linghuxiong.spring.batch.admin.service;

import org.linghuxiong.spring.batch.admin.model.TriggerEntity;
import org.quartz.SchedulerException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author linghuxiong
 * @date 2019/11/8 4:34 下午
 */
public interface TriggerService {

    TriggerEntity save(TriggerEntity triggerEntity) throws SchedulerException;
    void delete(Long triggerId);
    TriggerEntity toggleStatus(Long triggerId,Integer status) throws SchedulerException;
    Page<TriggerEntity> loadTriggerPageable(Pageable pageable, String triggerName, String triggerGroup, Integer triggerStatus);
}
