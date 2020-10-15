package com.xiaobu.service;

import com.xiaobu.base.entity.vo.ZtreeView;
import com.xiaobu.base.service.BaseService;
import com.xiaobu.entity.Resource;

import java.util.List;

/**
 * @author xiaobu
 * @date 2018-11-30 14:58:27
 * @description 实现类必须加 @Repository 否则回报没有注入bean
 */
public interface ResourceService extends BaseService <Resource,Integer> {

    /**
     * 获取角色的权限树
     * @param roleId 角色id
     * @return tree
     */
    List<ZtreeView> tree(int roleId);

    /**
     * 修改或者新增资源
     * @param resource 资源
     */
    void saveOrUpdate(Resource resource);

}