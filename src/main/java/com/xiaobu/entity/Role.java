package com.xiaobu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/29 14:01
 * @description V1.0
 */
@Entity
@Table(name = "tb_role")
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 1521527343972914807L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色key
     */
    private String roleKey;

    /**
     * 角色状态,0：正常；1：删除
     */
    private Integer status;

    /**
     * 角色描述
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

    @ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "tb_role_resource", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "resource_id") })
    private java.util.Set<Resource> resources;
}
