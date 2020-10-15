package com.xiaobu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/13 10:51
 * @description V1.0  @GeneratedValue(strategy = GenerationType.AUTO）表明程序会从另外三种里面选择最合适的一种
 */
@Entity
@Table(name = "test_book")
@NoArgsConstructor
@Setter
@Getter
@ToString
@ApiModel
public class Book implements Serializable {
    private static final long serialVersionUID = 6214167880862325845L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ApiModelProperty("书籍名")
    @Column(name="name",columnDefinition=("varchar(50)  default null comment '名称'"))
    private String name;
    @ApiModelProperty("作者")
    @Column(name="author",columnDefinition=("varchar(252)  default null comment '作者'"))
    private String author;


}
