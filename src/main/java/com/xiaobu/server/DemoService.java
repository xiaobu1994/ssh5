package com.xiaobu.server;


import com.xiaobu.entity.Book;

import javax.jws.WebService;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/23 9:01
 * @description V1.0  如要指定服务名称则接口和实现类的须一致@WebService(name="demo")
 */
@WebService
public interface DemoService {

    Book getBookById(int id);

    String insert();
}
