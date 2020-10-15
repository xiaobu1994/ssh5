package com.xiaobu.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/20 17:07
 * @description V1.0 用来做redis的验证的
 *
 */
@Entity
@Table(name = "test_area")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Area implements Serializable {
    private static final long serialVersionUID = 5568250902007317710L;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
    private int id;
    //姓名
    @Column(name="name",columnDefinition=("varchar(50)  default null comment '名称'"))
    private String name;

    @Column(name="createTime",columnDefinition=("varchar(50)  default null comment '创建时间'"))
    private String createTime;

    public Area(int id,String name, String createTime) {
        this.id=id;
        this.name = name;
        this.createTime = createTime;
    }
}
