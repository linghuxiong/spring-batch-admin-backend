package org.linghuxiong.spring.batch.admin.dao;

import com.alibaba.fastjson.JSONObject;
import org.linghuxiong.spring.batch.admin.util.ResultSetExtractor2Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linghuxiong
 * @date 2019/11/5 10:54 下午
 */
@Repository
public class SpringBatchDao {

    private static final String TOTAL_NUMBER_SQL = " select count(*) from ( {0} ) job ";
    private static final String PAGEABLE_SQL = " select * from ( {0} ) job limit ?,? ";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Page<JSONObject> loadBatchPageable(Pageable pageable,String jobName,String status){

        List<Object> parames = new ArrayList<>();
        String findAllJobExecutions = "SELECT A.JOB_EXECUTION_ID, A.START_TIME, A.END_TIME, A.STATUS, A.EXIT_CODE, A.EXIT_MESSAGE, A.CREATE_TIME, A.LAST_UPDATED, A.VERSION, A.JOB_CONFIGURATION_LOCATION, B.JOB_NAME "
                + " from BATCH_JOB_EXECUTION A,BATCH_JOB_INSTANCE B WHERE A.JOB_INSTANCE_ID = B.JOB_INSTANCE_ID ";

        if(!StringUtils.isEmpty(jobName)){
            findAllJobExecutions += " AND UPPER(B.JOB_NAME) LIKE ? ";
            parames.add("%" + jobName.toUpperCase() + "%");
        }

        if(!StringUtils.isEmpty(status)){
            findAllJobExecutions += " AND A.STATUS = ? ";
            parames.add(status);
        }

        int totalSize = jdbcTemplate.queryForObject(MessageFormat.format(TOTAL_NUMBER_SQL, findAllJobExecutions),Integer.class,parames.toArray()).intValue();

        parames.add(pageable.getPageNumber() * pageable.getPageSize());
        parames.add(pageable.getPageSize());

        findAllJobExecutions += " ORDER BY JOB_EXECUTION_ID DESC ";

        List<JSONObject> content = jdbcTemplate.query(MessageFormat.format(PAGEABLE_SQL, findAllJobExecutions),new ResultSetExtractor2Json(),parames.toArray());




        Page page = new PageImpl(content,pageable,totalSize);
        return page;
    }
}
