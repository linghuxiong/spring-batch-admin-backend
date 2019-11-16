package org.linghuxiong.spring.batch.admin.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

/**
 * @author linghuxiong
 * @date 2019-07-25 16:16
 */
@Configurable
public class FileTransferBatchConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @StepScope
    @Bean
    public ItemReader<String> itemReader(){
        //FlatFileItemReader
        return new FlatFileItemReaderBuilder<String>()
                .name("simpleFileReader")
                .resource(new ClassPathResource("batch-data/2019072401.txt"))
                .lineMapper(new PassThroughLineMapper())
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<String,String> itemProcessor(){
        return new ItemProcessor<String, String>() {
            @Override
            public String process(String item) throws Exception {
                return "[linghuxiong]"+item;
            }
        };
    }

    @StepScope
    @Bean
    public ItemWriter<String> itemWriter(){
        return new FlatFileItemWriterBuilder<String>()
                .name("simpleFileWriter")
                .lineAggregator(new PassThroughLineAggregator<String>())
                .resource(new FileSystemResource("/Users/eric/Documents/dev/linghuxiong/spring-boot-demo/batch/target/2019072402.txt"))
                .build();
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .<String,String>chunk(2)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Job job1(){
        return jobBuilderFactory.get("fileTransfer")
                .start(step1())
                .build();
    }




}
