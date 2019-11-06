package org.linghuxiong.spring.batch.admin.controller;

import com.alibaba.fastjson.JSONObject;
import org.linghuxiong.spring.batch.admin.service.SpringBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linghuxiong
 * @date 2019/11/5 10:50 下午
 */
@RestController
@RequestMapping("/batch")
public class SpringBatchController {

    @Autowired
    SpringBatchService batchService;

    @RequestMapping("/load")
    public Page<JSONObject> loadBatch(@RequestParam(required = false) Integer currentPage,@RequestParam(required = false) Integer pageSize ,
                                      @RequestParam(value = "name",required = false) String jobName,
                                      @RequestParam(value = "status",required = false) String status,
                                      @RequestParam(required = false) String sorter){

        if(currentPage == null){
            currentPage = Integer.valueOf(1);
        }

        if(pageSize == null){
            pageSize = Integer.valueOf(10);
        }

        Pageable pageable = PageRequest.of(currentPage-1, pageSize);
        return batchService.loadBatchPageable(pageable,jobName,status);
    }

    @PostMapping("/stop")
    public String stopBatch(@RequestParam Long jobExecutionId){
        batchService.stopBatch(jobExecutionId);
        return "success";
    }

}
