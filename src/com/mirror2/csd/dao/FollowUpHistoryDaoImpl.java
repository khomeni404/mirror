//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mirror2.csd.dao;

import com.mirror2.csd.dao.FollowUpHistoryDAO;
import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.FollowUpHistory;

import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FollowUpHistoryDaoImpl implements FollowUpHistoryDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public FollowUpHistoryDaoImpl() {
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false
    )
    public boolean save(FollowUpHistory history) {
        try {
            this.hibernateTemplate.persist(history);
            this.hibernateTemplate.flush();
            return true;
        } catch (Exception var3) {
            return false;
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false
    )
    public boolean update(FollowUpHistory history) {
        this.hibernateTemplate.merge(history);
        this.hibernateTemplate.flush();
        return true;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false
    )
    public boolean delete(FollowUpHistory history) {
        this.hibernateTemplate.delete(history);
        this.hibernateTemplate.flush();
        return true;
    }

    public List<FollowUpHistory> findAllFollowUpHistory() {
        return this.hibernateTemplate.find("from FollowUpHistory");
    }

    public List<FollowUpHistory> findAllFollowUpHistory(Customer customer) {
        Object[] paramArr = new Object[]{customer};
        return this.hibernateTemplate.find("from FollowUpHistory where customer=?", paramArr);
    }

    public List<FollowUpHistory> findAllFollowUpHistory(Date date, String of) {
        Object[] paramArr = new Object[]{date};
        if (of.equals("missed")) {
              return  this.hibernateTemplate.find("from FollowUpHistory where attentionOn<? and active=true", paramArr);
        } else
            return of.equals("today") ? this.hibernateTemplate.find("from FollowUpHistory where attentionOn=?", paramArr) : (of.equals("after") ? this.hibernateTemplate.find("from FollowUpHistory where attentionOn>?", paramArr) : (of.equals("before") ? this.hibernateTemplate.find("from FollowUpHistory where attentionOn<?", paramArr) : this.hibernateTemplate.find("from FollowUpHistory where attentionOn=?", paramArr)));
    }

    public FollowUpHistory getFollowUpHistory(Long id) {
        return (FollowUpHistory) this.hibernateTemplate.get(FollowUpHistory.class, id);
    }

    public boolean setInactiveHistory(Customer customer) {
        Session session = this.hibernateTemplate.getSessionFactory().openSession();
        session.getTransaction().begin();
        SQLQuery query = session.createSQLQuery("update csd_follow_up_history set active = :active where customer_id = :customer");
        query.setParameter("active", Boolean.valueOf(false));
        query.setParameter("customer", customer.getId());
        int result = query.executeUpdate();
        session.getTransaction().commit();
        return true;
    }
}
