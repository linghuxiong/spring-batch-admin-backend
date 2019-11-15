package org.linghuxiong.spring.batch.admin;


import lombok.extern.slf4j.Slf4j;
import org.linghuxiong.spring.batch.admin.quartz.QuartzTriggerManager;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author eric
 */
@Slf4j
public class BuildTriggerListener implements SpringApplicationRunListener {

    public BuildTriggerListener(SpringApplication sa, String[] args) {
        super();
    }

    @Override
    public void starting() {

    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
    }

    @Override
    public void started(ConfigurableApplicationContext context) {

        log.info("@@ batch server started @@");

        QuartzTriggerManager quartzTriggerManager = null;

        try {
            quartzTriggerManager = context.getBean(QuartzTriggerManager.class);
        } catch (NoSuchBeanDefinitionException e) {
            return;
        }

        try {

            if (quartzTriggerManager != null) {
                quartzTriggerManager.initTriggers();
            } else {
                log.warn("quartzTriggerManager is not existed!");
                return;
            }
        } catch (Exception e) {
            log.error("cannot initTriggers", e);
        }
    }
}
