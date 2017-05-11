package com.mirror2.csd.dao;

import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.Installment;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class InstallmentDaoImpl implements InstallmentDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(Installment installment) {
        try {
            hibernateTemplate.persist(installment);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(Installment installment) {
        hibernateTemplate.merge(installment);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(Installment installment) {
        hibernateTemplate.delete(installment);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean deleteAllInstallment(Customer customer) {
        Object[] param = new Object[1];
        param[0] = customer;
        List<Installment> list =  hibernateTemplate.find("from Installment where customer=?", param);
        hibernateTemplate.deleteAll(list);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<Installment> findAllInstallment() {
        return hibernateTemplate.find("from Installment");
    }

    @Override
    public List<Installment> findAllInstallment(Customer customer) {
        Object[] param = new Object[1];
        param[0] = customer;
        return hibernateTemplate.find("from Installment where customer=?", param);
    }

    @Override
    public Installment getInstallment(Long id) {
        return hibernateTemplate.get(Installment.class, id);
    }
    @Override
    public Double getPayableInstallmentAmount(Customer customer) {
        try {
            DetachedCriteria dc = DetachedCriteria.forClass(Installment.class)
                    .setProjection(Projections.sum("amount"))
                    .add(Restrictions.eq("customer", customer))
                    .add(Restrictions.le("deadLine", new Date()));
            List result = hibernateTemplate.findByCriteria(dc);
            return CollectionUtils.isEmpty(result) ? 0.0 : (Double) result.get(0);
        } catch (Exception e) {
            return 0.0;
        }
    }
}
