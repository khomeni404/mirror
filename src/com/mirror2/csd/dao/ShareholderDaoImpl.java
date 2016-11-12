package com.mirror2.csd.dao;

import com.mirror2.csd.model.Shareholder;
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
public class ShareholderDaoImpl implements ShareholderDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(Shareholder shareholder) {
        try {
            hibernateTemplate.persist(shareholder);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(Shareholder shareholder) {
        hibernateTemplate.merge(shareholder);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(Shareholder shareholder) {
        hibernateTemplate.delete(shareholder);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<Shareholder> findAllShareholder() {
        return hibernateTemplate.find("from Shareholder");
    }

    @Override
    public Shareholder getShareholder(Long id) {
        return hibernateTemplate.get(Shareholder.class, id);
    }
}
