package com.xiaobu.repository;

import com.xiaobu.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tanhw119214  on  2018/6/12 17:36
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {





}
