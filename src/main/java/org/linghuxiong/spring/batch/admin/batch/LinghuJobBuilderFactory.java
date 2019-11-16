package org.linghuxiong.spring.batch.admin.batch;

import org.linghuxiong.spring.batch.admin.batch.listener.LinghuJobListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author linghuxiong
 * @date 2019/11/16 11:58 下午
 */
public class LinghuJobBuilderFactory extends JobBuilderFactory {

    @Autowired
    LinghuJobListener linghuJobListener;

    public LinghuJobBuilderFactory(JobRepository jobRepository) {
        super(jobRepository);
    }

    @Override
    public JobBuilder get(String name) {
        return super.get(name).listener(linghuJobListener);
    }
}
