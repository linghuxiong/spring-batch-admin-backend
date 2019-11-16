package org.linghuxiong.spring.batch.admin.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author linghuxiong
 * @date 2019/11/13 10:31 下午
 */
@Data
@Table(name = "T_JOB_EXECUTION_HISTORY")
@Entity
public class JobExecutionHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "JOB_NAME")
    private String jobName;

    @Column(name = "RUN_ID")
    private Long runId;

    @Column(name = "EXIT_CODE")
    private String exitCode;

    @Column(name = "EXIT_MESSAGE")
    private String exitMessage;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "START_TIME")
    private Date startAt;

    @Column(name = "END_TIME")
    private Date endAt;

    @Column(name = "UPDATE_TIME")
    private Date updatedAt;

    @Column(name = "INSERT_TIME")
    private Date createdAt;

}
