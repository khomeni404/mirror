package com.mirror2.common.service;/**
 * Created by Khomeni on 26-Apr-17.
 */

import com.mirror2.common.dao.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Khomeni
 *         Created on : 26-Apr-17
 */

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private CommonDAO commonDAO;

    @Override
    public boolean save(Object model) {
        return commonDAO.save(model);
    }

    @Override
    public boolean saveAll(List<?> modelList) {
        return false;
    }

    @Override
    public boolean update(Object model) {
        return false;
    }

    @Override
    public boolean updateAll(List<?> modelList) {
        return false;
    }

    @Override
    public boolean delete(Object model) {
        return false;
    }

    @Override
    public <MODEL> MODEL get(Class<MODEL> clazz, Long id) {
        return null;
    }

    @Override
    public <MODEL> MODEL get(Class<MODEL> clazz, String propertyName, Object propertyValue) {
        return null;
    }

    @Override
    public <MODEL> MODEL get(Class<MODEL> clazz, String propertyName1, Object propertyValue1, String propertyName2, Object propertyValue2) {
        return null;
    }

    @Override
    public <MODEL> MODEL get(Class<MODEL> clazz, String aliasModel, String propertyName, Object propertyValue) {
        return null;
    }

    @Override
    public <MODEL> MODEL get(Class<MODEL> clazz, Long id, Boolean active) {
        return null;
    }

    @Override
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz) {
        return commonDAO.findAll(clazz);
    }

    @Override
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, String[] idArray) {
        return commonDAO.findAll(clazz);
    }

    @Override
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, int start, int limit) {
        return commonDAO.findAll(clazz);
    }

    @Override
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, int start, int limit, List<String> projections) {
        return commonDAO.findAll(clazz);
    }

    @Override
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, Boolean active) {
        return commonDAO.findAll(clazz);
    }

    @Override
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, String propertyName, Object propertyValue) {
        return commonDAO.findAll(clazz);
    }

    @Override
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, String aliasModel, String propertyName, Object propertyValue) {
        return commonDAO.findAll(clazz);
    }

    @Override
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, Map<String, Object> propertyValueMap) {
        return commonDAO.findAll(clazz);
    }

    @Override
    public <MODEL> List<MODEL> searchBy_name(Class<MODEL> clazz, String text) {
        return commonDAO.findAll(clazz);
    }

    @Override
    public <MODEL> List<MODEL> searchBy_name_nameBN(Class<MODEL> clazz, String text) {
        return commonDAO.findAll(clazz);
    }

    @Override
    public Integer count(Class clazz) {
        return null;
    }

    @Override
    public Integer count(Class clazz, Boolean active) {
        return null;
    }
}
