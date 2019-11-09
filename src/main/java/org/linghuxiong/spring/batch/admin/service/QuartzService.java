package org.linghuxiong.spring.batch.admin.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author linghuxiong
 * @date 2019/11/8 1:51 下午
 */
public interface QuartzService {

    Page<JSONObject> loadQuartzPageable(Pageable pageable,String schedName, String triggerGroup, String triggerName, String jobGroup, String jobName,String triggerStatus);

    void removeQuartzTriggerJob(String id);
}
