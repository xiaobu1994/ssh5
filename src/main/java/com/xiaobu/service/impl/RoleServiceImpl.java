package com.xiaobu.service.impl;

import com.xiaobu.base.service.BaseServiceImpl;
import com.xiaobu.entity.Resource;
import com.xiaobu.entity.Role;
import com.xiaobu.service.ResourceService;
import com.xiaobu.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xiaobu
 * @date 2018-11-30 14:53:31
 * @description 这些实现类必须加 @Repository 否则回报没有注入bean 
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role,Integer> implements RoleService{

    @Autowired
    ResourceService resourceService;

    @Override
    public void saveOrUpdate(Role role) {
        if(role.getId() != 0){
            Role dbRole = findById(role.getId());
            dbRole.setUpdateTime(new Date());
            dbRole.setName(role.getName());
            dbRole.setDescription(role.getDescription());
            dbRole.setUpdateTime(new Date());
            dbRole.setStatus(role.getStatus());
            update(dbRole);
        }else{
            role.setCreateTime(new Date());
            role.setUpdateTime(new Date());
            save(role);
        }
    }

    @Override
    public void grant(Integer id, String[] resourceIds) {
        Role role = findById(id);
        Assert.notNull(role, "角色不存在");

        //Assert.state(!"administrator".equals(role.getRoleKey()),"超级管理员角色不能进行资源分配");
        Resource resource;
        Set<Resource> resources = new HashSet<Resource>();
        if(resourceIds != null){
            for (String resourceId : resourceIds) {
                if (StringUtils.isBlank(resourceId) || "0".equals(resourceId)) {
                    continue;
                }
                Integer rid = Integer.parseInt(resourceId);
                resource = resourceService.findById(rid);
                resources.add(resource);
            }
        }
        role.setResources(resources);
        update(role);
    }


    @Override
    public  void deleteById(Integer id){
        super.deleteById(id);
    }
}