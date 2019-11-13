package org.linghuxiong.spring.batch.admin.controller;

import com.alibaba.fastjson.JSONObject;
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
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    QuartzService quartzService;

    @GetMapping("/load")
    public Page<JSONObject> loadQuartzPageable(@RequestParam(required = false) Integer currentPage, @RequestParam(required = false) Integer pageSize,
                                               @RequestParam(required = false) String triggerStatus,
                                               @RequestParam(required = false) String triggerName,
                                               @RequestParam(required = false) String schedName,
                                               @RequestParam(required = false) String jobName,
                                               @RequestParam(required = false) String jobGroup,
                                               @RequestParam(required = false) String triggerGroup) {
        if(currentPage == null){
            currentPage = Integer.valueOf(1);
        }

        if(pageSize == null){
            pageSize = Integer.valueOf(10);
        }

        Pageable pageable = PageRequest.of(currentPage-1, pageSize);
        return quartzService.loadQuartzPageable(pageable,schedName,triggerGroup,triggerName,jobGroup,jobName,triggerStatus);
    }

    @PostMapping("/remove")
    public String removeQuartzTriggerJob(@RequestParam String key){
        quartzService.removeQuartzTriggerJob(key);
        return "success";
    }

}
