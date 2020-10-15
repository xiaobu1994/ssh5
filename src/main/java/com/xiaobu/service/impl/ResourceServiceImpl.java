package com.xiaobu.service.impl;

import com.xiaobu.base.entity.vo.ZtreeView;
import com.xiaobu.base.service.BaseServiceImpl;
import com.xiaobu.entity.Resource;
import com.xiaobu.entity.Role;
import com.xiaobu.repository.ResourceRepository;
import com.xiaobu.service.ResourceService;
import com.xiaobu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author xiaobu
 * @date 2018-11-30 14:58:27
 * @description 这些实现类必须加 @Repository 否则回报没有注入bean 
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource,Integer> implements ResourceService{

    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    RoleService roleService;
    @Override
    @Cacheable(value = "resourceCache", key = "'tree' + #roleId")
    public List<ZtreeView> tree(int roleId) {
        List<ZtreeView> resulTreeNodes = new ArrayList<ZtreeView>();
        Role role = roleService.findById(roleId);
        Set<Resource> roleResources = role.getResources();
        resulTreeNodes.add(new ZtreeView(0L, null, "系统菜单", true));
        ZtreeView node;
        Sort sort = new Sort(Sort.Direction.ASC, "parent", "id", "sort");
        List<Resource> all = resourceRepository.findAll(sort);
        for (Resource resource : all) {
            node = new ZtreeView();
            node.setId((long) resource.getId());
            if (resource.getParent() == null) {
                node.setpId(0L);
            } else {
                node.setpId((long) resource.getParent().getId());
            }
            node.setName(resource.getName());
            if (roleResources != null && roleResources.contains(resource)) {
                node.setChecked(true);
            }
            resulTreeNodes.add(node);
        }
        return resulTreeNodes;
    }

    @Override
    public void saveOrUpdate(Resource resource) {
        if(resource.getId() != 0){
            Resource dbResource = findById(resource.getId());
            dbResource.setUpdateTime(new Date());
            dbResource.setName(resource.getName());
            dbResource.setSourceKey(resource.getSourceKey());
            dbResource.setType(resource.getType());
            dbResource.setSourceUrl(resource.getSourceUrl());
            dbResource.setLevel(resource.getLevel());
            dbResource.setSort(resource.getSort());
            dbResource.setIsHide(resource.getIsHide());
            dbResource.setIcon(resource.getIcon());
            dbResource.setDescription(resource.getDescription());
            dbResource.setUpdateTime(new Date());
            dbResource.setParent(resource.getParent());
            update(dbResource);
        }else{
            resource.setCreateTime(new Date());
            resource.setUpdateTime(new Date());
            save(resource);
        }
    }

    @Override
    public  void deleteById(Integer id){
        super.deleteById(id);
    }

}