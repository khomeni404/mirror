package com.mirror2.csd.dao;

import com.mirror2.csd.model.Building;
import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.MoneyDisburse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class MoneyDisburseDaoImpl implements MoneyDisburseDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(MoneyDisburse moneyDisburse) {
        try {
            hibernateTemplate.persist(moneyDisburse);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(MoneyDisburse moneyDisburse) {
        hibernateTemplate.merge(moneyDisburse);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(MoneyDisburse moneyDisburse) {
        hibernateTemplate.delete(moneyDisburse);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<MoneyDisburse> findAllMoneyDisburse() {
        return hibernateTemplate.find("from MoneyDisburse");
    }

    @Override
    public List<MoneyDisburse> findAllMoneyDisburses(String findFor) {
        /*
        List<MoneyDisburse> moneyDisburses = null;
        if(findFor.toUpperCase().equals("NOT")){
            String arg = "Not";
            moneyDisburses =  hibernateTemplate.find("from MoneyDisburse where isUpdate=?", arg);
        } else if(findFor.toUpperCase().equals("ALL")){
            moneyDisburses = findAllMoneyDisburse();
        } else {

        }

        return moneyDisburses;
        */
        return findAllMoneyDisburse();
    }

    @Override
    public List<MoneyDisburse> findAllMoneyDisbursesSummary(String findFor) {
        return null;
    }

    @Override
    public MoneyDisburse getMoneyDisburse(Long id) {
        return hibernateTemplate.get(MoneyDisburse.class, id);
    }
}
