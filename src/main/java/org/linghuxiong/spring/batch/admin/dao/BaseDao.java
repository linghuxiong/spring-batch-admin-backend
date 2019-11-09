package org.linghuxiong.spring.batch.admin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author linghuxiong
 * @date 2019/11/8 3:22 下午
 */
public abstract class BaseDao {

    public static final String TOTAL_NUMBER_SQL = " select count(*) from ( {0} ) job ";
    public static final String PAGEABLE_SQL = " select * from ( {0} ) job limit ?,? ";

    @Autowired
    JdbcTemplate jdbcTemplate;
}
