package com.mirror2.time;

import com.mirror2.csd.util.CurrentUserCredentials;
import com.mirror2.util.TheDates;
import com.mirror2.security.model.User;
import com.mirror2.security.service.UserDetailsService;
import com.mirror2.time.model.AttendanceRegister;
import com.mirror2.time.model.HolidayEntitlement;
import com.mirror2.time.model.LeaveApplication;
import com.mirror2.time.model.Weekend;
import com.mirror2.time.service.AttendanceService;
import com.mirror2.time.service.LeaveService;
import com.mirror2.util.MirrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Khomeni
 * Date: 11/3/14
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/time/")
public class AttendanceAndLeaveController {
    @Autowired
    AttendanceService attendanceService;

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    LeaveService leaveService;

    //Report Home
    @RequestMapping(method = RequestMethod.GET, value = "/timeHome.erp")
    public ModelAndView timeHome() {
        Map<String, Object> payHomeMap = new HashMap<String, Object>();
        payHomeMap.put("PageTitle", "Report Home");
        payHomeMap.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("time/report/time_home", payHomeMap);
    }

    //viewLeaveStatus
    @RequestMapping(method = RequestMethod.GET, value = "/viewLeaveSummary.erp")
    public ModelAndView viewLeaveSummary(@RequestParam("lookingFor") String year) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Leave Status");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        TheDates td = new TheDates();
        Date from = year.toUpperCase().equals("CY..") ? td.getFirstDayOfCurrentYear() : td.toDate(year + "-01-01");
        Date to = year.toUpperCase().equals("CY..") ? td.getLastDayOfCurrentYear() : td.toDate(year + "-12-31");

        List<Map<String, Object>> statusList = leaveService.getLeaveSummaryAll(from, to);
        map.put("statusList", statusList);
        return new ModelAndView("/time/report/leave_status_view", map);
    }

    //Attendance Status
    @RequestMapping(method = RequestMethod.GET, value = "/viewAttendanceSummary.erp")
    public ModelAndView viewAttendanceSummary(@RequestParam("lookingFor") String mmYY) {
        TheDates td = new TheDates();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Attendance Summary");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        Date[] dates = new TheDates().getFirstLastDate(mmYY);
        List<Map<String, Comparable>> statusList = attendanceService.findAllAttendanceSummary(dates[0], dates[1]);
        map.put("statusList", statusList);


        map.put("month", mmYY.toUpperCase().equals("CM..") ? td.getNameOfCurrentMonth() : td.getNameOfMonth(Integer.parseInt(mmYY.substring(0,2))));
        map.put("year", mmYY.toUpperCase().equals("CM..") ? td.getCurrentYear() : "20"+mmYY.substring(2));
        map.put("totalDaysOfMonth", td.getTotalDaysOfMonth(mmYY));
        map.put("pastDays", mmYY.toUpperCase().equals("CM..") ? td.getPastDaysOfCurrentDate() : td.getPastDaysOfMonthByMmYy(mmYY));


        List<HolidayEntitlement> holidays = attendanceService.findAllHolidayEntitlement(dates[0], dates[1]);
        map.put("holidays", holidays);
        return new ModelAndView("/time/report/attendance_status_view", map);
    }

    //createAttendanceRegister
    @RequestMapping(method = RequestMethod.GET, value = "/createAttendanceRegister.erp")
    public ModelAndView createAttendanceRegister() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Manual Attendance Create");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        return new ModelAndView("/time/attendance_register_create", map);
    }

    //Save AttendanceRegister
    @RequestMapping(method = RequestMethod.POST, value = "/saveAttendanceRegister.erp")
    public ModelAndView saveAttendanceRegister(@RequestParam("dateOf") String dateOf,
                                               @RequestParam("timeIn") String timeIn,
                                               @RequestParam("note") String note) {
        Long empId = new CurrentUserCredentials().authorizeId;
        TheDates td = new TheDates();
        AttendanceRegister register = new AttendanceRegister();
        User user = userDetailsService.getUser(empId);
        register.setEmployee(user);
        register.setDate(td.toDate(dateOf));
        try {
            register.setTimeIn(new SimpleDateFormat("HH:mm:ss")
                    .parse(timeIn));
        } catch (ParseException pe) {
            //TODO...
        }
        register.setEntryType("Manual");
        register.setApproved(false);
        register.setNote(note);

        List<AttendanceRegister> registers = attendanceService
                .getAttendanceRegisterList(td.toDate(dateOf), user);
        if (registers.size() == 0) {
            user.getRegisters().add(register);
            attendanceService.save(register);
        }
        return new ModelAndView("redirect:/time/attendanceRegisterList.erp?dateFor=Today");
    }

    //attendanceRegisterList
    @RequestMapping(method = RequestMethod.GET, value = "/attendanceRegisterList.erp")
    public ModelAndView attendanceRegisterList(@RequestParam("dateFor") String dateFor) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Attendance List");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        TheDates td = new TheDates();
        Date date = dateFor.toUpperCase().equals("TODAY") ? new Date() : td.toDate(dateFor);
        List<AttendanceRegister> registers = attendanceService.getAttendanceRegisterList(date);
        map.put("registers", registers);
        map.put("dateFor", dateFor);

        //TODO.. Need to create another method with One arg
        List<Weekend> weekends = attendanceService.findAllWeekend(date, date);
        map.put("weekends", weekends);

        List<LeaveApplication> applications = leaveService.findAllLeaveApplication(date);
        map.put("applications", applications);

        return new ModelAndView("/time/attendance_list", map);
    }

    //editAttendanceRegister
    @RequestMapping(method = RequestMethod.GET, value = "/editAttendanceRegister.erp")
    public ModelAndView editAttendanceRegister(@RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Leave Application Create");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        AttendanceRegister register = attendanceService.getAttendanceRegister(id);
        map.put("register", register);
        return new ModelAndView("/time/attendance_register_edit", map);
    }

    //updateAttendanceRegister
    @RequestMapping(method = RequestMethod.GET, value = "/updateAttendanceRegister.erp")
    public ModelAndView updateAttendanceRegister(@RequestParam("id") Long id) {

        AttendanceRegister register = attendanceService.getAttendanceRegister(id);
        register.setNote("(Actual: " + register.getTimeIn() + ")");
        register.setApproved(true);
        try {
            register.setTimeIn(new SimpleDateFormat("HH:mm:ss")
                    .parse("09:00:00"));
        } catch (ParseException pe) {
            //TODO...
        }
        attendanceService.update(register);
        return new ModelAndView("redirect:/time/attendanceRegisterList.erp?dateFor=" + register.getDate());
    }

    //leaveApplicationCreate
    @RequestMapping(method = RequestMethod.GET, value = "/createLeaveApplication.erp")
    public ModelAndView createLeaveApplication() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Leave Application Create");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        return new ModelAndView("/time/leave_application_create", map);
    }

    //saveLeaveApplication
    @RequestMapping(method = RequestMethod.POST, value = "/saveLeaveApplication.erp")
    public ModelAndView saveLeaveApplication(@RequestParam("days") Integer days,
                                             @RequestParam("fromDate") String fromDate,
                                             @RequestParam("issue") String issue,
                                             @RequestParam("phone") String phone,
                                             @RequestParam("location") String location,
                                             @RequestParam("leaveType") String leaveType) {
        Long appliedById = new CurrentUserCredentials().authorizeId;
        TheDates td = new TheDates();
        User user = userDetailsService.getUser(appliedById);
        Date from = td.toDate(fromDate);
        Date to = td.getDateAfterDay(fromDate, Integer.parseInt(days - 1 + ""));
        List<LeaveApplication> list
                = leaveService.findAllLeaveApplication(user,from,to);
        if(list.size() == 0){
            LeaveApplication application = new LeaveApplication();
            application.setAppliedBy(user);
            application.setAppliedOn(new Date());
            application.setDays(days);
            application.setFromDate(td.toDate(fromDate));
            application.setToDate(to);
            application.setIssue(issue);
            application.setStatus(0);
            application.setNote("");
            application.setLocationOnLeave(location);
            application.setPhoneOnLeave(phone);
            application.setLeaveType(leaveType);
            user.getApplications().add(application);
            leaveService.save(application);
        }
        return new ModelAndView("redirect:/time/leaveApplicationList.erp?status=0");
    }

    //leave Application List
    @RequestMapping(method = RequestMethod.GET, value = "/leaveApplicationList.erp")
    public ModelAndView leaveApplicationList(@RequestParam("status") int status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Leave App");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        List<LeaveApplication> applications = leaveService.findAllLeaveApplication();

        map.put("applications", applications);
        map.put("status", status);
        map.put("user", new CurrentUserCredentials().user);
        return new ModelAndView("/time/leave_application_list", map);
    }

    //viewLeaveApplication

    @RequestMapping(method = RequestMethod.GET, value = "/viewLeaveApplication.erp")
    public ModelAndView viewLeaveApplication(@RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Leave App");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        LeaveApplication application = leaveService.getLeaveApplication(id);
        map.put("app", application);
        map.put("user", new CurrentUserCredentials().user);
        return new ModelAndView("/time/leave_application", map);
    }

    //updateLeaveApplication
    @RequestMapping(method = RequestMethod.GET, value = "/updateLeaveApplication.erp")
    public ModelAndView updateLeaveApplication(@RequestParam("updateType") int updateType,
                                               @RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Leave Application Update");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);


        Long appliedById = new CurrentUserCredentials().authorizeId;
        LeaveApplication application = leaveService.getLeaveApplication(id);
        User user = userDetailsService.getUser(appliedById);
        application.setId(id);
        application.setStatus(updateType);
        if (updateType == 1) {
            application.setProxyBy(user);
        } else if (updateType == 2) {
            application.setAcceptedBy(user);
        } else if (updateType == 3) {
            application.setReceivedBy(user);
        } else if (updateType == 4) {
            application.setApprovedBy(user);
        } else if (updateType == 5) {
            application.setRejectBy(user);
        }
        if (!application.getAppliedBy().equals(user))
            leaveService.update(application);

        return new ModelAndView("redirect:/time/viewLeaveApplication.erp?id=" + id, map);
    }

    //deleteLeaveApplication
    @RequestMapping(method = RequestMethod.GET, value = "/deleteLeaveApplication.erp")
    public ModelAndView deleteLeaveApplication(@RequestParam("id") Long id) {

        LeaveApplication application = leaveService.getLeaveApplication(id);
        //if(application.getAppliedBy().getAuthorizeId() == new CurrentUserCredentials().authorizeId)
        application.getAppliedBy().getApplications().remove(application);
        leaveService.delete(application);

        return new ModelAndView("redirect:/time/leaveApplicationList.erp?status=0");
    }

    //createHolidayEntitlement
    @RequestMapping(method = RequestMethod.GET, value = "/createHolidayEntitlement.erp")
    public ModelAndView createHolidayEntitlement() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Holiday Entitlement Create");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        return new ModelAndView("/time/holiday_entitlement_create", map);
    }

    //Save HolidayEntitlement
    @RequestMapping(method = RequestMethod.POST, value = "/saveHolidayEntitlement.erp")
    public ModelAndView saveHolidayEntitlement(@RequestParam("days") Integer days,
                                               @RequestParam("dateFrom") String date,
                                               @RequestParam("note") String note,
                                               @RequestParam("holidayType") String holidayType) {
        TheDates td = new TheDates();
        HolidayEntitlement duplicate = attendanceService.getHolidayEntitlement(td.toDate(date));
        if (duplicate == null) {
            HolidayEntitlement holidayEntitlement = new HolidayEntitlement();
            holidayEntitlement.setDate(td.toDate(date));
            holidayEntitlement.setDays(days);
            holidayEntitlement.setNote(note);
            holidayEntitlement.setHolidayType(holidayType);
            attendanceService.save(holidayEntitlement);
        }

        return new ModelAndView("redirect:/time/viewAttendanceSummary.erp?lookingFor=CM..");
    }

    //createWeekend
    @RequestMapping(method = RequestMethod.GET, value = "/createWeekend.erp")
    public ModelAndView createWeekend() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Weekend Create");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        return new ModelAndView("time/weekend_create", map);
    }

    //saveWeekend
    @RequestMapping(method = RequestMethod.POST, value = "/saveWeekend.erp")
    public ModelAndView saveWeekend(@RequestParam("dateOf") String dateOf,
                                    @RequestParam("type") String type,
                                    @RequestParam("note") String note) {
        TheDates td = new TheDates();
        Date date = td.toDate(dateOf);
        User user = new CurrentUserCredentials().user;
        Weekend weekendDuplicate = attendanceService.getWeekend(user, date);

        if (weekendDuplicate != null) {
            return new ModelAndView("redirect:/time/weekendList.erp?lookingFor=CM..");
        } else {
            Weekend weekend = new Weekend();
            weekend.setDate(date);
            weekend.setNote(note);
            weekend.setStatus(note.toUpperCase().equals("GENERAL") ? "Yes" : "No");
            weekend.setType(type);
            weekend.setEmployee(user);
            attendanceService.save(weekend);
            return new ModelAndView("redirect:/time/weekendList.erp?lookingFor=CM..");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/weekendList.erp")
    public ModelAndView weekendList(@RequestParam("lookingFor") String mmYY) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Weekend List");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        Date[] dates = new TheDates().getFirstLastDate(mmYY);
        List<Map<String, Comparable>> weekends = attendanceService.getWeekendCongregation(dates[0], dates[1]);
        map.put("weekends", weekends);
        map.put("lookingFor", mmYY);
        //TODO...
        map.put("duration", "01-11-2014 - 30-11-2014");
        return new ModelAndView("/time/weekend_list", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/updateWeekend.erp")
    public ModelAndView updateWeekend(@RequestParam("id") Long id,
                                      @RequestParam("lookingFor") String lookingFor) {
        Weekend weekend = attendanceService.getWeekend(id);
        weekend.setId(id);
        weekend.setStatus("Yes");
        attendanceService.update(weekend);
        return new ModelAndView("redirect:/time/weekendList.erp?lookingFor=" + lookingFor);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteWeekend.erp")
    public ModelAndView deleteWeekend(@RequestParam("id") Long id,
                                      @RequestParam("lookingFor") String lookingFor) {
        Weekend weekend = attendanceService.getWeekend(id);
        attendanceService.delete(weekend);
        return new ModelAndView("redirect:/time/weekendList.erp?lookingFor=" + lookingFor);
    }
}
