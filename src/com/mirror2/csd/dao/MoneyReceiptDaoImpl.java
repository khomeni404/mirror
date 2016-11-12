package com.mirror2.csd.dao;

import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.MoneyReceipt;
import com.mirror2.security.model.User;
import com.mirror2.util.DateUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class MoneyReceiptDaoImpl implements MoneyReceiptDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(MoneyReceipt moneyReceipt) {
        try {
            hibernateTemplate.persist(moneyReceipt);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(MoneyReceipt moneyReceipt) {
        hibernateTemplate.merge(moneyReceipt);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(MoneyReceipt moneyReceipt) {
        hibernateTemplate.delete(moneyReceipt);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<MoneyReceipt> findAllMoneyReceipt() {
        return hibernateTemplate.find("from MoneyReceipt");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MoneyReceipt> findAllMoneyReceipt(Integer start, Integer limit) {
        List obj = hibernateTemplate.findByCriteria(DetachedCriteria.forClass(MoneyReceipt.class), start, limit);
        return (List<MoneyReceipt>) obj;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MoneyReceipt> findAllMoneyReceipt(Customer customer, Date from, Date to) {
        Object[] param = new Object[]{customer, from, to, "Passed"};

        return hibernateTemplate.find("FROM MoneyReceipt mr WHERE mr.customer = ? " +
                "and mr.cashDate between ? and ? AND status = ? ORDER BY mr.cashDate ASC", param);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> findAllMaxDatedMoneyReceipt(String type, Date from) {
        return
                hibernateTemplate.find("select mr.customer.id, mr.customer.CID, customer.name, customer.referenceBy.name, max(cashDate)" +
                        " FROM MoneyReceipt mr where  mr.customer.status != 'Refunded' " +
                        "and mr.status='Passed' group by mr.customer.id order by mr.customer.CID");

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> getCollectionDataModeWise(Date from, Date to) {
        Object[] param = new Object[]{from, to, "Passed"};
        return hibernateTemplate.find("select paymentMethod, count(*), sum(amount) FROM MoneyReceipt where cashDate between ? and ? AND status = ? group by paymentMethod", param);
    }


    @Override
    public List<MoneyReceipt> waitingMoneyReceiptForApprove() {
        return hibernateTemplate.find("FROM MoneyReceipt WHERE cashDate < SYSDATE() AND status='Not Passed' ORDER BY cashDate ASC");
    }


    @Override
    public MoneyReceipt getMoneyReceipt(Long id) {
        return hibernateTemplate.get(MoneyReceipt.class, id);
    }

    @Override
    public MoneyReceipt getMoneyReceipt(String mrNo) {
        Object[] obj = new Object[]{mrNo};
        try {
            return (MoneyReceipt) hibernateTemplate.find("from MoneyReceipt where receiptNo=?", obj).get(0);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<MoneyReceipt> getDepositedAmountByMR(Customer customer, String status) {
        Object[] obj = new Object[2];
        obj[0] = customer;
        obj[1] = status;

        return hibernateTemplate.find("from MoneyReceipt where customer=? and status=?", obj);
    }

}
