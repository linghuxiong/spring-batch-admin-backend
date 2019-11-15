package org.linghuxiong.spring.batch.admin.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author linghuxiong
 * @date 2019/11/13 10:31 下午
 */
@Data
@Table(name = "T_QUARTZ_JOB_FIRE_HISTORY")
@Entity
public class QuartzJobFireHistoryEntity {

    @Id
    @Column(name = "FIRE_INSTANCE_ID")
    private String fireInstanceId;

    @Column(name = "TRIGGER_GROUP")
    private String triggerGroup;

    @Column(name = "TRIGGER_NAME")
    private String triggerName;

    @Column(name = "JOB_GROUP")
    private String jobGroup;

    @Column(name = "JOB_NAME")
    private String jobName;

    @Column(name = "FIRE_TIME")
    private Date fireTime;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "FINISH_TIME")
    private Date finishTime;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "PREVIOUS_FIRE_TIME")
    private Date previousFireTime;

    @Column(name = "NEXT_FIRE_TIME")
    private Date nextFireTime;
}
