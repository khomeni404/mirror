package com.mirror2.time.dao;

import com.mirror2.security.model.User;
import com.mirror2.time.model.LeaveApplication;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Khomeni
 * Date: 11/3/14
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface LeaveApplicationDAO {
    public boolean save(LeaveApplication application);

    public boolean update(LeaveApplication application);

    public boolean delete(LeaveApplication application);

    public List<LeaveApplication> findAllLeaveApplication();

    public List<LeaveApplication> findAllLeaveApplication(Date date);

    public LeaveApplication getLeaveApplication(Long id);

    public List<LeaveApplication> findAllLeaveApplication(User user, Date start, Date end);

    //public List<LeaveApp> getLeaveApplicationList(Date date);

    //public List<LeaveApp> getLeaveApplicationList(Date date, User user);
}
