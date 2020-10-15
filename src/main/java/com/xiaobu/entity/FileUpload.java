package com.xiaobu.entity;

import com.xiaobu.base.util.DateUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/14 17:04
 * @description V1.0
 */
@Entity
@Table(name = "test_file_upload")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class FileUpload implements Serializable {
    private static final long serialVersionUID = -6007317940278221283L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    private String path;

    private String createTime = DateUtil.getCurrentDateTimeToStr1();
}
