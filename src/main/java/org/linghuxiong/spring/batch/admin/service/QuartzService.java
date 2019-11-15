package org.linghuxiong.spring.batch.admin.service;

import com.alibaba.fastjson.JSONObject;
import org.linghuxiong.spring.batch.admin.model.JobEntity;
import org.linghuxiong.spring.batch.admin.model.QuartzJobFireHistoryEntity;
import org.quartz.SchedulerException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author linghuxiong
 * @date 2019/11/8 1:51 下午
 */
public interface QuartzService {

    Page<JSONObject> loadQuartzPageable(Pageable pageable,String schedName, String triggerGroup, String triggerName, String jobGroup, String jobName,String triggerStatus);
    Page<QuartzJobFireHistoryEntity> loadQuartzJobHistoryPageable(Pageable pageable,  String jobGroup,String jobName, String triggerGroup, String triggerName,Integer status);

    void pausedTrigger(String key) throws SchedulerException;
    void resumeTrigger(String key) throws SchedulerException;
}
