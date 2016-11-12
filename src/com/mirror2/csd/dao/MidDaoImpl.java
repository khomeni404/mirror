package com.mirror2.csd.dao;

import com.mirror2.csd.model.Building;
import com.mirror2.csd.model.MID;
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
public class MidDaoImpl implements MidDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(MID mid) {
        try {
            hibernateTemplate.persist(mid);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(MID mid) {
        hibernateTemplate.merge(mid);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(MID mid) {
        hibernateTemplate.delete(mid);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<MID> findAllMID() {
        return hibernateTemplate.find("from MID");
    }


    @Override
    public List<MID> findAllMID(String type) {
        Object[] obj = new Object[1];
        obj[0] = new String(type);
        return hibernateTemplate.find("from MID where type=?", obj);
    }

    @Override
    public MID getMID(Long id) {
        return hibernateTemplate.get(MID.class, id);
    }

    @Override
    public MID getMID(String mid) {
        Object[] obj = new Object[1];
        obj[0] = new String(mid);
        return (MID) hibernateTemplate.find("from MID where mid=?", obj).get(0);
    }
}
