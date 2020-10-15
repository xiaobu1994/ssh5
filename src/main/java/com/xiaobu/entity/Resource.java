package com.xiaobu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/29 14:03
 * @description V1.0
 */
@Table(name = "tb_resource")
@Entity
@Data
public class Resource implements Serializable {
    private static final long serialVersionUID = -868260621470310505L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源唯一标识
     */
    private String sourceKey;

    /**
     * 资源类型,0:目录;1:菜单;2:按钮
     */
    private Integer type;

    /**
     * 资源url
     */
    private String sourceUrl;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否隐藏
     *
     * 0显示 1隐藏
     */
    private Integer isHide;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Resource parent;
}
