package com.mirror2.csd.dao;

import com.mirror2.csd.model.OtherPayment;
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
public class OtherPaymentDaoImpl implements OtherPaymentDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(OtherPayment otherPayment) {
        try {
            hibernateTemplate.persist(otherPayment);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(OtherPayment otherPayment) {
        hibernateTemplate.merge(otherPayment);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(OtherPayment otherPayment) {
        hibernateTemplate.delete(otherPayment);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<OtherPayment> findAllOtherPayment() {
        return hibernateTemplate.find("from OtherPayment");
    }

    @Override
    public List<OtherPayment> findAllOtherPayment(String paymentType) {
        return hibernateTemplate.find("from OtherPayment where paymentType=?", paymentType);
    }

    @Override
    public OtherPayment getOtherPayment(Long id) {
        return hibernateTemplate.get(OtherPayment.class, id);
    }
}
