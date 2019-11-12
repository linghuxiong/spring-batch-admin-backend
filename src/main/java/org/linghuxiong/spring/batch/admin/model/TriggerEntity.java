package org.linghuxiong.spring.batch.admin.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**Ø
 * @author linghuxiong
 * @date 2019/11/8 4:05 下午
 */
@Data
@Table(name = "T_TRIGGER")
@Entity
public class TriggerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TRIGGER_NAME")
    private String name;

    @Column(name = "TRIGGER_GROUP")
    private String group;

    @Column(name = "CRON_EXPRESSION")
    private String cronExpression;

    @Column(name = "TIME_INTERVAL")
    private Long timeInterval;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "UPDATE_TIME")
    private Date updatedAt;

    @Column(name = "INSERT_TIME")
    private Date createdAt;

}
