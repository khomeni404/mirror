package com.mirror2.csd.dao;

import com.mirror2.csd.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class BuildingDaoImpl implements BuildingDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(Building building) {
        try {
            hibernateTemplate.persist(building);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(Building building) {
        hibernateTemplate.merge(building);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(Building building) {
        hibernateTemplate.delete(building);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<Building> findAllBuilding() {
        return hibernateTemplate.find("from Building");
    }

    @Override
    public Building getBuilding(Long id) {
        return hibernateTemplate.get(Building.class, id);
    }
}
