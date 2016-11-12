package com.mirror2.icm.dao;

import com.mirror2.icm.model.InternalRequisition;
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
public class InternalRequisitionDaoImpl implements InternalRequisitionDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(InternalRequisition internalRequisition) {
        try {
            hibernateTemplate.persist(internalRequisition);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(InternalRequisition internalRequisition) {
        hibernateTemplate.merge(internalRequisition);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(InternalRequisition internalRequisition) {
        hibernateTemplate.delete(internalRequisition);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<InternalRequisition> findAllInternalRequisition() {
        return hibernateTemplate.find("from InternalRequisition");
    }

    @Override
    public InternalRequisition getInternalRequisition(Long id) {
        return hibernateTemplate.get(InternalRequisition.class, id);
    }

}
