package org.linghuxiong.spring.batch.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.linghuxiong.spring.batch.admin.dao.QuartzDao;
import org.linghuxiong.spring.batch.admin.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author linghuxiong
 * @date 2019/11/8 1:51 下午
 */
@Service
@Slf4j
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    QuartzDao quartzDao;

    @Override
    public Page<JSONObject> loadQuartzPageable(Pageable pageable, String schedName, String triggerGroup, String triggerName, String jobGroup, String jobName, String triggerStatus) {
        return quartzDao.loadQuartzPageable(pageable,schedName,triggerGroup,triggerName,jobGroup,jobName,triggerStatus);
    }

    @Override
    public void removeQuartzTriggerJob(String id) {
        if(!StringUtils.isEmpty(id)){
            String[] params = StringUtils.split(id,"-");
            log.info("SCHED_NAME" + params[0]);
            log.info("TRIGGER_GROUP" + params[1]);
            log.info("TRIGGER_NAME" + params[2]);
            log.info("JOB_GROUP" + params[3]);
            log.info("JOB_NAME" + params[4]);
        }
    }
}
