package com.xiaobu.base.repository;

import com.xiaobu.base.entity.vo.PageFinder;
import com.xiaobu.base.entity.vo.QueryBuilder;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/15 11:53
 * @description V1.0
 */
//Spring Data JPA都是调用SimpleJpaRepository来创建实例
@SuppressWarnings({"rawtypes","unchecked","all"})
public class BaseRepositoryImpl<T extends Serializable, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;

    private Class<T> clazz;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.clazz = domainClass;
        this.entityManager = em;
    }



    @Override
    @SuppressWarnings("unchecked")
    public T findUnique(String hql) {
        Query query = entityManager.createQuery(hql);
        return (T) query.getSingleResult();
    }

    @Override
    public List<T> findEntityListBySql(String sql, Class c) {
        Query query = entityManager.createNativeQuery(sql, c);
        List<T> list = query.getResultList();
        entityManager.close();
        return list;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<T> findEntityListByHql(String hql) {
        Query query = entityManager.createQuery(hql);
        List list = query.getResultList();
        entityManager.close();
        return (List<T>) list;
    }

    @Override
    public List findByHql(String hql) {
        Query query = entityManager.createQuery(hql);
        List list = query.getResultList();
        entityManager.close();
        return list;
    }

    @Override
    public List<Object[]> findListBySql(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> list = query.getResultList();
        entityManager.close();
        return list;
    }

    @Override
    public int countTotalByHql(String hql) {
        Query query = entityManager.createQuery("select count(*) " + hql);
        int count = Integer.parseInt(query.getSingleResult().toString());
        entityManager.close();
        return count;
    }

    @Override
    public int countTotalBySql(String sql) {
        Query query = entityManager.createNativeQuery("select count(*) " + sql);
        int count = Integer.parseInt(query.getSingleResult().toString());
        entityManager.close();
        return count;
    }

    @Override
    public PageFinder<T> findPageListByHql(String hql, QueryBuilder queryBuilder) {
        //取得记录总数
        Query q = entityManager.createQuery("select count(*) " + hql);
        int count = Integer.parseInt(q.getSingleResult().toString());
        //初始化分页对象
        PageFinder<T> finder = new PageFinder<T>(queryBuilder.getPage(), queryBuilder.getPageSize(), count);
        //取得数据列表
        Query query = entityManager.createQuery( hql);
        query.setFirstResult((queryBuilder.getPage() - 1) * queryBuilder.getPageSize());
        query.setMaxResults(queryBuilder.getPageSize());
        finder.setData(query.getResultList());
        if (finder.getData().size() == 0) {
            finder.setData(null);
        }
        return finder;
    }

    @Override
    public int getCountByCriteriaBuilder(Class c) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> critQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = critQuery.from(c);
        critQuery.select(criteriaBuilder.countDistinct(root));
        return entityManager.createQuery(critQuery).getSingleResult().intValue();
    }

    @Override
    public List<T> getEntityListByCriteriaBuilder(Class c) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> critQuery = criteriaBuilder.createQuery(c);
        Root<T> root = critQuery.from(c);
        critQuery.select(root);
        return entityManager.createQuery(critQuery).getResultList();
    }


}

