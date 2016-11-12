package com.mirror2.csd.dao;

import com.mirror2.csd.model.Application;
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
public class ApplicationDaoImpl implements ApplicationDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(Application application) {
        try {
            hibernateTemplate.persist(application);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(Application application) {
        hibernateTemplate.merge(application);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(Application application) {
        hibernateTemplate.delete(application);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<Application> findAllApplication() {
        return hibernateTemplate.find("from Application");
    }

    @Override
    public Application getApplication(Long id) {
        return hibernateTemplate.get(Application.class, id);
    }
}
