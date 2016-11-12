package com.mirror2.csd.dao;

import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.DisbursementLog;
import org.apache.commons.math3.util.Precision;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class DisbursementLogDaoImpl implements DisbursementLogDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(DisbursementLog log) {
        try {
            hibernateTemplate.persist(log);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(DisbursementLog log) {
        hibernateTemplate.merge(log);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(DisbursementLog log) {
        hibernateTemplate.delete(log);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<DisbursementLog> findAllDisbursementLog() {
        return hibernateTemplate.find("from DisbursementLog");
    }

    @Override
    public DisbursementLog getDisbursementLog(Long id) {
        return hibernateTemplate.get(DisbursementLog.class, id);
    }

    @Override
    public List<Object[]> getDisbursementLogReport(Date from, Date to) {
        List<Object[]> list = new ArrayList<Object[]>();
        Query queryInstTotal = null;
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        try {         //count(select inst2 from  Installment inst2 where inst2.deadLine < :today)
            queryInstTotal = session.createQuery(
                    "SELECT log.head, SUM(log.ODP), SUM(log.RDP), SUM(log.ADP) FROM DisbursementLog log where log.recordDate between :from_date and :to_date GROUP BY log.head"
                    // "select count(inst.id), sum(inst.amount) from Installment inst  where customer = :customer"
            );
            queryInstTotal.setParameter("from_date", from);
            queryInstTotal.setParameter("to_date", to);
            if (queryInstTotal != null) {
                list = queryInstTotal.list();
            }

            return list;

        } catch (Exception e) {
            return list;
        }
    }


}
