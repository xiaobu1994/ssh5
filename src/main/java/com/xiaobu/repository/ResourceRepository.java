package com.xiaobu.repository;

import com.xiaobu.entity.Resource;
import com.xiaobu.base.repository.BaseRepository;

/**
 * @author xiaobu
 * @date 2018-11-30 14:58:27
 * @description 这些实现类必须加 @Repository 否则回报没有注入bean 
 */
public interface ResourceRepository extends BaseRepository <Resource,Integer> {

}