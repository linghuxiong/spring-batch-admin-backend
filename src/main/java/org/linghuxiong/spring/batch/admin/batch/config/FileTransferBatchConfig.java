package org.linghuxiong.spring.batch.admin.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

/**
 * @author linghuxiong
 * @date 2019-07-25 16:16
 */
@Configurable
@Slf4j
public class FileTransferBatchConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @StepScope
    @Bean
    public ItemReader<String> itemReader(){
        //FlatFileItemReader
        return new ItemReader<String>() {
            @Override
            public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                log.info("==================================> exec ItemReader");
                int i = new Random().nextInt(2);
                if(i == 0){
                    return null;
                }
                return i+"";
            }
        };
    }

    @StepScope
    @Bean
    public ItemProcessor<String,String> itemProcessor(){
        return new ItemProcessor<String, String>() {
            @Override
            public String process(String item) throws Exception {
                log.info("==================================> exec ItemProcessor");
                return item;
            }
        };
    }

    @StepScope
    @Bean
    public ItemWriter<String> itemWriter(){
        return new ItemWriter<String>() {
            @Override
            public void write(List<? extends String> list) throws Exception {
                log.info("==================================> exec ItemWriter");
            }
        };
    }

    @Bean
    public Step step1(ItemReader itemReader,ItemProcessor itemProcessor,ItemWriter itemWriter){
        return stepBuilderFactory.get("step1")
                .<String,String>chunk(2)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public Job job1(Step step1){
        return jobBuilderFactory.get("fileTransfer")
                .start(step1)
                .build();
    }

}
