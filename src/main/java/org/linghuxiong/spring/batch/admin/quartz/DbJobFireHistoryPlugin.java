package org.linghuxiong.spring.batch.admin.quartz;

import org.linghuxiong.spring.batch.admin.dao.QuartzJobFireHistoryDao;
import org.linghuxiong.spring.batch.admin.model.QuartzJobFireHistoryEntity;
import org.quartz.*;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.spi.ClassLoadHelper;
import org.quartz.spi.SchedulerPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.scheduling.quartz.ResourceLoaderClassLoadHelper;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Date;

/**
 * @author linghuxiong
 * @date 2019/11/14 10:38 下午
 */
@Component
public class DbJobFireHistoryPlugin implements SchedulerPlugin, JobListener {

    private String name;

    private QuartzJobFireHistoryDao dao;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {

        Trigger trigger = context.getTrigger();

        QuartzJobFireHistoryEntity entity = new QuartzJobFireHistoryEntity();
        entity.setTriggerGroup(context.getTrigger().getKey().getGroup());
        entity.setTriggerName(context.getTrigger().getKey().getName());
        entity.setFireTime(new Date());
        entity.setNextFireTime(trigger.getNextFireTime());
        entity.setPreviousFireTime(trigger.getPreviousFireTime());
        entity.setJobGroup(context.getJobDetail().getKey().getGroup());
        entity.setJobName(context.getJobDetail().getKey().getName());
        entity.setFireInstanceId(context.getFireInstanceId());
        dao.save(entity);
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {

        QuartzJobFireHistoryEntity entity = dao.findById(context.getFireInstanceId()).get();
        if(entity != null){
            entity.setFinishTime(new Date());

            if (jobException != null) {
                entity.setMessage(jobException.getMessage());
                entity.setStatus(0);
            } else {
                entity.setMessage(String.valueOf(context.getResult()));
                entity.setStatus(1);
            }
            dao.save(entity);
        }
    }

    @Override
    public void initialize(String name, Scheduler scheduler, ClassLoadHelper loadHelper) throws SchedulerException {
        this.name = name;
        this.dao = ((AnnotationConfigServletWebServerApplicationContext)SchedulerFactoryBean.getConfigTimeResourceLoader()).getBean(QuartzJobFireHistoryDao.class);
        scheduler.getListenerManager().addJobListener(this, EverythingMatcher.allJobs());
    }

    @Override
    public void start() {

    }

    @Override
    public void shutdown() {

    }
}
