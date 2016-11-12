package com.mirror2.time.service;

import com.mirror2.security.model.User;
import com.mirror2.time.model.AttendanceRegister;
import com.mirror2.time.model.HolidayEntitlement;
import com.mirror2.time.model.Weekend;

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
public interface AttendanceService {
    public boolean save(AttendanceRegister register);

    public boolean update(AttendanceRegister register);

    public boolean delete(AttendanceRegister register);

    public List<AttendanceRegister> findAllAttendanceRegister();

    public AttendanceRegister getAttendanceRegister(Long id);

    public AttendanceRegister getAttendanceRegister(Date date, User user);

    public List<AttendanceRegister> getAttendanceRegisterList(Date date);

    public List<AttendanceRegister> getAttendanceRegisterList(Date date, User user);

    public List<Map<String, Object>> findAllAttendanceSummary();

    public List<Map<String, Comparable>> findAllAttendanceSummary(Date from, Date to);

    public Map<String, Comparable> findAttendanceSummary(User user, Date from, Date to);



    //Holiday Entitlement
    public boolean save(HolidayEntitlement entitlement);

    public boolean update(HolidayEntitlement entitlement);

    public boolean delete(HolidayEntitlement entitlement);

    public List<HolidayEntitlement> findAllHolidayEntitlement();

    public int getMonthlyHolidayAll(String holidayType);

    public int getMonthlyHolidayTillDay(String holidayType);

    public List<HolidayEntitlement> findAllHolidayEntitlement(Date start, Date end);

    public List<HolidayEntitlement> findAllHolidayEntitlement(Date start, Date end, String holidayType);

    public List<HolidayEntitlement> findAllHolidayEntitlement(String year, Integer month, String holidayType);

    public HolidayEntitlement getHolidayEntitlement(Long id);

    public HolidayEntitlement getHolidayEntitlement(Date date);

    //Weekend
    public boolean save(Weekend dayOff);

    public boolean update(Weekend dayOff);

    public boolean delete(Weekend dayOff);

    public List<Weekend> findAllWeekend();

    public List<Weekend> findAllWeekend(Date from, Date to);

    public List<Weekend> findAllWeekend(User user, Date from, Date to);

    public Weekend getWeekend(User user, Date date);

    public Weekend getWeekend(Long id);

    public List<Map<String, Comparable>> getWeekendCongregation(Date start, Date end);


}
