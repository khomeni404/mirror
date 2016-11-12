package com.mirror2.time.service;

import com.mirror2.util.TheDates;
import com.mirror2.security.model.User;
import com.mirror2.security.service.UserDetailsService;
import com.mirror2.time.dao.LeaveApplicationDaoImpl;
import com.mirror2.time.model.LeaveApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Khomeni
 * Date: 11/3/14
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    LeaveApplicationDaoImpl leaveApplicationDao;
    @Autowired
    UserDetailsService userDetailsService;

    //Reporting
    public List<Map<String, Object>> getLeaveSummaryAll(Date from, Date to) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<User> users = userDetailsService.getAllUsers();
        for (User user : users) {
            list.add(getLeaveSummary(user, from, to));
        }
        return list;
    }

    public Map<String, Object> getLeaveSummary(User user, Date yearStart, Date yearEnd){
        Map<String, Object> map;
        int enjoyed, applied, waiting;
        TheDates td = new TheDates();
        List<LeaveApplication> applications = leaveApplicationDao.findAllLeaveApplication(user, yearStart, yearEnd);

        enjoyed = 0;
        applied = 0;
        waiting = 0;
        for (LeaveApplication application : applications) {
            if (application.getStatus() == 0) {

            } else if (application.getStatus() == 1) {

            } else if (application.getStatus() == 2) {
                applied += application.getDays();
            } else if (application.getStatus() == 3) {
                waiting += application.getDays();
            } else if (application.getStatus() == 4) {
                enjoyed += application.getDays();
            } else if (application.getStatus() == 5) {

            }
        }
        map = new HashMap<String, Object>();
        map.put("name", user.getToken().getName());
        map.put("attained", td.getCurrentMonthSerial() * 2);
        map.put("applied", applied);
        map.put("waiting", waiting);
        map.put("enjoyed", enjoyed);
        return map;
    }




    //Leave Application
    @Override
    public boolean save(LeaveApplication application) {
        return leaveApplicationDao.save(application);
    }

    @Override
    public boolean update(LeaveApplication application) {
        return leaveApplicationDao.update(application);
    }

    @Override
    public boolean delete(LeaveApplication application) {
        return leaveApplicationDao.delete(application);
    }

    @Override
    public List<LeaveApplication> findAllLeaveApplication() {
        return leaveApplicationDao.findAllLeaveApplication();
    }

    @Override
    public List<LeaveApplication> findAllLeaveApplication(Date date) {
        return leaveApplicationDao.findAllLeaveApplication(date);
    }

    @Override
    public List<LeaveApplication> findAllLeaveApplication(User user, Date start, Date end) {
        return leaveApplicationDao.findAllLeaveApplication(user, start, end);
    }

    @Override
    public LeaveApplication getLeaveApplication(Long id) {
        return leaveApplicationDao.getLeaveApplication(id);
    }

}
