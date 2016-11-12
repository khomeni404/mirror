package com.mirror2.time.dao;

import com.mirror2.security.model.User;
import com.mirror2.time.model.Weekend;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Khomeni
 * Date: 11/3/14
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface WeekendDAO {
    public boolean save(Weekend dayOff);

    public boolean update(Weekend dayOff);

    public boolean delete(Weekend dayOff);

    public List<Weekend> findAllWeekend();

    public List<Weekend> findAllWeekend(Date from, Date to);

    public List<Weekend> findAllWeekend(User user, Date from, Date to);

    public Weekend getWeekend(User user, Date date);

    public Weekend getWeekend(Long id);
}
