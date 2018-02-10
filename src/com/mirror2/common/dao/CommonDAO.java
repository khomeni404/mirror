package com.mirror2.common.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;
import java.util.Map;

/**
 * Created by Khomeni on 23-Feb-17.
 */
public interface CommonDAO {
    boolean save(Object model);

    boolean saveAll(List<?> modelList);

    boolean update(Object model);

    boolean updateAll(List<?> modelList);

    boolean delete(Object model);

    <MODEL> MODEL get(Class<MODEL> clazz, Long id);

    <MODEL> MODEL get(Class<MODEL> clazz, String propertyName, Object propertyValue);

    <MODEL> MODEL get(Class<MODEL> clazz, String propertyName1, Object propertyValue1, String propertyName2, Object propertyValue2);

    <MODEL> MODEL get(Class<MODEL> clazz, String aliasModel, String propertyName, Object propertyValue);

    <MODEL> MODEL get(Class<MODEL> clazz, Long id, Boolean active);


    <MODEL> List<MODEL> findAll(Class<MODEL> clazz);

    <MODEL> List<MODEL> findAll(Class<MODEL> clazz, String[] idArray);

    <MODEL> List<MODEL> findAll(Class<MODEL> clazz, int start, int limit);

    <MODEL> List<MODEL> findAll(Class<MODEL> clazz, int start, int limit, List<String> projections);

    <MODEL> List<MODEL> findAll(Class<MODEL> clazz, Boolean active);

    <MODEL> List<MODEL> findAll(Class<MODEL> clazz, String propertyName, Object propertyValue);

    <MODEL> List<MODEL> findAll(Class<MODEL> clazz, String aliasModel, String propertyName, Object propertyValue);

    <MODEL> List<MODEL> findAll(Class<MODEL> clazz, Map<String, Object> propertyValueMap);

    Object findAll(DetachedCriteria dc);

    Object findAll(String sql, Object[] param);

    <MODEL> List<MODEL> searchBy_name(Class<MODEL> clazz, String text);

    <MODEL> List<MODEL> searchBy_name_nameBN(Class<MODEL> clazz, String text);

    Integer count(Class clazz);

    Integer count(Class clazz, Boolean active);

    Object test();
}
