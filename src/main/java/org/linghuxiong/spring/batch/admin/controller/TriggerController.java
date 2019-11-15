package org.linghuxiong.spring.batch.admin.controller;

import org.linghuxiong.spring.batch.admin.model.TriggerEntity;
import org.linghuxiong.spring.batch.admin.service.TriggerService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author linghuxiong
 * @date 2019/11/8 4:03 下午
 */
@RestController
@RequestMapping("/trigger")
public class TriggerController {

    @Autowired
    TriggerService triggerService;

    @PostMapping("/save")
    public TriggerEntity save(@RequestBody TriggerEntity triggerEntity) throws SchedulerException {
        return triggerService.save(triggerEntity);
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long triggerId){
        triggerService.delete(triggerId);
        return "success";
    }

    @PostMapping("/toggleStatus")
    public String toggle(@RequestParam Long triggerId,@RequestParam Integer status) throws SchedulerException {
        triggerService.toggleStatus(triggerId,status);
        return "success";
    }

    @GetMapping("/load")
    public Page<TriggerEntity> loadTriggerPageable(@RequestParam(required = false) Integer currentPage, @RequestParam(required = false) Integer pageSize,
                                                   @RequestParam(required = false) Integer triggerStatus,
                                                   @RequestParam(required = false) String triggerName,
                                                   @RequestParam(required = false) String triggerGroup){
        if(currentPage == null){
            currentPage = Integer.valueOf(1);
        }

        if(pageSize == null){
            pageSize = Integer.valueOf(10);
        }

        Pageable pageable = PageRequest.of(currentPage-1, pageSize);
        return triggerService.loadTriggerPageable(pageable,triggerName,triggerGroup,triggerStatus);

    }

}
