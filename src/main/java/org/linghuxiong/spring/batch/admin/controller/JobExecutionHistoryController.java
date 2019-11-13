package org.linghuxiong.spring.batch.admin.controller;

import com.alibaba.fastjson.JSONObject;
import org.linghuxiong.spring.batch.admin.model.JobExecutionHistoryEntity;
import org.linghuxiong.spring.batch.admin.service.JobExecutionHistoryService;
import org.linghuxiong.spring.batch.admin.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author linghuxiong
 * @date 2019/11/8 1:48 下午
 */
@RestController
@RequestMapping("/jobHistory")
public class JobExecutionHistoryController {

    @Autowired
    JobExecutionHistoryService jobExecutionHistoryService;

    @GetMapping("/load")
    public Page<JobExecutionHistoryEntity> loadQuartzPageable(@RequestParam(required = false) Integer currentPage, @RequestParam(required = false) Integer pageSize,
                                                              @RequestParam(required = false) Integer status,
                                                              @RequestParam(required = false) String userName,
                                                              @RequestParam(required = false) String jobName,
                                                              @RequestParam(required = false) Long runId) {
        if(currentPage == null){
            currentPage = Integer.valueOf(1);
        }

        if(pageSize == null){
            pageSize = Integer.valueOf(10);
        }

        Pageable pageable = PageRequest.of(currentPage-1, pageSize);
        return jobExecutionHistoryService.loadJobHistoryPageable(pageable,jobName,userName,status,runId);
    }
}
