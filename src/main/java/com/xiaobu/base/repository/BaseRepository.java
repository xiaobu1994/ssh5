package com.xiaobu.base.repository;

import com.xiaobu.base.entity.vo.PageFinder;
import com.xiaobu.base.entity.vo.QueryBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/15 11:18
 * @description V1.0  集成了简单的CURD和分页
 */
@NoRepositoryBean
public interface BaseRepository<T extends Serializable, ID extends Serializable> extends JpaRepository<T, ID> {





    /**
     * @author xiaobu
     * @date 2018/11/15 17:47
     * @param hql  hql语句
     * @return T
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
     * @author xiaobu
     * @date 2018/11/16 9:44
     * @param hql hql语句
     * @return java.util.List<T>
     * @descprition 根据hql语句查询对象list集合
     * @version 1.0
     */
     List<T> findEntityListByHql(String hql);

     /**
      * @author xiaobu
      * @date 2018/11/16 9:56
      * @param hql hql语句
      * @return java.util.List
      * @descprition  用于查询非实体类的集合例如 vo
      * @version 1.0
      */
     List findByHql(String hql);



     /**
      * @author xiaobu
      * @date 2018/11/16 16:02
      * @param sql 原生sql
      * @return java.util.List<java.lang.Object[]>
      * @descprition   可用于查询自定义的vo
      * @version 1.0
      */
     List<Object[]> findListBySql(String sql);



     /**
      * @author xiaobu
      * @date 2018/11/16 16:38
      * @param hql hql语句
      * @return int
      * @descprition  应用hql语句统计个数
      * @version 1.0
      */
     int countTotalByHql(String hql);



     /**
      * @author xiaobu
      * @date 2018/11/16 16:52
      * @param sql sql语句
      * @return int
      * @descprition  应用sql语句统计个数
      * @version 1.0
      */
     int countTotalBySql(String sql);


    PageFinder<T> findPageListByHql(String hql, QueryBuilder queryBuilder);


    /**
     * @author xiaobu
     * @date 2018/11/19 10:19
     * @param c Class对象
     * @return int
     * @descprition  通过CriteriaBuilder获取总记录数
     * @version 1.0
     */
   int getCountByCriteriaBuilder(Class c);

   /**
    * @author xiaobu
    * @date 2018/11/20 15:23
    * @param c Class对象
    * @return java.util.List<T>
    * @descprition   通过CriteriaBuilder获取List
    * @version 1.0
    */
   List<T>  getEntityListByCriteriaBuilder(Class c);
}
