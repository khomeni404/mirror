package com.mirror2.time.service;

import com.mirror2.util.TheDates;
import com.mirror2.security.model.User;
import com.mirror2.security.service.UserDetailsService;
import com.mirror2.time.dao.AttendanceRegisterDAO;
import com.mirror2.time.dao.HolidayEntitlementDAO;
import com.mirror2.time.dao.LeaveApplicationDaoImpl;
import com.mirror2.time.dao.WeekendDAO;
import com.mirror2.time.model.AttendanceRegister;
import com.mirror2.time.model.HolidayEntitlement;
import com.mirror2.time.model.Weekend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Khomeni
 * Date: 11/3/14
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceRegisterDAO attendanceRegisterDAO;
    @Autowired
    LeaveApplicationDaoImpl leaveApplicationDao;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    HolidayEntitlementDAO holidayEntitlementDAO;
    @Autowired
    LeaveService leaveService;
    @Autowired
    WeekendDAO weekendDAO;


    //Reporting
    @Override
    public List<Map<String, Comparable>> findAllAttendanceSummary(Date from, Date to) {
        List<Map<String, Comparable>> list = new ArrayList<Map<String, Comparable>>();

        List<User> users = userDetailsService.getAllUsers();
        for (User user : users) {
            Map<String, Comparable> map = findAttendanceSummary(user, from, to);
            map.put("name", user.getToken().getName());
            list.add(map);
        }
        return list;
    }

    @Override
    public Map<String, Comparable> findAttendanceSummary(User user, Date from, Date to) {
        Map<String, Comparable> map = new HashMap<String, Comparable>();
        TheDates td = new TheDates();
        int exact = 0;
        int within9to915 = 0;
        int within915to930 = 0;
        int after930 = 0;
        int totalAttendance = 0;
        List<AttendanceRegister> registers = attendanceRegisterDAO.findAllAttendanceRegister(user, from, to);
        Date in;
        try {
            for (AttendanceRegister register : registers) {
                in = register.getTimeIn();
                if (in.compareTo(new SimpleDateFormat("HH:mm:ss").parse("09:00:00")) < 0) {
                    exact++;
                } else if (in.compareTo(new SimpleDateFormat("HH:mm:ss").parse("09:00:01")) > 0
                        && in.compareTo(new SimpleDateFormat("HH:mm:ss").parse("09:15:00")) < 0) {
                    within9to915++;
                } else if (in.compareTo(new SimpleDateFormat("HH:mm:ss").parse("09:15:01")) > 0
                        && in.compareTo(new SimpleDateFormat("HH:mm:ss").parse("09:30:00")) < 0) {
                    within915to930++;
                } else if (in.compareTo(new SimpleDateFormat("HH:mm:ss").parse("09:30:01")) > 0) {
                    after930++;
                } //09:00:00 | 09:00:01 | 09:15:00 | 09:15:01 |

            }
        } catch (ParseException pe) {
            //TODO...
        }
        //map.put("name", user.getToken().getName());
        map.put("exact", exact);
        map.put("within9to915", within9to915);
        map.put("within915to930", within915to930);
        map.put("after930", after930);
        map.put("totalAttendance", exact+within9to915+within915to930+after930);
        map.put("absent", after930/3);
        map.put("publicHolidayAll", getMonthlyHolidayAll("Public"));
        map.put("publicHolidayTillToday", getMonthlyHolidayTillDay("Public"));
        map.put("leave", Integer.parseInt(leaveService.getLeaveSummary(user, td.getFirstDayOfYear(from), td.getLastDayOfYear(from)).get("enjoyed").toString()));
        map.put("dayOff", getTotalWeekend(user, from, to));
        return map;
    }

    public int getMonthlyHolidayAll(String holidayType){
        int total = 0;
        TheDates td = new TheDates();
        List<HolidayEntitlement> holidays = holidayEntitlementDAO
                .findAllHolidayEntitlement(td.getFirstDayOfMonth(), td.getLastDayOfMonth(), holidayType);

        if(holidays != null){
            for(HolidayEntitlement holidayEntitlement : holidays){
                total += holidayEntitlement.getDays();
            }
        }

        return total;
    }

    public int getMonthlyHolidayTillDay(String holidayType){

        int total = 0;
        TheDates td = new TheDates();
        List<HolidayEntitlement> holidays = holidayEntitlementDAO
                .findAllHolidayEntitlement(td.getFirstDayOfMonth(), new Date(), holidayType);

        if(holidays != null){
            for(HolidayEntitlement holidayEntitlement : holidays){
                total += holidayEntitlement.getDays();
            }
        }

        return total;
    }

    public int getTotalWeekend(User user, Date from, Date to){
        int total = 0;
        List<Weekend> list = findAllWeekend(user, from, to);
        for(Weekend tdo : list){
            if(tdo.getStatus().toUpperCase().equals("YES")){
                total++;
            }
        }
        return total;
    }

    public List<Map<String, Comparable>> getWeekendCongregation(Date start, Date end){
        List<Map<String, Comparable>> list = new ArrayList<Map<String, Comparable>>();
        Map<String, Comparable> map;
        List <User> users = userDetailsService.getAllUsers();
        for(User user : users){
            map = new HashMap<String, Comparable>();
            map.put("userName", user.getToken().getName());
            List<Weekend> weekends = findAllWeekend(user, start, end);
            int i = 1;
            for(Weekend weekend : weekends){
                map.put("id"+i, weekend.getId());
                map.put("date"+i, weekend.getDate());
                map.put("status"+i, weekend.getStatus());
                map.put("type"+i, weekend.getType());
                map.put("note"+i, weekend.getNote());
                i++;
            }
            for(int j = i; j <= 6; j++){
                map.put("id"+j, 0);
                map.put("date"+j, "---");
                map.put("status"+j, "---");
                map.put("type"+j, "---");
                map.put("note"+j, "---");
            }
            list.add(map);
        }
        return list;
    }




    //Attendance Register
    @Override
    public boolean save(AttendanceRegister register) {
        return attendanceRegisterDAO.save(register);
    }

    @Override
    public boolean update(AttendanceRegister register) {
        return attendanceRegisterDAO.update(register);
    }

    @Override
    public boolean delete(AttendanceRegister register) {
        return attendanceRegisterDAO.delete(register);
    }

    @Override
    public List<AttendanceRegister> findAllAttendanceRegister() {
        return attendanceRegisterDAO.findAllAttendanceRegister();
    }

    @Override
    public AttendanceRegister getAttendanceRegister(Long id) {
        return attendanceRegisterDAO.getAttendanceRegister(id);
    }


    @Override
    public AttendanceRegister getAttendanceRegister(Date date, User user) {
        return attendanceRegisterDAO.getAttendanceRegister(date, user);
    }

    @Override
    public List<AttendanceRegister> getAttendanceRegisterList(Date date) {
        return attendanceRegisterDAO.getAttendanceRegisterList(date);
    }

    @Override
    public List<AttendanceRegister> getAttendanceRegisterList(Date date, User user) {
        return attendanceRegisterDAO.getAttendanceRegisterList(date, user);
    }

    @Override
    public List<Map<String, Object>> findAllAttendanceSummary() {
        return null;
    }

    //Holiday Entitlement
    @Override
    public boolean save(HolidayEntitlement holidayEntitlement) {
        return holidayEntitlementDAO.save(holidayEntitlement);
    }

    @Override
    public boolean update(HolidayEntitlement holidayEntitlement) {
        return holidayEntitlementDAO.update(holidayEntitlement);
    }

    @Override
    public boolean delete(HolidayEntitlement holidayEntitlement) {
        return holidayEntitlementDAO.delete(holidayEntitlement);
    }

    @Override
    public List<HolidayEntitlement> findAllHolidayEntitlement() {
        return holidayEntitlementDAO.findAllHolidayEntitlement();
    }

    @Override
    public HolidayEntitlement getHolidayEntitlement(Long id) {
        return holidayEntitlementDAO.getHolidayEntitlement(id);
    }

    @Override
    public HolidayEntitlement getHolidayEntitlement(Date date) {
        return holidayEntitlementDAO.getHolidayEntitlement(date);
    }

    @Override
    public List<HolidayEntitlement> findAllHolidayEntitlement(Date start, Date end) {
        return holidayEntitlementDAO.findAllHolidayEntitlement(start, end);
    }

    @Override
    public List<HolidayEntitlement> findAllHolidayEntitlement(Date start, Date end, String holidayType) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<HolidayEntitlement> findAllHolidayEntitlement(String year, Integer month, String holidayType) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    //The Day Off
    @Override
    public boolean save(Weekend dayOff) {
        return weekendDAO.save(dayOff);
    }

    @Override
    public boolean update(Weekend dayOff) {
        return weekendDAO.update(dayOff);
    }

    @Override
    public boolean delete(Weekend dayOff) {
        return weekendDAO.delete(dayOff);
    }

    @Override
    public List<Weekend> findAllWeekend() {
        return weekendDAO.findAllWeekend();
    }

    @Override
    public List<Weekend> findAllWeekend(Date from, Date to) {
        return weekendDAO.findAllWeekend(from, to);
    }

    @Override
    public List<Weekend> findAllWeekend(User user, Date from, Date to) {
        return weekendDAO.findAllWeekend(user, from, to);
    }

    @Override
    public Weekend getWeekend(User user, Date date) {
        return weekendDAO.getWeekend(user, date);
    }

    @Override
    public Weekend getWeekend(Long id) {
        return weekendDAO.getWeekend(id);
    }
}

