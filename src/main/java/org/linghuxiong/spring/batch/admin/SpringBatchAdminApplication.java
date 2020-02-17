package org.linghuxiong.spring.batch.admin;

import org.linghuxiong.spring.batch.admin.batch.LinghuJobBuilderFactory;
import org.linghuxiong.spring.batch.admin.batch.config.FileTransferBatchConfig;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author eric
 */
@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
@Import({FileTransferBatchConfig.class})
public class SpringBatchAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchAdminApplication.class, args);
    }

    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry registry) {
        JobRegistryBeanPostProcessor postProcessor = new JobRegistryBeanPostProcessor();
        postProcessor.setJobRegistry(registry);
        return postProcessor;
    }

    @Bean
    public JobBuilderFactory jobBuilderFactory(JobRepository jobRepository){
        return new LinghuJobBuilderFactory(jobRepository);
    }

}
