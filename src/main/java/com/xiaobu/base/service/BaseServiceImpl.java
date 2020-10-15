package com.xiaobu.base.service;

import com.xiaobu.base.entity.vo.PageFinder;
import com.xiaobu.base.entity.vo.QueryBuilder;
import com.xiaobu.base.repository.BaseRepository;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/15 11:31
 * @description V1.0
 */
@SuppressWarnings("unchecked")
public abstract class BaseServiceImpl<T extends Serializable, ID extends Serializable> implements BaseService<T, ID> {

    @Autowired
    private BaseRepository<T, ID> baseRepository;

    private String getTName() {
        /*
         * this.getClass().getSuperClass()和this.getClass().getGenericSuperclass()的区别
         * 都是获得本类的父类，假设this.getClass()是test<T>
         * 则this.getClass().getSuperClass()的结果是Test
         * 则this.getClass().getGenericSuperclass()的结果是Test<T>
         */
        Type genType = getClass().getGenericSuperclass();
        //getActualTypeArguments()就是获取泛型参数的类型,比如Test<T>，最后就得到T类型
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Object entityClass = (Object) params[0];
        String name = entityClass.toString();
        int position = name.lastIndexOf(".");
        return name.substring(position + 1);
    }


    @Override
    public T findById(ID id) {
        Optional<T> optional = baseRepository.findById(id);
        return optional.get();
    }

    @Override
    public boolean existsById(ID var1) {
        return baseRepository.existsById(var1);
    }


    @Override
    public void save(T t) {
        baseRepository.save(t);
    }

    @Override
    public void update(T t) {
        baseRepository.saveAndFlush(t);
    }


    @Override
    public void delete(T t) {
        baseRepository.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        baseRepository.deleteById(id);
    }


    @Override
    public int count() {
        return (int) baseRepository.count();
    }


    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }


    @Override
    public List<T> findAllByOrderAsc(String param) {
        return baseRepository.findAll(Sort.by(Sort.Order.asc(param)));
    }


    @Override
    public List<T> findAllByOrderDesc(String param) {
        return baseRepository.findAll(Sort.by(Sort.Order.desc(param)));
    }


    @Override
    public Page<T> findByPageable(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }


    @Override
    public Page<T> findByPageable(List<T> list,Pageable pageable,long count) {
        return new PageImpl<T>(list, pageable, count);
    }


    @Override
    public T findUnique(String hql) {
        return baseRepository.findUnique(hql);
    }

    @Override
    public List<T> findEntityListBySql(String sql, Class c) {
        return baseRepository.findEntityListBySql(sql, c);
    }

    @Override
    public List<T> findEntityListByHql(String hql) {
        return baseRepository.findEntityListByHql(hql);
    }


    @Override
    public List findByHql(String hql) {
        return baseRepository.findByHql(hql);
    }

    @Override
    public List<Object[]> findListBySql(String sql) {
        return baseRepository.findListBySql(sql);
    }


    @Override
    public int countTotalByHql(String hql) {
        return baseRepository.countTotalByHql(hql);
    }

    @Override
    public int countTotalBySql(String sql) {
        return baseRepository.countTotalBySql(sql);
    }


    @Override
    public int getCountByCriteriaBuilder(Class c) {
        return baseRepository.getCountByCriteriaBuilder(c);
    }

    @Override
    public List<T> getEntityListByCriteriaBuilder(Class c) {
        return baseRepository.getEntityListByCriteriaBuilder(c);
    }

    @Override
    public PageFinder<T> findPageListByHql(QueryBuilder queryBuilder) {
        String tName = getTName();

        PageFinder<T> pageFinder = new PageFinder<T>();

        StringBuilder hql = new StringBuilder("from " + tName);
        hql.append(" o");
        //判断是否有map查询条件
        HashMap<String, String> map = queryBuilder.getMap();
        if (!map.isEmpty()) {
            Set<String> keys = queryBuilder.getMap().keySet();
            for (Map.Entry<String, String> mapEntry : map.entrySet()) {
                String key = mapEntry.getKey();
                String value = mapEntry.getValue();

                //判断当前字符串是否包含“where”，如果包含说明不止一个条件，要加关键字“and”
                if (hql.toString().contains("where o.")) {
                    hql.append(" and o.");
                } else {
                    hql.append(" where o.");
                }

                hql.append(key);
                //如果是模糊查询需要拼凑模糊查询语句
                if (queryBuilder.isLike()) {
                    hql.append(" like '%");
                    hql.append(value);
                    hql.append("%'");
                } else {
                    hql.append(" ='");
                    hql.append(value);
                    hql.append("'");
                }

            }
        }
        //判断是否有list查询条件
        if (!queryBuilder.getList().isEmpty()) {
            for (String list : queryBuilder.getList()) {
                //判断当前字符串是否包含“where”，如果包含说明不止一个条件，要加关键字“and”
                if (hql.toString().contains("where o.")) {
                    hql.append(" and o.");
                } else {
                    hql.append(" where o.");
                }
                hql.append(list);
            }
        }
        //判断是否有排序
        if (queryBuilder.getOrder() != null) {
            hql.append(" order by o.").append(queryBuilder.getOrder());
            if (queryBuilder.getIsAsc()) {
                hql.append(" asc");
            } else {
                hql.append(" desc");
            }
        }
        pageFinder = baseRepository.findPageListByHql(hql.toString(), queryBuilder);
        return pageFinder;
    }


    @Override
    public List<T> findListLikeByParam(String paramName,
                                       String paramValue) {
        paramName = StringEscapeUtils.escapeSql(paramName);
        paramValue = StringEscapeUtils.escapeSql(paramValue);
        String hql = "from " + getTName() +
                " o where o." +
                paramName +
                " like '%" +
                paramValue +
                "%'";
        return baseRepository.findByHql(hql);
    }


    @Override
    public T findUniqueByParam(String paramName,String paramValue){
        StringBuilder hql = new StringBuilder("from ");
        hql.append(getTName());
        String key = StringEscapeUtils.escapeSql(paramName);
        String value = StringEscapeUtils.escapeSql(paramValue);
        hql.append(" where ").append(key);
        hql.append(" = '");
        hql.append(value);
        hql.append("'");
        return baseRepository.findUnique(hql.toString());
    }

    @Override
    public List<T> findListByMap(Map<String, String> map) {
        StringBuilder hql = new StringBuilder("from ");
        hql.append(getTName());
        hql.append(" o");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = StringEscapeUtils.escapeSql(entry.getKey());
            String value = StringEscapeUtils.escapeSql(entry.getValue());
            //判断当前字符串是否包含“where”，如果包含说明不止一个条件，要加关键字“and”
            if (hql.toString().contains("where o.")) {
                hql.append(" and o.");
            } else {
                hql.append(" where o.");
            }
            hql.append(key);
            hql.append(" = '");
            hql.append(value);
            hql.append("'");
        }
        return baseRepository.findByHql(hql.toString());
    }
}
