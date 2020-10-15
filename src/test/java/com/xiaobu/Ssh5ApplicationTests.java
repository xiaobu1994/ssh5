package com.xiaobu;

import com.xiaobu.entity.Resource;
import com.xiaobu.service.ResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ssh5ApplicationTests {

    @Autowired
    ResourceService resourceService;
    @Test
    public void contextLoads() {
     Resource resource= resourceService.findUniqueByParam("name", "用户管理");
        System.out.println(resource);
    }

}
