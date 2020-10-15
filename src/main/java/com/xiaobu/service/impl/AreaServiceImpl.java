package com.xiaobu.service.impl;

import com.xiaobu.base.service.BaseServiceImpl;
import com.xiaobu.entity.Area;
import com.xiaobu.repository.AreaRepository;
import com.xiaobu.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaobu
 * @date 2018-11-20 17:29:39
 * @description V1.0 
 */
@Service
@Slf4j
public class AreaServiceImpl extends BaseServiceImpl<Area,Integer> implements AreaService {

    private static final Map<Integer, Area> DATABASES = new HashMap<>();
    @Autowired
    AreaRepository areaRepository;

    static {
        DATABASES.put(2, new Area(2, "u2", "d2"));
        DATABASES.put(3, new Area(3, "u3", "d3"));
        DATABASES.put(4, new Area(4, "u4", "d4"));
    }

    /**
     * @author xiaobu
     * @date 2018/11/21 9:46
     * @param id  id
     * @return com.example.entity.Area
     * @descprition   缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合
     * @ @Cacheable(value = "user", key = "#id",condition = "#id < 10")） 当条件为true才执行
     * @version 1.0
     */
    @Override
    @Cacheable(value = "area", key = "#id")
    public Area get(int id) {
       log.info("进入save方法");
        return  DATABASES.get(id);
    }

    /**
     * @author xiaobu
     * @date 2018/11/21 9:23
     * @param area area对象
     * @return com.example.entity.Area
     * @descprition   @CachePut(根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用)
     * @version 1.0
     */

    @CachePut(value = "area", key = "#area.id")
    @Override
    public Area saveOrUpdate(Area area) {
        DATABASES.put(area.getId(), area);
        log.info("进入 saveOrUpdate 方法");
        return area;
    }

    @CacheEvict(value = "area", key = "#id")
    @Override
    public void delete(int id) {
        DATABASES.remove(id);
        log.info("进入 delete 方法");
    }
}