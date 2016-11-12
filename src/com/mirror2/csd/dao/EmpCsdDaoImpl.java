package com.mirror2.csd.dao;

import com.mirror2.csd.model.Building;
import com.mirror2.csd.model.EmpCsd;
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
public class EmpCsdDaoImpl implements EmpCsdDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(EmpCsd empCsd) {
        try {
            hibernateTemplate.persist(empCsd);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(EmpCsd empCsd) {
        hibernateTemplate.merge(empCsd);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(EmpCsd empCsd) {
        hibernateTemplate.delete(empCsd);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<EmpCsd> findAllEmpCsd() {
        return hibernateTemplate.find("from EmpCsd");
    }

    @Override
    public EmpCsd getEmpCsd(Long id) {
        return hibernateTemplate.get(EmpCsd.class, id);
    }
}
