package com.mirror2.time.dao;

import com.mirror2.security.model.User;
import com.mirror2.time.model.AttendanceRegister;
import com.mirror2.time.model.TheDayOff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Khomeni
 * Date: 11/3/14
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class TheDayOffDaoImpl implements TheDayOffDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(TheDayOff dayOff) {
        try {
            hibernateTemplate.persist(dayOff);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(TheDayOff dayOff) {
        hibernateTemplate.merge(dayOff);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(TheDayOff dayOff) {
        hibernateTemplate.delete(dayOff);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<TheDayOff> findAllTheDayOff() {
        return hibernateTemplate.find("from TheDayOff");
    }

    public List<TheDayOff> findAllTheDayOff(Date from, Date to){
        Object[] paramArr = new Object[2];
        paramArr[0] = from;
        paramArr[1] = to;
        return hibernateTemplate.find("from TheDayOff where date between ? and ?", paramArr);
    }

    public List<TheDayOff> findAllTheDayOff(User user, Date from, Date to){
        Object[] paramArr = new Object[3];
        paramArr[0] = user;
        paramArr[1] = from;
        paramArr[2] = to;
        return hibernateTemplate.find("from TheDayOff where employee=? and date between ? and ?", paramArr);
    }

    @Override
    public TheDayOff getTheDayOff(Long id) {
        return hibernateTemplate.get(TheDayOff.class, id);
    }


}
