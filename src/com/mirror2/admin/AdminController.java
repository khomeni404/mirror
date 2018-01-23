package com.mirror2.admin;

//import javax.servlet.http.HttpSession;

import com.mirror2.admin.model.BoardMeeting;
import com.mirror2.admin.service.AdminService;
import com.mirror2.common.model.Badge;
import com.mirror2.csd.model.Director;
import com.mirror2.csd.model.MID;
import com.mirror2.csd.service.CsdService;
import com.mirror2.csd.util.OrdinalNumber;
import com.mirror2.security.service.BootStrap;
import com.mirror2.security.service.UserDetailsService;
import com.mirror2.util.MirrorConstants;
import com.mirror2.util.TheDates;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mirror2.security.service.AuthenticationAndAuthorizationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/admin/")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CsdService csdService;

    @RequestMapping(method = RequestMethod.GET, value = "/createBoardMeeting.erp")
    public ModelAndView createBoardMeeting() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Board Meeting");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        List<BoardMeeting> meetingList = adminService.findAllBoardMeeting();
        map.put("meetingList", meetingList);

        List<Director> directorList = adminService.findAllDirector();
        map.put("directorList", directorList);

        return new ModelAndView("/admin/board_meeting_create", map);

    }
    @RequestMapping(method = RequestMethod.GET, value = "/deleteBoardMeeting.erp")
    public ModelAndView deleteBoardMeeting(@RequestParam Long id) {
        BoardMeeting meeting = adminService.getBoardMeeting(id);
        adminService.delete(meeting);
        return new ModelAndView("redirect:/admin/createBoardMeeting.erp");

    }
    @RequestMapping(method = RequestMethod.POST, value = "/saveBoardMeeting.erp")
    public ModelAndView saveBoardMeeting(@RequestParam String remarks,
                                         @RequestParam int meetingName,
                                         @RequestParam String heldOn) {
        Date heldDate;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            heldDate = formatter.parse(heldOn);
        } catch (ParseException e) {
            return new ModelAndView("redirect:/admin/createBoardMeeting.erp");
        }

        BoardMeeting meeting = new BoardMeeting();
        meeting.setHeldOn(heldDate);
        meeting.setMeetingName(meetingName+OrdinalNumber.getOrdinalNumber(meetingName)+" Board Meeting");
        meeting.setRecordOn(new Date());
        meeting.setRemarks(remarks + " board of directors attended.");

        adminService.save(meeting);
        return new ModelAndView("redirect:/admin/createBoardMeeting.erp");

    }


    @RequestMapping(method = RequestMethod.GET, value = "/directorList.erp")
    public ModelAndView directorList(@RequestParam String type) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Director List");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        List<MID> directorList = csdService.findAllMID(type);
        map.put("directorList", directorList);

        return new ModelAndView("/admin/director_list", map);

    }


}
