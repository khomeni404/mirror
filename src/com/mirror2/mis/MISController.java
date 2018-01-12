package com.mirror2.mis;

import com.mirror2.common.dao.CommonDAO;
import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.Location;
import com.mirror2.csd.model.Offer;
import com.mirror2.csd.service.CsdService;
import com.mirror2.mis.bean.SearchBean;
import com.mirror2.mis.service.MISService;
import com.mirror2.report.service.ReportService;
import com.mirror2.security.SessionUtil;
import com.mirror2.security.model.User;
import com.mirror2.util.DateUtil;
import com.mirror2.util.MirrorConstants;
import com.mirror2.util.MirrorDataList;
import com.mirror2.util.MirrorUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.validator.GenericValidator;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 14/09/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 14/09/15
 * Version : 1.0
 */

@Controller
@RequestMapping("/mis/")
public class MISController {
    SimpleDateFormat sdf_1 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf_2 = new SimpleDateFormat("dd-MM-yyyy");

    @Autowired
    private MISService misService;

    @Autowired
    private CsdService csdService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private CommonDAO commonDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/home.erp")
    public ModelAndView home() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("DashBoardLink", "");
        map.put("PageTitle", "MIS Home");
        map.put("from", sdf_2.format(DateUtil.getFirstDayOfYear(new Date())));
        map.put("to", sdf_2.format(new Date()));
        String sql1 = "SELECT DISTINCT(b.handOver) FROM Building b ORDER BY b.handOver ASC";
        map.put("buildingHandoverYearList", commonDAO.findAll(sql1, null));
        String sql = "SELECT DISTINCT(YEAR(c.handoverDate)) FROM Customer c ORDER BY YEAR(c.handoverDate) ASC";
        map.put("handoverYearList", commonDAO.findAll(sql, null));
        map.put("offerList", commonDAO.findAll(Offer.class));
        map.put("locationList", commonDAO.findAll(Location.class));
        return new ModelAndView("/mis/home_mis", map);
    }

    @RequestMapping(value = "/test.erp", method = RequestMethod.GET)
    public
    @ResponseBody
    String test(@RequestParam Long id,
                HttpServletRequest request, HttpServletResponse response)
            throws JRException, IOException {

        Map<String, Object> params = new HashMap<String, Object>();
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        Map<String, String> map;
        int sl = 1;
        for (int i = 0; i < 15; i++) {
            map = new HashMap<String, String>();
            map.put("SL", sl + "");
            map.put("ITEM_CODE", "AA-" + i);
            map.put("ITEM_NAME", "BB-" + i);
            Double qty = 56.6;
            map.put("QTY", qty.intValue() + " PCS");
            Double amount = 5695.3;
            map.put("UNIT_PRICE", "100");// BigDecimal.valueOf(amount).toPlainString());
            map.put("SALES_PRICE", "200");// BigDecimal.valueOf(qty*amount).toPlainString());
            dataList.add(map);
            sl++;
        }
        params.put("ORDER_NO", "");
        params.put("DATE", "12/10/2016");
        params.put("CUST_NAME", "");
        params.put("TOTAL_AMT", "");
        params.put("TOTAL_AMT_WORD", "" + " TAKA ONLY");
        params.put("PAID_AMT", "");//  SoftUtil.toJasperAmt(ledger.getPaidTotal()));
        params.put("DUE_AMT", "");// SoftUtil.toJasperAmt(ledger.getDueTotal()));
        params.put("SALES_MAN", "");
        params.put("LOGO", misService.getRealPath("/") + "qrf_logo_big.png");
        JRDataSource dataSource = new JRBeanCollectionDataSource(dataList);
        try {
            params.put("format", "pdf");
            params.put("fileName", "sales_order_1");
            ByteArrayOutputStream byteArrayOutputStream = misService.generateReport(response, params, dataSource);
            response.setContentLength(byteArrayOutputStream.size());
            response.getOutputStream().write(byteArrayOutputStream.toByteArray());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @RequestMapping(value = "/getSalesDataSalesPersonWise.erp", method = RequestMethod.GET)
    public
    @ResponseBody
    String getSalesDataSalesPersonWise(@RequestParam String type,
                                       @RequestParam String from,
                                       @RequestParam String to,
                                       HttpServletRequest request, HttpServletResponse response)
            throws JRException, IOException {

        Map<String, Object> params = new HashMap<String, Object>();
        Date fromDate = from.equals("") ? DateUtil.toDate("01/01/2011", "DD/MM/YYYY") : DateUtil.toDate(from, "DD/MM/YYYY");
        Date toDate = DateUtil.toDate(to, "DD/MM/YYYY");

        List<Map<String, String>> dataList = reportService.getSalesDataSalesPersonWise(type, fromDate, toDate);

        params.put("FROM", from);
        params.put("TO", to);
        params.put("FOR", type);
        params.put("TOTAL_AMT", "");
        params.put("LOGO", misService.getRealPath("/") + "dp.png");
        JRDataSource dataSource = new JRBeanCollectionDataSource(dataList);
        try {
            params.put("format", "pdf");
            params.put("fileName", "report_26");
            ByteArrayOutputStream byteArrayOutputStream = misService.generateReport(response, params, dataSource);
            response.setContentLength(byteArrayOutputStream.size());
            response.getOutputStream().write(byteArrayOutputStream.toByteArray());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @RequestMapping(value = "/getCustomerOfferWise.erp", method = RequestMethod.GET)
    public
    @ResponseBody
    String getCustomerOfferWise(@RequestParam(required = false) Long offerId,
                                @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date bookingDateFrom,
                                @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date bookingDateTo,
                                HttpServletRequest request, HttpServletResponse response)
            throws JRException, IOException {

        Map<String, Object> params = new HashMap<String, Object>();
        Offer offer = commonDAO.get(Offer.class, offerId);

        List<Map<String, String>> dataList = misService.getCustomerOfferWise(offerId, bookingDateFrom, bookingDateTo);

//        params.put("FROM", sdf_1.format(bookingDateFrom));
//        params.put("TO", sdf_1.format(bookingDateTo));
        params.put("OFFER_NAME", offer.getOfferName());
        params.put("OFFER_DEAD_LINE", offer.getDeadLine());
        params.put("OFFER_DESCRIPTION", offer.getOfferDescription());
        params.put("TAKEN_ON", sdf_2.format(new Date()));
        params.put("LOGO", misService.getRealPath("/") + "dp.png");
        JRDataSource dataSource = new JRBeanCollectionDataSource(dataList);
        try {
            params.put("format", "pdf");
            params.put("fileName", "report_25");
            ByteArrayOutputStream byteArrayOutputStream = misService.generateReport(response, params, dataSource);
            response.setContentLength(byteArrayOutputStream.size());
            response.getOutputStream().write(byteArrayOutputStream.toByteArray());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @RequestMapping(value = "/getCustomerListCustomized.erp", method = RequestMethod.POST)
    public
    @ResponseBody
    String getCustomerListCustomized(@ModelAttribute SearchBean searchBean,
                                         HttpServletRequest request, HttpServletResponse response)
            throws JRException, IOException {

        Map<String, Object> params = new HashMap<String, Object>();

        List<Map<String, String>> dataList = misService.getCustomerListCustomized(searchBean);

        String handoverYear = searchBean.getHandoverYear();
        String payMode = searchBean.getPayMode();
        Long offerId = searchBean.getOfferId();
        Integer floorSize = searchBean.getFloorSize();
        params.put("HANDOVER_YEAR", GenericValidator.isBlankOrNull(handoverYear) ? "All year" : handoverYear);
        params.put("PAY_MODE", GenericValidator.isBlankOrNull(payMode) ? "All Mode" : payMode);
        Offer offer = null;
        if (offerId != null) {
            offer = commonDAO.get(Offer.class, offerId);
        }
        params.put("OFFER_NAME", offer == null ? "All Offer" : offer.getOfferName());
        params.put("FLOOR_SIZE", floorSize == null ? "All Sizes" : String.valueOf(floorSize));
        params.put("REPORT_DATE", sdf_2.format(new Date()));
        params.put("STATUS", GenericValidator.isBlankOrNull(searchBean.getBookingStatus()) ? "All Status": searchBean.getBookingStatus());
        params.put("B_HANDOVER", GenericValidator.isBlankOrNull(searchBean.getBuildingHandover()) ? "All Year": searchBean.getBuildingHandover());
        params.put("LOGO", misService.getRealPath("/") + "dp.png");
        JRDataSource dataSource = new JRBeanCollectionDataSource(dataList);
        try {
            params.put("format", "pdf");
            String fileName = searchBean.getReportFileName();
            params.put("fileName", GenericValidator.isBlankOrNull(fileName) ? "report_27_2" : fileName);

            ByteArrayOutputStream byteArrayOutputStream = misService.generateReport(response, params, dataSource);
            response.setContentLength(byteArrayOutputStream.size());
            response.getOutputStream().write(byteArrayOutputStream.toByteArray());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/showPaymentStatement.erp", method = RequestMethod.GET)
    public
    @ResponseBody
    String showPaymentStatement(@RequestParam String cid,
                                @RequestParam String from,
                                @RequestParam String to,
                                HttpServletRequest request, HttpServletResponse response)
            throws JRException, IOException {
        cid = MirrorUtil.makeCid(cid);
        Map<String, Object> params = new HashMap<String, Object>();
        Date fromDate = from.equals("") ? DateUtil.toDate("01/01/2011", "DD-MM-YYYY") : DateUtil.toDate(from, "DD-MM-YYYY");
        Date toDate = to.equals("") ? new Date() : DateUtil.toDate(to, "DD-MM-YYYY");
        Customer customer = csdService.getCustomer(cid);
        Object[] dataArray = misService.getPaymentStatementDataList(customer, fromDate, toDate);
        @SuppressWarnings("unchecked")
        List<Map<String, String>> dataList = (List<Map<String, String>>) dataArray[0];
        String total = (String) dataArray[1];
        String totalInWord = (String) dataArray[2];

        params.put("FROM", sdf_2.format(fromDate));
        params.put("TO", sdf_2.format(toDate));
        params.put("NAME", customer.getName());
        params.put("CID", cid);
        params.put("CELL", customer.getCellPhone());
        params.put("ADDRESS", customer.getMailingAddress());
        params.put("TOTAL_AMT", total);
        params.put("TOTAL_AMT_WORD", totalInWord.toUpperCase() + " TAKA ONLY.");
        params.put("LOGO", misService.getRealPath("/") + "dp.png");
        User user = SessionUtil.getSessionUser();
        params.put("USER", user.getToken().getName());
        params.put("DEG", user.getToken().getDesignation() + ", " + user.getToken().getDepartment());

        JRDataSource dataSource = new JRBeanCollectionDataSource(dataList); //*222#
        try {
            params.put("format", "pdf");
            params.put("fileName", "report_12");
            ByteArrayOutputStream byteArrayOutputStream = misService.generateReport(response, params, dataSource);
            response.setContentLength(byteArrayOutputStream.size());
            response.getOutputStream().write(byteArrayOutputStream.toByteArray());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    //

    @RequestMapping(value = "/ShowCustomerListAsPerLastPayment1.erp", method = RequestMethod.POST)
    public ModelAndView ShowCustomerListAsPerLastPayment1(@RequestParam Integer month,
                                                          @RequestParam String type,
                                                          HttpServletRequest request, HttpServletResponse response)
            throws JRException, IOException {
        Date targetDate = DateUtil.getDateBeforeDay(new Date(), month * 30);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Collection by Year");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        List<Map<String, String>> dataList = misService.getCustomerDataListAsPerLastPayment(type, targetDate);
        map.put("dataList", dataList);
        return new ModelAndView("/report/csd/customer_by_last_payment", map);
    }

    @RequestMapping(value = "/report_13.erp", method = RequestMethod.POST)
    public
    @ResponseBody
    String report_13(@RequestParam Integer month,
                     @RequestParam String type,
                     HttpServletRequest request, HttpServletResponse response)
            throws JRException, IOException {
        Date targetDate = DateUtil.getDateBeforeDay(new Date(), month * 30);
        Map<String, Object> params = new HashMap<String, Object>();
        List<Map<String, String>> dataList = misService.getCustomerDataListAsPerLastPayment(type, targetDate);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        params.put("TARGET_DATE", sdf.format(targetDate));
        params.put("TYPE", type);
        params.put("LOGO", misService.getRealPath("/") + "dp.png");
        params.put("TOTAL", String.valueOf(dataList.size()));
        User user = SessionUtil.getSessionUser();
        params.put("USER", user.getToken().getName());
        params.put("DEG", user.getToken().getDesignation() + ", " + user.getToken().getDepartment());

        JRDataSource dataSource = new JRBeanCollectionDataSource(dataList); //*222#
        try {
            params.put("format", "pdf");
            params.put("fileName", "report_13");
            ByteArrayOutputStream byteArrayOutputStream = misService.generateReport(response, params, dataSource);
            response.setContentLength(byteArrayOutputStream.size());
            response.getOutputStream().write(byteArrayOutputStream.toByteArray());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/showCollectionReport_16.erp")
    @SuppressWarnings("unchecked")
    public ModelAndView showCollectionReport_16(
            @RequestParam String from,
            @RequestParam String to) {
        Date fromDate = from.equals("") ? DateUtil.toDate("01/01/2011", "DD-MM-YYYY") : DateUtil.toDate(from, "DD-MM-YYYY");
        Date toDate = to.equals("") ? new Date() : DateUtil.toDate(to, "DD-MM-YYYY");
        Object[] dataArray = misService.getCollectionModeWise(fromDate, toDate);
        List<Map<String, String>> dataList = (List<Map<String, String>>) dataArray[0];

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Sales Counter");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("dataList", dataList);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        map.put("FROM", sdf.format(fromDate));
        map.put("TO", sdf.format(toDate));
        return new ModelAndView("/mis/collection_mode_wise", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getSalesDataSalesPersonWise2.erp")
    public ModelAndView getSalesDataSalesPersonWise2(@RequestParam String type,
                                                     @RequestParam String from,
                                                     @RequestParam String to) {
        Date fromDate = from.equals("") ? DateUtil.toDate("01/01/2011", "DD/MM/YYYY") : DateUtil.toDate(from, "DD/MM/YYYY");
        Date toDate = DateUtil.toDate(to, "DD/MM/YYYY");

        List<Map<String, String>> dataList = reportService.getSalesDataSalesPersonWise(type, fromDate, toDate);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Sales Counter");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("dataList", dataList);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        map.put("FROM", sdf.format(fromDate));
        map.put("TO", sdf.format(toDate));
        return new ModelAndView("report/csd/sales_data_sales_person_wise", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customerListByLocation.erp")
    public ModelAndView customerListByLocation(@RequestParam("location") String location) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Customer");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        Long locationId = null;
        if (!GenericValidator.isBlankOrNull(location)) {
            String locId = location.split(":")[0].trim();
            locationId = NumberUtils.isDigits(locId) ? Long.valueOf(locId) : null;
        }
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
                /*criteria.setProjection(Projections.projectionList()
                                .add(Projections.property("CID"))
                                .add(Projections.property("name"))
                                .add(Projections.property("id"))
                        //.add(Projections.property("cellPhone"))
                );*/
        if (locationId != null) {
            criteria.add(Restrictions.and(
                    Restrictions.isNotNull("location"),
                    Restrictions.eq("location", new Location(locationId))
            ));
            map.put("location", location);
        } else {
            criteria.add(Restrictions.isNull("location"));
            map.put("location", "Not Defined");
        }
        criteria.add(Restrictions.ne("status", MirrorDataList.CUST_STATUS_REFUNDED));
        criteria.addOrder(Order.asc("CID"));

        map.put("customerList", commonDAO.findAll(criteria));
        return new ModelAndView("/mis/customer_list", map);
    }
}

