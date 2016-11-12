package com.mirror2.time.dao;

import com.mirror2.security.model.User;
import com.mirror2.time.model.Weekend;
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
public class WeekendDaoImpl implements WeekendDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(Weekend weekend) {
        try {
            hibernateTemplate.persist(weekend);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(Weekend weekend) {
        hibernateTemplate.merge(weekend);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(Weekend weekend) {
        hibernateTemplate.delete(weekend);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<Weekend> findAllWeekend() {
        return hibernateTemplate.find("from Weekend");
    }

    public List<Weekend> findAllWeekend(Date from, Date to){
        Object[] paramArr = new Object[2];
        paramArr[0] = from;
        paramArr[1] = to;
        return hibernateTemplate.find("from Weekend where date between ? and ?", paramArr);
    }

    public List<Weekend> findAllWeekend(User user, Date from, Date to){
        Object[] paramArr = new Object[3];
        paramArr[0] = user;
        paramArr[1] = from;
        paramArr[2] = to;
        return hibernateTemplate.find("from Weekend where employee=? and date between ? and ?", paramArr);
    }

    public Weekend getWeekend(User user, Date date){
        Object[] paramArr = new Object[2];
        paramArr[0] = user;
        paramArr[1] = date;
        try {
            return (Weekend) hibernateTemplate.find("from Weekend where employee=? and date=?", paramArr).get(0);
        }catch (IndexOutOfBoundsException ex){
            return null;
        }
    }

    @Override
    public Weekend getWeekend(Long id) {
        return hibernateTemplate.get(Weekend.class, id);
    }


}
