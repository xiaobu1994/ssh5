package com.xiaobu.repository;

import com.xiaobu.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/20 10:33
 * @description V1.0 增加额外的方法
 */
public interface BookRepositoryCustom extends JpaRepository<Book, Integer> {


    void deleteByIdWithHql(Integer id);
}
