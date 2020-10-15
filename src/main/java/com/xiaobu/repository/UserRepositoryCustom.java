package com.xiaobu.repository;

import com.xiaobu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tanhw119214  on  2018/6/12 17:36
 */

public interface UserRepositoryCustom extends JpaRepository<User, Integer> {

}
