package com.xiaobu.server;

import com.xiaobu.entity.Book;
import com.xiaobu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/23 9:03
 * @description V1.0  命名空间 默认为接口类所在包的逆序
 * 如要指定服务名称则接口和实现类的须一致@WebService(name="demo") @WebService(endpointInterface = "com.example.server.DemoService",serviceName = "demo")
 */
@WebService(endpointInterface = "com.xiaobu.server.DemoService")
@Component
public class DemoServiceImpl implements DemoService {
    @Autowired
    BookService bookService;
    @Override
    public Book getBookById(int id) {
        Book book = new Book();
        book.setName("webservice测试");
        book.setId(123);
        book.setAuthor("小布");
        return book;
    }

    @Override
    public String insert() {
        Book book = new Book();
        book.setAuthor("小布");
        book.setName("CXF webservice调用");
        bookService.save(book);
        return "插入成功";
    }
}
