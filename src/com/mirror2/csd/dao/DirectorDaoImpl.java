package com.mirror2.csd.dao;

import com.mirror2.csd.model.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: M Ayatullah
 * Date: 8/23/14
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class DirectorDaoImpl implements DirectorDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(Director director) {
        try {
            hibernateTemplate.persist(director);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(Director director) {
        hibernateTemplate.merge(director);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(Director director) {
        hibernateTemplate.delete(director);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<Director> findAllDirector() {
        return hibernateTemplate.find("from Director");
    }

    @Override
    public Director getDirector(Long id) {
        return hibernateTemplate.get(Director.class, id);
    }
}
