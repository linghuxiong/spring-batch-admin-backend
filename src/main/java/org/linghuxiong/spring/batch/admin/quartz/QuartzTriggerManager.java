package org.linghuxiong.spring.batch.admin.quartz;

import lombok.extern.slf4j.Slf4j;
import org.linghuxiong.spring.batch.admin.dao.JobDao;
import org.linghuxiong.spring.batch.admin.dao.TriggerDao;
import org.linghuxiong.spring.batch.admin.model.JobEntity;
import org.linghuxiong.spring.batch.admin.model.TriggerEntity;
import org.linghuxiong.spring.batch.admin.util.QuartzUtil;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.calendar.HolidayCalendar;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author linghuxiong
 * @date 2019/11/13 11:26 下午
 */
@Component
@Slf4j
public class QuartzTriggerManager {

    @Autowired
    private JobDao jobDao;

    @Autowired
    private TriggerDao triggerDao;

    @Autowired
    private Scheduler scheduler = null;

    public void initTriggers() {
        try {
            removeAllExistedJobs();
            buildTriggers();
            buildCalendars();
            scheduler.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void removeAllExistedJobs() throws Exception {
        log.info("begin to remove all the job keys in current database!");
        Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());
        List<JobKey> willBeDeletedJobKeys = new ArrayList<>();
        willBeDeletedJobKeys.addAll(jobKeys);
        scheduler.deleteJobs(willBeDeletedJobKeys);
        log.info("success remove all the job keys {} in current database!", willBeDeletedJobKeys.size());
    }

    private void buildTriggers(){

        List<JobEntity> triggers = jobDao.findAllByTriggerNameIsNotNull();
        if(!CollectionUtils.isEmpty(triggers)){
            triggers.stream().forEach(jobEntity->{
                String triggerName = jobEntity.getTriggerName();
                TriggerEntity triggerEntity = triggerDao.getByNameEquals(triggerName);

                Trigger trigger = QuartzUtil.buildTrigger(triggerEntity);
                try {
                    if (scheduler.checkExists(trigger.getKey())) {
                        scheduler.rescheduleJob(trigger.getKey(), trigger);
                    } else {
                        JobDetail jobDetail = QuartzUtil.buildJobDetail(jobEntity);
                        scheduler.scheduleJob(jobDetail, trigger);
                    }
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            });
        }

    }



    private void buildCalendars() throws Exception {
        HolidayCalendar cal = new HolidayCalendar();
        scheduler.addCalendar("default-holiday-calendar", cal, true, true);
    }
}
