package com.xiaobu.repository.impl;

import com.xiaobu.repository.FileUploadRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author xiaobu
 * @date 2018-11-20 15:48:46
 * @description V1.0 
 */
@Repository
@SuppressWarnings({"rawtypes","unchecked","all"})
public class FileUploadRepositoryCustomImpl implements FileUploadRepositoryCustom {
    @Autowired
       @PersistenceContext
       private EntityManager entityManager;


}