package com.mirror2.time.dao;

import com.mirror2.icm.model.Item;
import com.mirror2.security.model.User;
import com.mirror2.time.model.AttendanceRegister;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Khomeni
 * Date: 11/3/14
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AttendanceRegisterDAO {
    public boolean save(AttendanceRegister register);

    public boolean update(AttendanceRegister register);

    public boolean delete(AttendanceRegister register);

    public List<AttendanceRegister> findAllAttendanceRegister();

    public AttendanceRegister getAttendanceRegister(Long id);

    public AttendanceRegister getAttendanceRegister(Date date, User user);

    public List<AttendanceRegister> getAttendanceRegisterList(Date date);

    public List<AttendanceRegister> getAttendanceRegisterList(Date date, User user);

    public List<AttendanceRegister> findAllAttendanceRegister(User user, Date from, Date to);
}
