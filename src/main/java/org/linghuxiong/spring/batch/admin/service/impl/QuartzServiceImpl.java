package org.linghuxiong.spring.batch.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.linghuxiong.spring.batch.admin.dao.QuartzDao;
import org.linghuxiong.spring.batch.admin.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author linghuxiong
 * @date 2019/11/8 1:51 下午
 */
@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    QuartzDao quartzDao;

    @Override
    public Page<JSONObject> loadQuartzPageable(Pageable pageable, String schedName, String triggerGroup, String triggerName, String jobGroup, String jobName, String triggerStatus) {
        return quartzDao.loadQuartzPageable(pageable,schedName,triggerGroup,triggerName,jobGroup,jobName,triggerStatus);
    }

    @Override
    public void removeQuartzTriggerJob(String id) {

    }
}
