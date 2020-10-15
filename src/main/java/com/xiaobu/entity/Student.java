package com.xiaobu.entity;

/**
 * @author tanhw119214  on  2018/6/12 17:33
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 */
@Entity
@Table(name = "test_student")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", unique = true, nullable = false, length = 32)
    private String id;
    //姓名
    @Column(nullable = false, length = 5)
    private String name;
    //登录密码
    private String password;
    //对应着mysql里面的dateTime类型
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    //也是对应着mysql里面的dateTime类型
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateTime;
    public Student( String name, String password,LocalDateTime createTime) {
        this.name = name;
        this.password = password;
        this.createTime = createTime;
    }


}


