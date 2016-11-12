package com.mirror2.csd.dao;

import com.mirror2.csd.model.CancellationApp;
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
public class CancellationAppDaoImpl implements CancellationAppDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(CancellationApp app) {
        try {
            hibernateTemplate.persist(app);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(CancellationApp app) {
        hibernateTemplate.merge(app);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(CancellationApp app) {
        hibernateTemplate.delete(app);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<CancellationApp> findAllCancellationApp() {
        return hibernateTemplate.find("from CancellationApp");
    }

    @Override
    public CancellationApp getCancellationApp(Long id) {
        return hibernateTemplate.get(CancellationApp.class, id);
    }
}
