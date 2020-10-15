package com.xiaobu.service;


import com.xiaobu.base.service.BaseService;
import com.xiaobu.entity.User;


public interface UserService extends BaseService<User,Integer> {
    /**
     * @author xiaobu
     * @date 2018/11/29 15:26
     * @param username 姓名
     * @return com.xiaobu.entity.User
     * @descprition  根据username查找用户
     * @version 1.0
     */
    User findByUserName(String username);

    /**
     * @author xiaobu
     * @date 2018/11/29 15:25
     * @param user 用户
     * @descprition  新增、修改
     * @version 1.0
     */
    void saveOrUpdate(User user);

    /**
     * @author xiaobu
     * @date 2018/11/29 15:23
     * @param id 用户id, roleIds 角色id数组
     * @descprition 把用户授予什么角色
     * @version 1.0
     */
    void grant(int id,String[] roleIds);

}
