package com.xiaobu.base.service;

import com.xiaobu.base.entity.vo.PageFinder;
import com.xiaobu.base.entity.vo.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/15 11:30
 * @description V1.0
 */
public interface BaseService<T extends Serializable, ID extends Serializable> {
    /**
     * @param id 主键
     * @return T
     * @author xiaobu
     * @date 2018/11/22 11:59
     * @descprition 根据id查找对象
     * @version 1.0
     */
    T findById(ID id);

    /**
     * @param var1 id
     * @return boolean
     * @author xiaobu
     * @date 2018/11/20 17:38
     * @descprition 判断这个id是否存在
     * @version 1.0
     */
    boolean existsById(ID var1);


    /**
     * @param t T对象
     * @author xiaobu
     * @date 2018/11/20 15:50
     * @descprition
     * @version 1.0
     */
    void save(T t);


    /**
     * @author xiaobu
     * @date 2018/11/29 14:57
     * @param t 对象
     * @descprition   修改
     * @version 1.0
     */
    void update(T t);

    /**
     * @param t 删除对象
     * @author xiaobu
     * @date 2018/11/20 15:00
     * @descprition
     * @version 1.0
     */
    void delete(T t);

    /**
     * @param id 主键id
     * @author xiaobu
     * @date 2018/11/20 15:02
     * @descprition
     * @version 1.0
     */
    void deleteById(ID id);

    /**
     * @return int
     * @author xiaobu
     * @date 2018/11/20 15:07
     * @descprition 获取总数
     * @version 1.0
     */
    int count();

    /**
     * @return java.util.List<T>
     * @author xiaobu
     * @date 2018/11/20 15:08
     * @descprition 查询所有的数据
     * @version 1.0
     */
    List<T> findAll();

    /**
     * @param param 实体类的属性名
     * @return java.util.List<T>
     * @author xiaobu
     * @date 2018/11/20 15:11
     * @descprition 根据某个实体类的属性名按正序排列查询
     * @version 1.0
     */
    List<T> findAllByOrderAsc(String param);


    /**
     * @param param 实体类的属性名
     * @return java.util.List<T>
     * @author xiaobu
     * @date 2018/11/20 15:14
     * @descprition 根据某个实体类的属性名按正序排列查询
     * @version 1.0
     */
    List<T> findAllByOrderDesc(String param);


    /**
     * @param pageable eg:  Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.desc("name")));
     * @return org.springframework.data.domain.Page<T>
     * @author xiaobu
     * @date 2018/11/20 15:17
     * @descprition 分页对象分页
     * @version 1.0
     */
    Page<T> findByPageable(Pageable pageable);


    /**
     * @author xiaobu
     * @date 2018/12/3 15:35
     * @param list, pageable, count]
     * @return org.springframework.data.domain.Page<T>
     * @descprition   条件查询
     * @version 1.0
     */
    Page<T> findByPageable(List<T> list,Pageable pageable,long count);


    /**
     * @param hql hql语句
     * @return T
     * @author xiaobu
     * @date 2018/11/15 17:47
     * @descprition
     * @version 1.0
     */
    T findUnique(String hql);

    /**
     * 查询多个属性
     * 返回List<Object>对象形式的List，Object为Class格式对象
     *
     * @param sql 原生SQL语句
     * @return List
     */
    List<T> findEntityListBySql(String sql, Class c);


    /**
     * @param hql hql语句
     * @return java.util.List<T>
     * @author xiaobu
     * @date 2018/11/16 9:44
     * @descprition 根据hql语句查询对象list集合
     * @version 1.0
     */
    List<T> findEntityListByHql(String hql);

    /**
     * @param hql hql语句
     * @return java.util.List
     * @author xiaobu
     * @date 2018/11/16 9:56
     * @descprition 用于查询非实体类的集合例如 vo
     * @version 1.0
     */
    List findByHql(String hql);


    /**
     * @param sql 原生sql
     * @return java.util.List<java.lang.Object               [               ]>
     * @author xiaobu
     * @date 2018/11/16 16:02
     * @descprition 可用于查询自定义的vo 需要cast一下
     * @version 1.0
     */
    List<Object[]> findListBySql(String sql);


    /**
     * @param hql hql语句
     * @return int
     * @author xiaobu
     * @date 2018/11/16 16:38
     * @descprition 应用hql语句统计个数
     * @version 1.0
     */
    int countTotalByHql(String hql);


    /**
     * @param sql sql语句
     * @return int
     * @author xiaobu
     * @date 2018/11/16 16:52
     * @descprition 应用sql语句统计个数
     * @version 1.0
     */
    int countTotalBySql(String sql);


    /**
     * @param c Class对象
     * @return int
     * @author xiaobu
     * @date 2018/11/19 10:19
     * @descprition 通过CriteriaBuilder获取总记录数
     * @version 1.0
     */
    int getCountByCriteriaBuilder(Class c);

    /**
     * @param c Class对象
     * @return java.util.List<T>
     * @author xiaobu
     * @date 2018/11/20 15:23
     * @descprition 通过CriteriaBuilder获取List
     * @version 1.0
     */
    List<T> getEntityListByCriteriaBuilder(Class c);


    /**
     * @param queryBuilder 分页查询对象
     * @return com.example.base.entity.vo.PageFinder<T>
     * @author xiaobu
     * @date 2018/11/19 13:47
     * @descprition
     * @version 1.0
     */
     PageFinder<T> findPageListByHql(QueryBuilder queryBuilder);

     /**
      * @author xiaobu
      * @date 2018/11/30 14:25
      * @param paramName name, paramValue value
      * @return java.util.List<T>
      * @descprition   模糊查询
      * @version 1.0
      */
     List<T> findListLikeByParam(String paramName,String paramValue);

    /**
     * @author xiaobu
     * @date 2018/11/30 14:25
     * @param paramName name, paramValue value
     * @return java.util.List<T>
     * @descprition  精确查询
     * @version 1.0
     */
     T findUniqueByParam(String paramName,String paramValue);

     /**
      * @author xiaobu
      * @date 2018/11/30 14:26
      * @param map hashMap
      * @return java.util.List<T>
      * @descprition  精确查询
      * @version 1.0
      */
     List<T>findListByMap(Map<String,String> map);
}
