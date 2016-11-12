package com.mirror2.csd.dao;

import com.mirror2.csd.model.IncentiveDist;
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
public class IncentiveDistDaoImpl implements IncentiveDistDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(IncentiveDist dist) {
        try {
            hibernateTemplate.persist(dist);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(IncentiveDist dist) {
        hibernateTemplate.merge(dist);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(IncentiveDist dist) {
        hibernateTemplate.delete(dist);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<IncentiveDist> findAllIncentiveDist() {
        return hibernateTemplate.find("from IncentiveDist");
    }

    @Override
    public List<IncentiveDist> findAllIncentiveDist(Integer status) {
        Object[] param = new Object[1];
        param[0] = status;
        return hibernateTemplate.find("from IncentiveDist where status=?", param);
    }

    @Override
    public IncentiveDist getIncentiveDist(Long id) {
        return hibernateTemplate.get(IncentiveDist.class, id);
    }


}
