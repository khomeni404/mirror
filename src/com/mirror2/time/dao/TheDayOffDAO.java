package com.mirror2.time.dao;

import com.mirror2.security.model.User;
import com.mirror2.time.model.AttendanceRegister;
import com.mirror2.time.model.TheDayOff;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Khomeni
 * Date: 11/3/14
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TheDayOffDAO {
    public boolean save(TheDayOff dayOff);

    public boolean update(TheDayOff dayOff);

    public boolean delete(TheDayOff dayOff);

    public List<TheDayOff> findAllTheDayOff();

    public List<TheDayOff> findAllTheDayOff(Date from, Date to);

    public List<TheDayOff> findAllTheDayOff(User user, Date from, Date to);

    public TheDayOff getTheDayOff(Long id);
}
