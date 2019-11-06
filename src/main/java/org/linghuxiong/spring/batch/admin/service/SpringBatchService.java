package org.linghuxiong.spring.batch.admin.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author linghuxiong
 * @date 2019/11/5 10:53 下午
 */
public interface SpringBatchService {

    Page<JSONObject> loadBatchPageable(Pageable pageable,String jobName,String status);

    void stopBatch(Long jobExecutionId);
}
