package com.mirror2.report;

import com.google.gson.Gson;
import com.mirror2.csd.model.MID;
import com.mirror2.csd.service.CsdService;
import com.mirror2.report.service.ReportService;
import com.mirror2.util.DateUtil;
import com.mirror2.util.MirrorConstants;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 03/03/16
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 03/03/16
 * Version : 1.0
 */

@Controller
@RequestMapping("/report/")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private CsdService csdService;


    @RequestMapping(method = RequestMethod.GET, value = "/getCollectionDataAllYear.erp")
    public ModelAndView getCollectionDataAllYear() {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Collection by Year");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("dataList", reportService.getCollectionDataAllYear());
        return new ModelAndView("report/csd/collection_by_year", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getSalesByYear.erp")
    public ModelAndView getSalesByYear() {
        Date from = DateUtil.toDate("01/01/2011", "DD/MM/YYYY");
        Date to = DateUtil.toDate("31/12/2045", "DD/MM/YYYY");
        List dataList = reportService.getSalesByYear(from, to);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Payment Home");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("dataList", dataList);
        return new ModelAndView("report/csd/sales_by_year", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getSalesByMonth.erp")
    public ModelAndView getSalesByMonth() {
        Date from = DateUtil.toDate("01/01/2011", "DD/MM/YYYY");
        Date to = DateUtil.toDate("31/12/2045", "DD/MM/YYYY");
        List dataList = reportService.getSalesByMonth(from, to);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Payment Home");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("dataList", dataList);
        return new ModelAndView("report/csd/sales_by_month", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getSalesBySalesPersonType.erp")
    public ModelAndView getSalesBySalesPersonType() {
        Date from = DateUtil.toDate("01/01/2011", "DD/MM/YYYY");
        Date to = DateUtil.toDate("31/12/2045", "DD/MM/YYYY");
        List dataList = reportService.getSalesBySalesPersonType(from, to);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Payment Home");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("dataList", dataList);
        return new ModelAndView("report/csd/sales_by_sales_person_type", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getSalesBySalesPerson.erp")
    public ModelAndView getSalesBySalesPerson(@RequestParam String type, @RequestParam Integer limit) {
        Date from = DateUtil.toDate("01/01/2011", "DD/MM/YYYY");
        Date to = DateUtil.toDate("31/12/2045", "DD/MM/YYYY");
        if (limit == -1) {
            limit = 5000;
        }
        List dataList = reportService.getSalesBySalesPerson(from, to, type, limit);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Payment Home");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("dataList", dataList);
        return new ModelAndView("report/csd/sales_by_sales_person", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getSalesDataSalesPersonWise.erp")
    public ModelAndView getSalesDataSalesPersonWise(@RequestParam String type,
                                                    @RequestParam String from,
                                                    @RequestParam String to) {
        Date fromDate = from.equals("") ?  DateUtil.toDate("01/01/2011", "DD/MM/YYYY") :  DateUtil.toDate(from, "DD/MM/YYYY");
        Date toDate = DateUtil.toDate(to, "DD/MM/YYYY");

        List<Map<String, String>> dataList = reportService.getSalesDataSalesPersonWise(type, fromDate, toDate);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Sales Counter");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("dataList", dataList);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        map.put("FROM", sdf.format(fromDate));
        map.put("TO", sdf.format(toDate));
        map.put("TYPE",type);
        return new ModelAndView("report/csd/sales_data_sales_person_wise", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getSalesDataDetailsSalesPersonWise.erp")
    public ModelAndView getSalesDataDetailsSalesPersonWise(@RequestParam String type,
                                                    @RequestParam String from,
                                                    @RequestParam String to) {
        Date fromDate = from.equals("") ?  DateUtil.toDate("01/01/2011", "DD/MM/YYYY") :  DateUtil.toDate(from, "DD/MM/YYYY");
        Date toDate = DateUtil.toDate(to, "DD/MM/YYYY");

        List<Map<String, String>> dataList = reportService.getSalesDataDetailsSalesPersonWise(type, fromDate, toDate);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Sales Counter");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("dataList", dataList);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        map.put("FROM", fromDate/* sdf.format(fromDate)*/);
        map.put("TO", toDate);
        return new ModelAndView("report/csd/sales_data_details_sales_person_wise", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getSalesByBOD.erp")
    public ModelAndView getSalesByBOD(@RequestParam String from) {
        Date fromDate = DateUtil.toDate(from, "DD/MM/YYYY");
        Date to = DateUtil.toDate("31/12/2045", "DD/MM/YYYY");

        List dataList = reportService.getSalesByBOD(fromDate, to);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Payment Home");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("dataList", dataList);
        map.put("lastAgm", from);
        return new ModelAndView("report/csd/sales_by_bod", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getReferredCustomer.erp")
    public ModelAndView getAllReferredCustomer() {

        List<MID> identityList = csdService.findAllMID();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Payment Home");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("dataList", identityList);
        return new ModelAndView("report/csd/all_referred_customer", map);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/getSalesBySalesPersonExcel.erp")
    public ModelAndView getSalesBySalesPersonExcel(@RequestParam String type, @RequestParam Integer limit) {
        Date from = DateUtil.toDate("01/01/2011", "DD/MM/YYYY");
        Date to = DateUtil.toDate("31/12/2045", "DD/MM/YYYY");
        if (limit == -1) {
            limit = 5000;
        }
        reportService.getSalesBySalesPersonExcel(from, to, type, limit);
        List dataList = reportService.getSalesBySalesPerson(from, to, type, limit);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Payment Home");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("dataList", dataList);
        return new ModelAndView("report/csd/sales_by_sales_person", map);
    }


}
