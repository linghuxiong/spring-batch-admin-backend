package org.linghuxiong.spring.batch.admin.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.ReferenceJobFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author linghuxiong
 * @date 2019/12/24 10:40 下午
 */
@RestController
@RequestMapping("/autoJob")
public class AutoJobController {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    JobRegistry jobRegistry;

    @Autowired
    JobLauncher jobLauncher;

    @GetMapping("/build")
    public String buildJob() throws NoSuchJobException, DuplicateJobException {
        Step testStep = stepBuilderFactory.get("AAA").tasklet(new Tasklet(){
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                /**
                 *   https://www.cnblogs.com/DreamDrive/p/5417431.ht
                 *   Java 动态编译，可以支持页面传入Java 代码片段进行编译，但是这种方法有个弊端就是不能写太复杂的内容，暂时没有想到对应的场景
                 *   当然，可以上传整个类的源码文件进行编译，也可以支持上传class 文件
                 */
                System.out.println("@@@@@@@@#$$$$$$$$$$$#################@!#############################");
                return RepeatStatus.FINISHED;
            }
        }).build();
        Job fileTransfer1 = jobBuilderFactory.get("fileTransfer1").start(testStep).build();
        jobRegistry.register(new ReferenceJobFactory(fileTransfer1));
        return "SUCCESS";
    }

    @GetMapping("/launcher")
    public String launcherJob() throws NoSuchJobException, JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        return JSON.toJSONString(jobLauncher.run(jobRegistry.getJob("fileTransfer1"),new JobParametersBuilder().addDate("#CURRENNCY",new Date()).toJobParameters()));
    }
}
