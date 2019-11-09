package org.linghuxiong.spring.batch.admin.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author linghuxiong
 * @date 2019/11/8 4:05 下午
 */
@Data
@Table(name = "T_JOB")
@Entity
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "JOB_NAME")
    private String name;

    @Column(name = "JOB_GROUP")
    private String group;

    @Column(name = "JOB_TYPE")
    private Integer type;

    @Column(name = "ESTIMATED_TIME")
    private Long estimatedTime;

    @Column(name = "JOB_STATUS")
    private Integer status;

    @Column(name = "TRIGGER_NAME")
    private String triggerName;

    @Column(name = "SPRING_JOB_NAME")
    private String springJobName;

    @Column(name = "JOB_DESC")
    private String jobDesc;

    @Column(name = "PARAMS")
    private String params;

    @Column(name = "CALLBACK_URL")
    private String callbackUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "UPDATE_TIME")
    private Date updatedAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "INSERT_TIME")
    private Date createdAt;
}
