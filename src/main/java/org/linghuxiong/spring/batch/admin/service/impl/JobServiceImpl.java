package org.linghuxiong.spring.batch.admin.service.impl;

import org.linghuxiong.spring.batch.admin.dao.JobDao;
import org.linghuxiong.spring.batch.admin.model.JobEntity;
import org.linghuxiong.spring.batch.admin.service.JobService;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author linghuxiong
 * @date 2019/11/9 12:44 上午
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobDao jobDao;

    @Autowired
    private Scheduler scheduler ;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    JobRegistry jobRegistry;

    @Override
    public Page<JobEntity> loadJobPageable(Pageable pageable, String name, Integer type, Integer status, String triggerName, String springJobName) {
        Specification<JobEntity> queryCondition = new Specification<JobEntity>() {
            @Override
            public Predicate toPredicate(Root<JobEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (name != null) {
                    predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name.toUpperCase() + "%"));
                }

                if (triggerName != null) {
                    predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("triggerName")), "%" + triggerName.toUpperCase() + "%"));
                }

                if (springJobName != null) {
                    predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("springJobName")), "%" + springJobName.toUpperCase() + "%"));
                }

                if (type != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("type"), type));
                }

                if (status != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("status"), status));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        return jobDao.findAll(queryCondition, pageable);
    }

    @Override
    public void launch(Long jobId) throws NoSuchJobException, JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobEntity jobEntity = jobDao.getOne(jobId);
        jobLauncher.run(jobRegistry.getJob(jobEntity.getSpringJobName()),new JobParametersBuilder().addDate("#CURRENNCY",new Date()).toJobParameters());
    }

    @Override
    public JobEntity save(JobEntity jobEntity) {
        jobEntity.setUpdatedAt(new Date());
        return jobDao.save(jobEntity);
    }

    @Override
    public void delete(Long jobId) {
        jobDao.deleteById(jobId);
    }

    @Override
    public JobEntity toggleStatus(Long jobId, Integer status) throws SchedulerException {
        JobEntity entity = jobDao.getOne(jobId);

        if(status.intValue() == 0){
            scheduler.pauseJob(JobKey.jobKey(entity.getName(),entity.getGroup()));
        }else if(status.intValue() == 1){
            scheduler.resumeJob(JobKey.jobKey(entity.getName(),entity.getGroup()));
        }

        entity.setStatus(status);
        jobDao.save(entity);
        return entity;
    }
}
