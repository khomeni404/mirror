package com.mirror2.common.dao;

import com.google.gson.internal.Primitives;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 2016-10-20 for qrf
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Version : 1.0
 */

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class CommonDAOImpl implements CommonDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(Object model) {
        hibernateTemplate.persist(model);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean saveAll(List modelList) {
        /*modelList.forEach(model ->
                        CommonDAOImpl::save
        );*/
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(Object model) {
        hibernateTemplate.merge(model);
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean updateAll(List modelList) {
       /* modelList.stream().forEach(entity ->
                        hibernateTemplate.merge(entity)
        );*/
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(Object model) {
        hibernateTemplate.delete(model);
        return true;
    }

    @Override
    public <MODEL> MODEL get(Class<MODEL> clazz, Long id) {
        Object model = hibernateTemplate.get(clazz, id);
        return Primitives.wrap(clazz).cast(model);
    }

    @Override
    public <MODEL> MODEL get(Class<MODEL> clazz, String propertyName, Object propertyValue) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.eq(propertyName, propertyValue));
        try {
            Object model = hibernateTemplate.findByCriteria(criteria).get(0);
            return Primitives.wrap(clazz).cast(model);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    @Override
    public <MODEL> MODEL get(Class<MODEL> clazz, String propertyName1, Object propertyValue1, String propertyName2, Object propertyValue2) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.eq(propertyName1, propertyValue1))
                .add(Restrictions.eq(propertyName2, propertyValue2));
        try {
            Object model = hibernateTemplate.findByCriteria(criteria).get(0);
            return Primitives.wrap(clazz).cast(model);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> MODEL get(Class<MODEL> clazz, String aliasModel, String propertyName, Object propertyValue) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .createAlias(aliasModel, "model2")
                .add(Restrictions.eq("model2."+propertyName, propertyValue));
        try {
            Object model = hibernateTemplate.findByCriteria(criteria).get(0);
            return Primitives.wrap(clazz).cast(model);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public <MODEL> MODEL get(Class<MODEL> clazz, Long id, Boolean active) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.eq("id", id))
                .add(Restrictions.eq("active", active));
        try {
            Object model = hibernateTemplate.findByCriteria(criteria).get(0);
            return Primitives.wrap(clazz).cast(model);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }


    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        return (List<MODEL>) hibernateTemplate.findByCriteria(criteria);
    }


    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, String[] idArray) {
        List<String> integerList = Arrays.asList(idArray);
        List<Long> longList = Arrays.asList(12L, 13L);// TODO integerList.stream().map(NumberUtils::toLong).collect(Collectors.toList());
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.in("id", longList));
        return (List<MODEL>) hibernateTemplate.findByCriteria(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, int start, int limit) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        Criteria executableCriteria = criteria.getExecutableCriteria(hibernateTemplate.getSessionFactory().openSession());

        executableCriteria.setFirstResult(start);
        executableCriteria.setMaxResults(limit);
        return (List<MODEL>) executableCriteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, int start, int limit, List<String> projections) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        Criteria executableCriteria = criteria.getExecutableCriteria(hibernateTemplate.getSessionFactory().openSession());

        if (!CollectionUtils.isEmpty(projections)) {
            ProjectionList projectionList = Projections.projectionList();
            for (String property : projections) {
                projectionList.add(Projections.property(property), property);
            }
            executableCriteria.setProjection(projectionList);
        }

        executableCriteria.setResultTransformer(Transformers.aliasToBean(clazz));
        executableCriteria.setFirstResult(start);
        executableCriteria.setMaxResults(limit);
        return (List<MODEL>) executableCriteria.list();

    }



    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, Boolean active) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.eq("active", active));
        return (List<MODEL>) hibernateTemplate.findByCriteria(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, String propertyName, Object propertyValue) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.eq(propertyName, propertyValue));
        return (List<MODEL>) hibernateTemplate.findByCriteria(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, String aliasModel, String propertyName, Object propertyValue) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .createAlias(aliasModel, "model2")
                .add(Restrictions.eq("model2."+propertyName, propertyValue));
        return (List<MODEL>) hibernateTemplate.findByCriteria(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, Map<String, Object> propertyValueMap) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        for (String property : propertyValueMap.keySet()) {
            criteria.add(Restrictions.eq(property, propertyValueMap.get(property)));
        }
        return (List<MODEL>) hibernateTemplate.findByCriteria(criteria);
    }

    @Override
    public Object findAll(DetachedCriteria dc) {
        return hibernateTemplate.findByCriteria(dc);
    }

    @Override
    public Object findAll(String sql, Object[] param) {
        if (ArrayUtils.isEmpty(param)) {
            return hibernateTemplate.find(sql);
        } else {
            return hibernateTemplate.find(sql, param);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> searchBy_name(Class<MODEL> clazz, String text) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.like("name", text, MatchMode.ANYWHERE));
        return (List<MODEL>) hibernateTemplate.findByCriteria(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> searchBy_name_nameBN(Class<MODEL> clazz, String text) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.disjunction()
                                .add(
                                        Restrictions.or(
                                                Restrictions.like("name", text, MatchMode.ANYWHERE),
                                                Restrictions.like("nameBN", text, MatchMode.ANYWHERE)
                                        )
                                )
                );
        return (List<MODEL>) hibernateTemplate.findByCriteria(criteria);
    }



    @Override
    public Integer count(Class clazz) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .setProjection(Projections.rowCount());
        Object o = hibernateTemplate.findByCriteria(criteria).get(0);
        if (o == null) {
            return 0;
        } else {
            return Integer.valueOf(o.toString());
        }
    }

    @Override
    public Integer count(Class clazz, Boolean active) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.eq("active", active))
                .setProjection(Projections.rowCount());
        Object o = hibernateTemplate.findByCriteria(criteria).get(0);
        if (o == null) {
            return 0;
        } else {
            return Integer.valueOf(o.toString());
        }
    }
}
