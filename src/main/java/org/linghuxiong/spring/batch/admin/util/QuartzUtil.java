package org.linghuxiong.spring.batch.admin.util;

import org.linghuxiong.spring.batch.admin.model.JobEntity;
import org.linghuxiong.spring.batch.admin.model.TriggerEntity;
import org.linghuxiong.spring.batch.admin.quartz.QuartzJobForCallSpringBatchJob;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.springframework.util.StringUtils;

/**
 * @author linghuxiong
 * @date 2019/11/16 1:16 上午
 */
public class QuartzUtil {

    public static Trigger buildTrigger(TriggerEntity trigger){
        Trigger t = TriggerBuilder.newTrigger()
                .withIdentity(new TriggerKey(trigger.getName(), trigger.getGroup()))
                .withSchedule(buildSchedulerBuilder(trigger))
                .build();
        return t;

    }

    public static ScheduleBuilder<?> buildSchedulerBuilder(TriggerEntity trigger) {
        if (!StringUtils.isEmpty(trigger.getCronExpression())) {
            return CronScheduleBuilder.cronSchedule(trigger.getCronExpression());
        } else {
            return SimpleScheduleBuilder.repeatSecondlyForever(trigger.getTimeInterval().intValue());
        }
    }

    public static JobDetail buildJobDetail(JobEntity jobEntity){
        JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setDescription(jobEntity.getJobDesc());
        jobDetail.setGroup(jobEntity.getGroup());
        jobDetail.setJobClass(QuartzJobForCallSpringBatchJob.class);
        jobDetail.setKey(new JobKey(jobEntity.getName(), jobEntity.getGroup()));
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("SPRING_JOB_NAME", jobEntity.getSpringJobName());
        jobDetail.setJobDataMap(jobDataMap);
        return jobDetail;
    }

}
