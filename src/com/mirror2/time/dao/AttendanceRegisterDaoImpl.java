package com.mirror2.time.dao;

import com.mirror2.icm.model.Item;
import com.mirror2.icm.model.Store;
import com.mirror2.security.model.User;
import com.mirror2.time.model.AttendanceRegister;
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
public class AttendanceRegisterDaoImpl implements AttendanceRegisterDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(AttendanceRegister register) {
        try {
            hibernateTemplate.persist(register);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(AttendanceRegister register) {
        hibernateTemplate.merge(register);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(AttendanceRegister register) {
        hibernateTemplate.delete(register);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<AttendanceRegister> findAllAttendanceRegister() {
        return hibernateTemplate.find("from AttendanceRegister");
    }

    @Override
    public AttendanceRegister getAttendanceRegister(Long id) {
        return hibernateTemplate.get(AttendanceRegister.class, id);
    }



    @Override
    public AttendanceRegister getAttendanceRegister(Date date, User user) {
        /*
        Object[] paramArr = new Object[1];
        paramArr[0] = new String(itemName);
        return (Item) hibernateTemplate.find("from Item where name=?", paramArr).get(0);
        */
        return null;
    }

    @Override
    public List<AttendanceRegister> getAttendanceRegisterList(Date date) {
        Object[] paramArr = new Object[1];
        paramArr[0] = date;
        return hibernateTemplate.find("from AttendanceRegister where date=?", paramArr);
    }

    @Override
    public List<AttendanceRegister> getAttendanceRegisterList(Date date, User user) {
        Object[] paramArr = new Object[2];
        paramArr[0] = date;
        paramArr[1] = user;
        return hibernateTemplate.find("from AttendanceRegister where date=? and employee=?", paramArr);
    }

    @Override
    public List<AttendanceRegister> findAllAttendanceRegister(User user, Date from, Date to){
        Object[] paramArr = new Object[3];
        paramArr[0] = user;
        paramArr[1] = from;
        paramArr[2] = to;
        return hibernateTemplate.find("from AttendanceRegister where employee=? and date between ? and ?", paramArr);
    }
}
