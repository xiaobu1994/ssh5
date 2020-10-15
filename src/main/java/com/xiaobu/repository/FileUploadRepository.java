package com.xiaobu.repository;

import com.xiaobu.base.repository.BaseRepository;
import com.xiaobu.entity.FileUpload;
import org.springframework.stereotype.Repository;

/**
 * @author xiaobu
 * @date 2018-11-20 15:48:46
 * @description V1.0 
 */
@Repository
public interface FileUploadRepository extends BaseRepository<FileUpload,Integer> {

}