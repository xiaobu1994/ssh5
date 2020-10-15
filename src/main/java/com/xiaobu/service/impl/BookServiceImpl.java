package com.xiaobu.service.impl;

import com.xiaobu.base.service.BaseServiceImpl;
import com.xiaobu.entity.Book;
import com.xiaobu.repository.BookRepository;
import com.xiaobu.repository.BookRepositoryCustom;
import com.xiaobu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/13 11:15
 * @description V1.0
 */
@Service
@SuppressWarnings({"rawtypes", "unchecked", "all"})
public class BookServiceImpl extends BaseServiceImpl<Book, Integer> implements BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookRepositoryCustom bookRepositoryCustom;

    @Override
    public void deleteByIdWithHql(Integer id) {
        bookRepositoryCustom.deleteByIdWithHql(id);
    }
}
