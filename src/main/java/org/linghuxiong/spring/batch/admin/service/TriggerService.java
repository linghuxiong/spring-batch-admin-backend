package org.linghuxiong.spring.batch.admin.service;

import org.linghuxiong.spring.batch.admin.model.TriggerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author linghuxiong
 * @date 2019/11/8 4:34 下午
 */
public interface TriggerService {

    TriggerEntity save(TriggerEntity triggerEntity);
    void delete(Long triggerId);
    TriggerEntity toggleStatus(Long triggerId,Integer status);
    Page<TriggerEntity> loadTriggerPageable(Pageable pageable, String triggerName, String triggerGroup, Integer triggerStatus);
}
