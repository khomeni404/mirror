package com.mirror2.icm.dao;

import com.mirror2.icm.model.Unit;
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
public class UnitDaoImpl implements UnitDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(Unit unit) {
        try {
            hibernateTemplate.persist(unit);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(Unit unit) {
        hibernateTemplate.merge(unit);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(Unit unit) {
        hibernateTemplate.delete(unit);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<Unit> findAllUnit() {
        return hibernateTemplate.find("from Unit");
    }

    @Override
    public Unit getUnit(Long id) {
        return hibernateTemplate.get(Unit.class, id);
    }
}

