package org.linghuxiong.spring.batch.admin.controller;

import org.linghuxiong.spring.batch.admin.model.JobEntity;
import org.linghuxiong.spring.batch.admin.model.TriggerEntity;
import org.linghuxiong.spring.batch.admin.service.JobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author linghuxiong
 * @date 2019/11/8 4:04 下午
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobService jobService;

    @PostMapping("/save")
    public JobEntity save(@RequestBody JobEntity jobEntity){
        return jobService.save(jobEntity);
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long jobId){
        jobService.delete(jobId);
        return "success";
    }

    @PostMapping("/toggleStatus")
    public String toggle(@RequestParam Long jobId,@RequestParam Integer status) throws SchedulerException {
        jobService.toggleStatus(jobId,status);
        return "success";
    }

    @GetMapping("/load")
    public Page<JobEntity> loadTriggerPageable(@RequestParam(required = false) Integer currentPage, @RequestParam(required = false) Integer pageSize,
                                               @RequestParam(required = false) Integer status,
                                               @RequestParam(required = false) Integer type,
                                               @RequestParam(required = false) String name,
                                               @RequestParam(required = false) String triggerName,
                                               @RequestParam(required = false) String springJobName){
        if(currentPage == null){
            currentPage = Integer.valueOf(1);
        }

        if(pageSize == null){
            pageSize = Integer.valueOf(10);
        }

        Pageable pageable = PageRequest.of(currentPage-1, pageSize);
        return jobService.loadJobPageable(pageable,name,type,status,triggerName,springJobName);

    }

}
