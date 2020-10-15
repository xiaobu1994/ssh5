package com.xiaobu.repository.impl;

import com.xiaobu.repository.AreaRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author xiaobu
 * @date 2018-11-20 17:29:39
 * @description V1.0  这些实现类必须加 @Repository 否则回报没有注入bean
 */
@Repository
@SuppressWarnings({"rawtypesked","all"})
public class AreaRepositoryCustomImpl implements AreaRepositoryCustom {
    @Autowired
       @PersistenceContext
       private EntityManager entityManager;


}