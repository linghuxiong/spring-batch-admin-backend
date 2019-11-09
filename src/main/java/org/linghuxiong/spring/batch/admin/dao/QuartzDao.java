package org.linghuxiong.spring.batch.admin.dao;

import com.alibaba.fastjson.JSONObject;
import org.linghuxiong.spring.batch.admin.util.ResultSetExtractor2Json;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linghuxiong
 * @date 2019/11/8 1:52 下午
 */
@Repository
public class QuartzDao extends BaseDao{

    public Page<JSONObject> loadQuartzPageable(Pageable pageable,String schedName, String triggerGroup, String triggerName, String jobGroup, String jobName,String triggerStatus){

        String querySql = "select CONCAT_WS('-',SCHED_NAME,TRIGGER_GROUP,TRIGGER_NAME,JOB_GROUP,JOB_NAME) as ID, SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP,JOB_NAME,JOB_GROUP,NEXT_FIRE_TIME,PREV_FIRE_TIME,PRIORITY,TRIGGER_STATE,START_TIME,END_TIME,MISFIRE_INSTR from QRTZ_TRIGGERS A";

        List<Object> parames = new ArrayList<>();

        if(!StringUtils.isEmpty(schedName)){
            querySql += " AND UPPER(A.SCHED_NAME) LIKE ? ";
            parames.add("%" + schedName.toUpperCase() + "%");
        }

        if(!StringUtils.isEmpty(triggerGroup)){
            querySql += " AND UPPER(A.TRIGGER_GROUP) LIKE ? ";
            parames.add("%" + triggerGroup.toUpperCase() + "%");
        }

        if(!StringUtils.isEmpty(triggerName)){
            querySql += " AND UPPER(A.TRIGGER_NAME) LIKE ? ";
            parames.add("%" + triggerName.toUpperCase() + "%");
        }

        if(!StringUtils.isEmpty(jobName)){
            querySql += " AND UPPER(A.JOB_NAME) LIKE ? ";
            parames.add("%" + jobName.toUpperCase() + "%");
        }

        if(!StringUtils.isEmpty(jobGroup)){
            querySql += " AND UPPER(A.JOB_GROUP) LIKE ? ";
            parames.add("%" + jobGroup.toUpperCase() + "%");
        }

        if(!StringUtils.isEmpty(triggerStatus)){
            querySql += " AND A.TRIGGER_STATE = ? ";
            parames.add(triggerStatus);
        }

        int totalSize = jdbcTemplate.queryForObject(MessageFormat.format(TOTAL_NUMBER_SQL, querySql),Integer.class,parames.toArray()).intValue();

        parames.add(pageable.getPageNumber() * pageable.getPageSize());
        parames.add(pageable.getPageSize());

        List<JSONObject> content = jdbcTemplate.query(MessageFormat.format(PAGEABLE_SQL, querySql),new ResultSetExtractor2Json(),parames.toArray());

        Page page = new PageImpl(content,pageable,totalSize);
        return page;
    }
}
