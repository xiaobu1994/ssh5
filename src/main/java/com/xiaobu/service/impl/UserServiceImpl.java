package com.xiaobu.service.impl;

import com.xiaobu.base.service.BaseServiceImpl;
import com.xiaobu.base.util.MD5Util;
import com.xiaobu.entity.Role;
import com.xiaobu.entity.User;
import com.xiaobu.repository.UserRepository;
import com.xiaobu.service.RoleService;
import com.xiaobu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public User findByUserName(String username) {
        return userRepository.findUnique("from User where userName=" + "'" + username + "'");
    }

    @Override
    public void saveOrUpdate(User user) {
        if (user.getId() != 0) {
            User dbUser = findById(user.getId());
            dbUser.setNickName(user.getNickName());
            dbUser.setSex(user.getSex());
            dbUser.setBirthday(user.getBirthday());
            dbUser.setTelephone(user.getTelephone());
            dbUser.setEmail(user.getEmail());
            dbUser.setAddress(user.getAddress());
            dbUser.setLocked(user.getLocked());
            dbUser.setDescription(user.getDescription());
            dbUser.setUpdateTime(new Date());
            userRepository.save(dbUser);

        } else {
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setPassword(MD5Util.getMD5Pwd(user.getPassword(), user.getUserName()));
            update(user);
        }
    }

    @Override
    public void grant(int id, String[] roleIds) {
        User user = findById(id);
        Assert.notNull(user, "用户不存在");
        //Assert.state(!"admin".equals(user.getUserName()),"超级管理员用户不能修改管理角色");
        Role role;
        Set<Role> roles = new HashSet<Role>();
        if (roleIds != null) {
            for (String roleId : roleIds) {
                Integer rid = Integer.parseInt(roleId);
                role = roleService.findById(rid);
                roles.add(role);
            }
        }
        user.setRoles(roles);
        update(user);
    }


    @Override
    public  void deleteById(Integer id){
        super.deleteById(id);
    }
}
