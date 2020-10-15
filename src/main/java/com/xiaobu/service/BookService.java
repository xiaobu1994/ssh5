package com.xiaobu.service;


import com.xiaobu.base.service.BaseService;
import com.xiaobu.entity.Book;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/13 11:14
 * @description V1.0
 */

public interface BookService extends BaseService<Book,Integer> {
    void deleteByIdWithHql(Integer id);

}
