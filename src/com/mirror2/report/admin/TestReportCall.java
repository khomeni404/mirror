package com.mirror2.report.admin;


import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import com.mirror2.report.admin.report.Report;
import com.mirror2.report.service.ReportService;
import com.mirror2.util.MirrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ayatullah213574 on 29/07/2015.
 */

@Controller
@RequestMapping("/report/")

public class TestReportCall {
    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/adminReportHome.erp", method = RequestMethod.GET)
    public ModelAndView printWelcome() {
        Map<String, Object> payHomeMap = new HashMap<String, Object>();
        payHomeMap.put("PageTitle", "Report Home");
        payHomeMap.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("/report/admin_report_home", payHomeMap);
    }

    @RequestMapping(value = "/viewReport.erp", method = RequestMethod.GET)
    public String viewReport(ModelMap modelMap, @RequestParam String reportName) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = Database.getConnection();
            statement = connection.createStatement();
            HashMap parameterMap = new HashMap();
            parameterMap.put("title", "Report Title Here");//sending the report title as a parameter.
            Report rpt = new Report(parameterMap, connection);
            rpt.setReportName("Director list"); //product list is the name of my jasper file.
            rpt.callReport(reportName);
            //rpt.callConnectionLessReport();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        modelMap.addAttribute("PageTitle", "Report Home") ;
        modelMap.addAttribute("DashboardLink", MirrorConstants.DASHBOARD_LINK)    ;

        return "/report/admin_report_home";
    }

    public static void main(String[] args) {
        showReport("04_Shareholder_Include_Director");
    }


    public static void showReport(String reportName) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = Database.getConnection();
            statement = connection.createStatement();
            HashMap parameterMap = new HashMap();
            parameterMap.put("title", "Report Title Here");//sending the report title as a parameter.
            Report rpt = new Report(parameterMap, connection);
            rpt.setReportName("Director list"); //product list is the name of my jasper file.
            rpt.callReport(reportName);
            //rpt.callConnectionLessReport();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
