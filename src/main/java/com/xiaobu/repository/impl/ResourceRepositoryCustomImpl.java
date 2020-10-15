package com.xiaobu.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.xiaobu.base.repository.BaseRepositoryImpl;
import com.xiaobu.repository.ResourceRepositoryCustom;

/**
 * @author xiaobu
 * @date 2018-11-30 14:58:27
 * @description 这些实现类必须加 @Repository 否则回报没有注入bean 
 */
@Repository
@SuppressWarnings({"rawtypes","unchecked","all"})
public class ResourceRepositoryCustomImpl implements  ResourceRepositoryCustom{
    @Autowired
       @PersistenceContext
       private EntityManager entityManager;


}