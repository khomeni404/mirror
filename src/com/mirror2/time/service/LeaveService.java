package com.mirror2.time.service;

import com.mirror2.security.model.User;
import com.mirror2.time.model.LeaveApplication;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Khomeni
 * Date: 11/3/14
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */
public interface LeaveService {
    //Leave Application
    public boolean save(LeaveApplication application);

    public boolean update(LeaveApplication application);

    public boolean delete(LeaveApplication application);

    public List<LeaveApplication> findAllLeaveApplication();

    public List<LeaveApplication> findAllLeaveApplication(Date date);

    public List<LeaveApplication> findAllLeaveApplication(User user, Date start, Date end);

    public LeaveApplication getLeaveApplication(Long id);

    //Reporting
    public List<Map<String, Object>> getLeaveSummaryAll(Date from, Date to);

    public Map<String, Object> getLeaveSummary(User user, Date from, Date to);
}
