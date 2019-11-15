package org.linghuxiong.spring.batch.admin.dao.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.linghuxiong.spring.batch.admin.dao.JobDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author linghuxiong
 * @date 2019/11/13 11:40 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JobDaoTest {

    @Autowired
    JobDao jobDao;

    @Test
    public void testGetAllByTriggerNameIsNotNull(){
        System.out.println(JSON.toJSONString(jobDao.findAllByTriggerNameIsNotNull()));
    }
}
