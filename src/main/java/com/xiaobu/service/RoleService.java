package com.xiaobu.service;

import com.xiaobu.entity.Role;
import com.xiaobu.base.service.BaseService;

/**
 * @author xiaobu
 * @date 2018-11-30 14:53:31
 * @description 这些实现类必须加 @Repository 否则回报没有注入bean 
 */
public interface RoleService extends BaseService <Role,Integer> {

    /**
     * 添加或者修改角色
     * @param role
     */
    void saveOrUpdate(Role role);

    /**
     * 给角色分配资源
     * @param id 角色ID
     * @param resourceIds 资源ids
     */
    void grant(Integer id, String[] resourceIds);

}