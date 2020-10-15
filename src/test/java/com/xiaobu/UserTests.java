package com.xiaobu;

import com.xiaobu.entity.Resource;
import com.xiaobu.entity.User;
import com.xiaobu.service.ResourceService;
import com.xiaobu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

    @Autowired
    UserService userService;

    @Autowired
    ResourceService resourceService;

    @Test
    public void test(){
        Page<User> users = userService.findByPageable(PageRequest.of(0,10, Sort.by(Sort.Order.asc("id"))));
        System.out.println("users.getContent()："+users.getContent());
        for(User user:users.getContent()){
            System.out.println("user.getBirthday() = " + user.getBirthday());
        }
    }


    //JPA的分页起始页是0
    @Test
    public void findByPage(){
        Page<Resource> page = resourceService.findByPageable(PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));
        System.out.println("page = " + page.getContent());

        page=resourceService.findByPageable(PageRequest.of(1, 10, Sort.by(Sort.Order.asc("id"))));
        System.out.println("page = " + page.getContent());
    }


}
