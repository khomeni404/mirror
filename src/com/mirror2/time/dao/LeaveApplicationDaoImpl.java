package com.mirror2.time.dao;

import com.mirror2.security.model.User;
import com.mirror2.time.model.LeaveApplication;
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
public class LeaveApplicationDaoImpl implements LeaveApplicationDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(LeaveApplication application) {
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
    public boolean update(LeaveApplication application) {
        hibernateTemplate.merge(application);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(LeaveApplication application) {
        hibernateTemplate.delete(application);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<LeaveApplication> findAllLeaveApplication() {
        return hibernateTemplate.find("from LeaveApplication");
    }

    @Override
    public List<LeaveApplication> findAllLeaveApplication(Date date){
        Object[] paramArr = new Object[4];
        paramArr[0] = date;
        paramArr[1] = date;
        paramArr[2] = date;
        paramArr[3] = date;
        return hibernateTemplate.find("from LeaveApplication where (fromDate=?) or (fromDate<? and toDate>?) or (toDate=?)", paramArr);
    }

    @Override
    public List<LeaveApplication> findAllLeaveApplication(User user, Date start, Date end){
        Object[] paramArr = new Object[5];
        paramArr[0] = user;
        paramArr[1] = start;
        paramArr[2] = start;
        paramArr[3] = end;
        paramArr[4] = end;
        return hibernateTemplate.find("from LeaveApplication where appliedBy=? and ((fromDate=?) or (fromDate<? and toDate>?) or (toDate=?))", paramArr);
    }

    @Override
    public LeaveApplication getLeaveApplication(Long id) {
        return hibernateTemplate.get(LeaveApplication.class, id);
    }


}
