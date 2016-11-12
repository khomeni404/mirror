package com.mirror2.csd;

import com.google.gson.Gson;
import com.mirror2.admin.service.AdminService;
import com.mirror2.csd.bean.PaymentDetailBean;
import com.mirror2.csd.model.*;
import com.mirror2.csd.service.CsdService;
import com.mirror2.csd.util.CurrentUserCredentials;
import com.mirror2.csd.util.OrdinalNumber;
import com.mirror2.messaging.threads.EmailThread;
import com.mirror2.csd.threads.ReceiptThread;
import com.mirror2.messaging.threads.MRSmsThread;
import com.mirror2.messaging.threads.SmsThread;
import com.mirror2.security.SessionUtil;
import com.mirror2.security.service.UserDetailsService;
import com.mirror2.util.*;
import com.mirror2.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/csd/")
public class CsdController {

    @Autowired
    private CsdService csdService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AdminService adminService;


    @RequestMapping(method = RequestMethod.GET, value = "/csdConfigurationHome.erp")
    public ModelAndView csdConfigurationHome() {
        Map<String, Object> hrmConfigurationHomeMap = new HashMap<String, Object>();
        hrmConfigurationHomeMap.put("PageTitle", "CSD Configuration Home");
        hrmConfigurationHomeMap.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("/csd/csd_configuration_home", hrmConfigurationHomeMap);
    }

    //Payment Home
    @RequestMapping(method = RequestMethod.GET, value = "/paymentHome.erp")
    public ModelAndView paymentHome() {
        Map<String, Object> payHomeMap = new HashMap<String, Object>();
        payHomeMap.put("PageTitle", "Payment Home");
        payHomeMap.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("/csd/payment_home", payHomeMap);
    }

    //Payment Home
    @RequestMapping(method = RequestMethod.GET, value = "/reportHome.erp")
    public ModelAndView reportHome() {
        Map<String, Object> payHomeMap = new HashMap<String, Object>();
        payHomeMap.put("PageTitle", "Report Home");
        payHomeMap.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("/csd/report/report_home", payHomeMap);
    }

    //approveHome
    @RequestMapping(method = RequestMethod.GET, value = "/approveHome.erp")
    public ModelAndView approveHome() {
        Map<String, Object> payHomeMap = new HashMap<String, Object>();
        payHomeMap.put("PageTitle", "Approve Home");
        payHomeMap.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("csd/approve_home", payHomeMap);
    }

    //Getting user from HttpSession
    @RequestMapping(method = RequestMethod.GET, value = "/test.erp")
    public ModelAndView test() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("PageTitle", "Current User");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("name", new CurrentUserCredentials().name);
        map.put("username", new CurrentUserCredentials().username);
        map.put("designation", new CurrentUserCredentials().designation);
        map.put("department", new CurrentUserCredentials().department);

        //return new ModelAndView("/csd/current_user", map);
        return new ModelAndView("csd/money_disburse_edit", map);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/customerList.erp")
    public ModelAndView customerList() {
        Map<String, Object> customerMap = new HashMap<String, Object>();
        customerMap.put("PageTitle", "Customer");
        customerMap.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("/csd/customer_list", customerMap);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customerJsonData.erp")
    public ModelAndView customerJsonData() {
        int start = 0;
        int limit = 100015;

        Map<String, Object> listMap = null;
        Map<String, Object> map = new HashMap<String, Object>();
        List<Customer> allData = csdService.findAllCustomer();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (Customer data : allData) {
            listMap = new HashMap<String, Object>();
            listMap.put("id", data.getId());
            listMap.put("bookingDate", data.getBookingDate());
            listMap.put("CID", data.getCID());
            listMap.put("name", data.getName());
            listMap.put("projectName", data.getBuilding().getProject().getProjectName());
            listMap.put("buildingName", data.getBuilding().getBuildingName());
            listMap.put("AID", data.getAID());
            listMap.put("countryLocation", data.getCountryLocation());
            listMap.put("referenceBy", data.getReferenceBy().getName());
            listMap.put("offerName", data.getOffer().getOfferName());
            listMap.put("status", data.getStatus());
            listMap.put("careBy", data.getCareBy());
            listMap.put("cellPhone", data.getCellPhone());
            listMap.put("altPhone", data.getAltPhone());
            listMap.put("emailAddress", data.getEmailAddress());
            listMap.put("mailingAddress", data.getMailingAddress());
            listMap.put("floorSize", data.getFloorSize());
            listMap.put("price", data.getPrice());
            listMap.put("handoverDate", data.getHandoverDate());
            listMap.put("paymentType", data.getPaymentType());
            mapList.add(listMap);

        }

        map.put("customerList", mapList.subList(start, start + limit > mapList.size() ?
                mapList.size() : start + limit));
        map.put("totalCount", mapList.size());
        return new ModelAndView(new JSONView(), map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createCustomer.erp")
    public ModelAndView createCustomer() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Create Customer");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        List<Building> buildings = csdService.findAllBuilding();
        map.put("buildings", buildings);
        List<MID> mids = csdService.findAllMID();
        map.put("mids", mids);
        List<EmpCsd> empCsds = csdService.findAllEmpCsd();
        map.put("empCsds", empCsds);
        List<Offer> offers = csdService.findAllOffer();
        map.put("offers", offers);
        map.put("errorMsg", "");
        return new ModelAndView("/csd/customer_create", map);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveCustomer.erp")
    public ModelAndView saveCustomer(@RequestParam("bookingDate") String bookingDate,
                                     @RequestParam("cid") String cid,
                                     @RequestParam("name") String name,
                                     @RequestParam String keyName,
                                     @RequestParam("buildingId") Long buildingId,
                                     @RequestParam("aid") String aid,
                                     @RequestParam("countryLocation") String countryLocation,
                                     @RequestParam("referenceId") String referenceId,
                                     @RequestParam("offerId") Long offerId,
                                     @RequestParam("careById") String careById,
                                     @RequestParam("cellPhone") String cellPhone,
                                     @RequestParam String salesType,
                                     @RequestParam("altPhone") String altPhone,
                                     @RequestParam("notes") String notes,
                                     @RequestParam("emailAddress") String emailAddress,
                                     @RequestParam("mailingAddress") String mailingAddress,
                                     @RequestParam("floorSize") int floorSize,
                                     @RequestParam("price") double price,
                                     @RequestParam("handoverDate") String handoverDate,
                                     @RequestParam("paymentType") String paymentType) {
        //This is to modify numeric or alphanumeric cid to 10 character alphanumeric format like "DPL 000125" from 125 or DPL 125
        if (cid.toUpperCase().startsWith("DPL")) {
            cid = cid.substring(3, cid.length());
        }
        DecimalFormat df = new DecimalFormat("000000");
        cid = "DPL " + df.format(Integer.parseInt(cid.trim()));
        //---------------------------------------------------------
        Customer customerDuplicate = null;
        TheDates theDates = new TheDates();
        try {
            customerDuplicate = csdService.getCustomer(cid);
        } catch (Exception ex) {
            //TODO...
        }
        if (customerDuplicate != null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("PageTitle", "Create Customer");
            map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

            List<Building> buildings = csdService.findAllBuilding();
            map.put("buildings", buildings);
            List<MID> mids = csdService.findAllMID();
            map.put("mids", mids);
            List<EmpCsd> empCsds = csdService.findAllEmpCsd();
            map.put("empCsds", empCsds);
            List<Offer> offers = csdService.findAllOffer();
            map.put("offers", offers);
            map.put("errorMsg", "This id <label style=\"color: red; font-weight: bold\">" + cid + "</label> exists");
            return new ModelAndView("/csd/customer_create", map);
        } else {
            Building building = csdService.getBuilding(buildingId);
            MID careBy = csdService.getMID(careById.split(":")[0].trim());
            MID referBy = csdService.getMID(referenceId.split(":")[0].trim());
            Offer offer = csdService.getOffer(offerId);

            MoneyDisburse moneyDisburse = new MoneyDisburse();
            moneyDisburse.setCarPark(0.0);
            moneyDisburse.setDownPayment(0.0);
            moneyDisburse.setSpecialPayment(0.0);
            moneyDisburse.setInstallment(0.0);
            moneyDisburse.setOtherPayment(0.0);
            moneyDisburse.setUpdate("Yes");
            csdService.save(moneyDisburse);

            Map<String, String> map = new HashMap<String, String>();
            Customer customer = new Customer();

            customer.setBookingDate(theDates.toDate(bookingDate));
            customer.setCID(cid);
            customer.setName(name.toUpperCase().trim());
            customer.setKeyName(keyName);
            customer.setBuilding(building);
            customer.setAID(aid);
            customer.setCountryLocation(countryLocation.trim());
            customer.setReferenceBy(referBy);
            customer.setOffer(offer);
            customer.setStatus(MirrorConstants.PROCESSING);
            customer.setCareBy(careBy);
            customer.setSalesType(salesType);
            customer.setCellPhone(cellPhone.trim());
            customer.setAltPhone(altPhone.trim());
            customer.setEmailAddress(emailAddress.toLowerCase().trim());
            customer.setMailingAddress(mailingAddress);
            customer.setFloorSize(floorSize);
            customer.setPrice(price);
            customer.setHandoverDate(theDates.toDate(handoverDate));
            customer.setPaymentType(paymentType.trim());
            customer.setMoneyDisburse(moneyDisburse);
            customer.setNotes(notes);
            //Collection
            building.getCustomers().add(customer);
            careBy.getCustomersCared().add(customer);
            referBy.getCustomersReferred().add(customer);
            offer.getCustomers().add(customer);

            csdService.save(customer);
            String sms = "New Customer: "+cid+"\n" +
                    "Name: "+name+"\n" +
                    "Reference: "+referBy.getName();
            SmsThread smsThread2 = new SmsThread("01717659287", sms);
            smsThread2.start();
            return new ModelAndView("redirect:/csd/createCustomer.erp", map);
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/editCustomer.erp")
    public ModelAndView editCustomer(@RequestParam("id") Long id, @RequestParam("errMsg") String errMsg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Update Customer");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        Customer customer = csdService.getCustomer(id);
        map.put("customer", customer);
        List<Building> buildings = csdService.findAllBuilding();
        map.put("buildings", buildings);
        List<MID> midList = csdService.findAllMID();
        map.put("midList", midList);
        List<EmpCsd> empCsds = csdService.findAllEmpCsd();
        map.put("empCsds", empCsds);
        List<Offer> offers = csdService.findAllOffer();
        map.put("offers", offers);

        map.put("customerDuplicateErrMsg", errMsg);
        return new ModelAndView("/csd/customer_edit", map);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateCustomer.erp")
    public ModelAndView updateCustomer(@RequestParam("bookingDate") String bookingDate,
                                       @RequestParam("id") Long id,
                                       @RequestParam("cid") String cid,
                                       @RequestParam("status") String status,
                                       @RequestParam("name") String name,
                                       @RequestParam String keyName,
                                       @RequestParam("buildingId") Long buildingId,
                                       @RequestParam("oldBuildingId") Long oldBuildingId,
                                       @RequestParam("aid") String aid,
                                       @RequestParam("countryLocation") String countryLocation,
                                       @RequestParam("referenceId") String referenceId,
                                       @RequestParam String salesType,
                                       @RequestParam("oldReferenceId") String oldReferenceId,
                                       @RequestParam("offerId") Long offerId,
                                       @RequestParam("oldOfferId") Long oldOfferId,
                                       @RequestParam("careById") String careById,
                                       @RequestParam("oldCareById") String oldCareById,
                                       @RequestParam("cellPhone") String cellPhone,
                                       @RequestParam("altPhone") String altPhone,
                                       @RequestParam("notes") String notes,
                                       @RequestParam("emailAddress") String emailAddress,
                                       @RequestParam("mailingAddress") String mailingAddress,
                                       @RequestParam("floorSize") int floorSize,
                                       @RequestParam("price") double price,
                                       @RequestParam("handoverDate") String handoverDate,
                                       @RequestParam("paymentType") String paymentType) {
        //This is to modify numeric or alphanumeric cid to 10 character alphanumeric format like "DPL 000125" from 125 or DPL 125
        if (cid.toUpperCase().startsWith("DPL")) {
            cid = cid.substring(3, cid.length());
        }
        DecimalFormat df = new DecimalFormat("000000");
        cid = "DPL " + df.format(Integer.parseInt(cid.trim()));
        //---------------------------------------------------------
        Customer customer1 = csdService.getCustomer(id);
        Customer customerDuplicate = null;
        try {
            customerDuplicate = csdService.getCustomer(cid);
        } catch (Exception ex) {
            //TODO...
        }
        if (customerDuplicate != null && !customer1.getCID().equals(cid)) {
            String errMsg = "CID <label style=\"color: red; font-weight: bold\">" + cid + "</label> already used. Try another one.";
            return new ModelAndView("redirect:/csd/editCustomer.erp?id=" + id + "&errMsg=" + errMsg);
        } else {
            //Collection Remove
            Building oldBuilding = csdService.getBuilding(oldBuildingId);
            oldBuilding.getCustomers().remove(csdService.getCustomer(id));
            MID oldCareBy = csdService.getMID(oldCareById);
            oldCareBy.getCustomersCared().remove(csdService.getCustomer(id));
            MID oldReferBy = csdService.getMID(oldReferenceId);
            oldReferBy.getCustomersReferred().remove(csdService.getCustomer(id));
            Offer oldOffer = csdService.getOffer(oldOfferId);
            oldOffer.getCustomers().remove(csdService.getCustomer(id));


            Building building = csdService.getBuilding(buildingId);
            MID careBy = csdService.getMID(careById.split(":")[0].trim());
            MID referBy = csdService.getMID(referenceId.split(":")[0].trim());
            Offer offer = csdService.getOffer(offerId);

            TheDates td = new TheDates();

            Customer customer = csdService.getCustomer(id);
            customer.setBookingDate(td.toDate(bookingDate));
            customer.setCID(cid);
            customer.setName(name.toUpperCase().trim());
            customer.setKeyName(keyName);
            customer.setBuilding(building);
            customer.setAID(aid);
            customer.setCountryLocation(countryLocation.trim());
            customer.setReferenceBy(referBy);
            customer.setSalesType(salesType);
            customer.setOffer(offer);
            customer.setStatus(status);
            customer.setCareBy(careBy);
            customer.setCellPhone(cellPhone.trim());
            customer.setAltPhone(altPhone.trim());
            customer.setEmailAddress(emailAddress.toLowerCase().trim());
            customer.setMailingAddress(mailingAddress);
            customer.setFloorSize(floorSize);
            customer.setPrice(price);
            customer.setHandoverDate(td.toDate(handoverDate));
            customer.setPaymentType(paymentType.trim());
            customer.setNotes(notes);

            //Collection Set
            building.getCustomers().add(customer);
            careBy.getCustomersCared().add(customer);
            referBy.getCustomersReferred().add(customer);
            offer.getCustomers().add(customer);

            csdService.save(customer);
            return new ModelAndView("redirect:/csd/viewCustomer.erp?cidView=" + cid);
        }


    }

    @RequestMapping(method = RequestMethod.GET, value = "/viewCustomer.erp")
    public ModelAndView viewCustomer(@RequestParam("cidView") String cid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Customer Details");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        cid = MirrorUtil.makeCid(cid);

        map.put("cid", cid);
        Customer customer = csdService.getCustomer(cid);
        map.put("customer", customer);

        List<FollowUpHistory> historyList = csdService.findAllFollowUpHistory(customer);
        map.put("historyList", historyList);

        return new ModelAndView("csd/customer_view_2", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/followUpHistoryList.erp")
    public ModelAndView followUpHistoryList(@RequestParam String of, @RequestParam String date) {
        HashMap map = new HashMap();
        map.put("PageTitle", "Customer Followup");
        map.put("DashboardLink", "../home.erp");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        List historyList = null;
        if (of.equals("missed")) {
            historyList = this.csdService.findAllFollowUpHistory(new Date(), of);
            date = sdf.format(new Date());
        } else if ((!date.equals("before") || !of.equals("before")) && (!date.equals("today") || !of.equals("today")) && (!date.equals("after") || !of.equals("after"))) {
            Date dateNew = (new TheDates()).getDateAfterDay(date, Integer.parseInt(of));
            historyList = this.csdService.findAllFollowUpHistory(dateNew, of);
            date = sdf.format(dateNew);
        } else {
            historyList = this.csdService.findAllFollowUpHistory(new Date(), of);
            date = sdf.format(new Date());
        }

        map.put("historyList", historyList);
        map.put("date", date + "");
        return new ModelAndView("csd/follow_up_history_list", map);
    }


    //Building start ................
    @RequestMapping(method = RequestMethod.GET, value = "/buildingList.erp")
    public ModelAndView buildingList() {
        Map<String, Object> buildingMap = new HashMap<String, Object>();
        buildingMap.put("PageTitle", "Building List");
        buildingMap.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("/csd/building_list", buildingMap);
    }

    //buildingJsonData
    @RequestMapping(method = RequestMethod.GET, value = "/buildingJsonData.erp")
    public ModelAndView buildingJsonData() {
        int start = 0;
        int limit = 100015;

        Map<String, Object> listMap = null;
        Map<String, Object> map = new HashMap<String, Object>();
        List<Building> allData = csdService.findAllBuilding();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (Building data : allData) {
            listMap = new HashMap<String, Object>();
            listMap.put("id", data.getId());
            listMap.put("buildingName", data.getBuildingName());
            listMap.put("floorSize", data.getTotalFloorSize());
            listMap.put("numberOfFloor", data.getNumberOfFloor());
            listMap.put("numberOfUnit", data.getNumberOfUnit());
            listMap.put("landArea", data.getLandArea());
            listMap.put("projectName", data.getProject().getProjectName());
            mapList.add(listMap);
        }

        map.put("buildingList", mapList.subList(start, start + limit > mapList.size() ?
                mapList.size() : start + limit));
        map.put("totalCount", mapList.size());
        return new ModelAndView(new JSONView(), map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createBuilding.erp")
    public ModelAndView createBuilding() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Create Building");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        List<Project> projects = csdService.findAllProject();
        map.put("projects", projects);

        return new ModelAndView("/csd/building_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveBuilding.erp")
    public ModelAndView saveBuilding(@RequestParam("buildingName") String buildingName,
                                     @RequestParam("nameAlias") String nameAlias,
                                     @RequestParam("floorSizes") String floorSizes,
                                     @RequestParam("totalFloorSize") int totalFloorSize,
                                     @RequestParam("numberOfFloor") int numberOfFloor,
                                     @RequestParam("numberOfUnit") int numberOfUnit,
                                     @RequestParam("aptIds") String aptIds,
                                     @RequestParam("landArea") Double landArea,
                                     @RequestParam("handOver") String handOver,
                                     @RequestParam("pid") Long pid) {

        Project project = csdService.getProject(pid);

        Map<String, String> map = new HashMap<String, String>();
        Building building = new Building();
        building.setBuildingName(MakDataChecker.setDashOnEmpty(buildingName.toUpperCase()));
        building.setNameAlias(MakDataChecker.setDashOnEmpty(nameAlias));
        building.setFloorSizes(MakDataChecker.setDashOnEmpty(floorSizes));
        building.setTotalFloorSize(MakDataChecker.setZeroOnEmpty(totalFloorSize));
        building.setNumberOfFloor(MakDataChecker.setZeroOnEmpty(numberOfFloor));
        building.setNumberOfUnit(MakDataChecker.setZeroOnEmpty(numberOfUnit));
        building.setAptIds(MakDataChecker.setDashOnEmpty(aptIds));
        building.setHandOver(MakDataChecker.setDashOnEmpty(handOver));
        building.setLandArea(MakDataChecker.setZeroOnEmpty(landArea));
        building.setProject(project);

        project.getBuildings().add(building);

        csdService.save(building);
        return new ModelAndView("redirect:/csd/buildingList.erp", map);
    }

    //editBuilding
    @RequestMapping(method = RequestMethod.GET, value = "/editBuilding.erp")
    public ModelAndView editBuilding(@RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Edit Building");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        Building building = csdService.getBuilding(id);
        map.put("building", building);
        map.put("projectList", csdService.findAllProject());

        return new ModelAndView("/csd/building_update", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateBuilding.erp")
    public ModelAndView updateBuilding(@RequestParam("id") Long id,
                                       @RequestParam("buildingName") String buildingName,
                                       @RequestParam("nameAlias") String nameAlias,
                                       @RequestParam("floorSizes") String floorSizes,
                                       @RequestParam("totalFloorSize") int totalFloorSize,
                                       @RequestParam("numberOfFloor") int numberOfFloor,
                                       @RequestParam("numberOfUnit") int numberOfUnit,
                                       @RequestParam("aptIds") String aptIds,
                                       @RequestParam("handOver") String handOver,
                                       @RequestParam("landArea") Double landArea,
                                       @RequestParam("pidOld") Long pidOld,
                                       @RequestParam("pid") Long pid) {
        Project projectOld = csdService.getProject(pidOld);
        projectOld.getBuildings().remove(csdService.getBuilding(id));

        Project project = csdService.getProject(pid);
        Building building = csdService.getBuilding(id);
        building.setBuildingName(MakDataChecker.setDashOnEmpty(buildingName.toUpperCase()));
        building.setNameAlias(MakDataChecker.setDashOnEmpty(nameAlias));
        building.setFloorSizes(MakDataChecker.setDashOnEmpty(floorSizes));
        building.setTotalFloorSize(MakDataChecker.setZeroOnEmpty(totalFloorSize));
        building.setNumberOfFloor(MakDataChecker.setZeroOnEmpty(numberOfFloor));
        building.setNumberOfUnit(MakDataChecker.setZeroOnEmpty(numberOfUnit));
        building.setAptIds(MakDataChecker.setDashOnEmpty(aptIds));
        building.setHandOver(MakDataChecker.setDashOnEmpty(handOver));
        building.setLandArea(MakDataChecker.setZeroOnEmpty(landArea));
        building.setProject(project);

        project.getBuildings().add(building);

        csdService.update(building);
        return new ModelAndView("redirect:/csd/editBuilding.erp?id=" + id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteBuilding.erp")
    public ModelAndView deleteBuilding(@RequestParam("id") Long id) {
        Map<String, String> deleteMap = new HashMap<String, String>();
        Building building = csdService.getBuilding(id);
        deleteMap.put("PageTitle", "Building List");
        Project p = building.getProject();
        p.getBuildings().remove(building);
        csdService.delete(building);
        return new ModelAndView("redirect:/csd/buildingList.erp", deleteMap);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/viewBuilding.erp")
    public ModelAndView viewBuilding(@RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Building");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        Building building = csdService.getBuilding(id);
        List<Building> buildingList = csdService.findAllBuilding();
        List<Customer> customerList = building.getCustomers();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        List<Map<String, String>> bookingList = new ArrayList<Map<String, String>>();
        Map<String, String> bookingMap;
        String[] ids = building.getAptIds().split(",");

        for (int i = 0; i < ids.length; i++) {
            bookingMap = new HashMap<String, String>();
            bookingMap.put("apt", ids[i]);
            if (customerList.size() != 0) {
                for (Customer c : customerList) {
                    String status = c.getStatus().toUpperCase();
                    if (!status.equals("REFUNDED"))
                        if (c.getAID().equals(ids[i])) {
                            bookingMap.put("applicant", c.getName());
                            bookingMap.put("cid", c.getCID());
                            bookingMap.put("location", "---");
                            bookingMap.put("mid", c.getReferenceBy().getMid());
                            bookingMap.put("booking", "Booked");
                            bookingMap.put("date", sdf.format(c.getBookingDate()));
                            bookingMap.put("status", status);
                            break;
                        } else {
                            bookingMap.put("applicant", "");
                            bookingMap.put("cid", "");
                            bookingMap.put("location", "");
                            bookingMap.put("mid", "");
                            bookingMap.put("booking", "");
                            bookingMap.put("date", "");
                            bookingMap.put("status", "");
                        }
                }
            } else {
                bookingMap.put("applicant", "");
                bookingMap.put("cid", "");
                bookingMap.put("location", "");
                bookingMap.put("mid", "");
                bookingMap.put("booking", "");
                bookingMap.put("date", "");
                bookingMap.put("status", "");
            }
            bookingList.add(bookingMap);
        }
        map.put("bookingList", bookingList);
        map.put("building", building);
        map.put("buildingList", buildingList);

        return new ModelAndView("/csd/booking_list", map);
    }

    //Money Receipt start ..........
    @RequestMapping(method = RequestMethod.GET, value = "/moneyReceiptList.erp")
    public ModelAndView moneyReceiptList() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Money Receipt List");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        return new ModelAndView("/csd/money_receipt_list", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/moneyReceiptJsonData.erp") //moneyReceiptJsonData
    public ModelAndView moneyReceiptJsonData(@RequestParam Integer start, @RequestParam Integer limit) {
        start = 0;
        limit = 15;
        Map<String, Object> listMap = null;
        Map<String, Object> map = new HashMap<String, Object>();
//        List<MoneyReceipt> allData = csdService.findAllMoneyReceipt(start, limit);
        List<MoneyReceipt> allData = csdService.findAllMoneyReceipt();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (MoneyReceipt data : allData) {
            listMap = new HashMap<String, Object>();
            listMap.put("id", data.getId());
            listMap.put("receiptNo", data.getReceiptNo());
            listMap.put("receiptDate", data.getReceiptDate());
            listMap.put("amount", data.getAmount());
            listMap.put("cid", data.getCustomer().getCID());
            listMap.put("name", data.getCustomer().getName());
            listMap.put("paymentMethod", data.getPaymentMethod());
            listMap.put("note", data.getNote());
            listMap.put("cashDate", data.getCashDate());
            listMap.put("entryDate", data.getEntryDate());
            listMap.put("signature", data.getSignature());
            listMap.put("operator", data.getOperator().getToken().getName());
            mapList.add(listMap);
        }

        map.put("receiptList", mapList.subList(start, start + limit > mapList.size() ?
                mapList.size() : start + limit));
//        map.put("receiptList", mapList);
        map.put("totalCount", csdService.findAllMoneyReceipt().size());

//        Gson gson = new Gson();
//        return gson.toJson(map);
//
        return new ModelAndView(new JSONView(), map);
    }

    //createMoneyReceipt
    @RequestMapping(method = RequestMethod.GET, value = "/createMoneyReceipt.erp")
    public ModelAndView createMoneyReceipt() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("PageTitle", "Insert MR");
        map.put("msg", "");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        return new ModelAndView("/csd/money_receipt_create_new", map);
    }

    //saveMoneyReceipt
    @RequestMapping(method = RequestMethod.POST, value = "/saveMoneyReceipt.erp")
    public ModelAndView saveMoneyReceipt(@RequestParam("receiptNo") String receiptNo,
                                         @RequestParam("receiptDate") String receiptDate,
                                         @RequestParam("amount") Double amount,
                                         @RequestParam("CID") String cid,
                                         @RequestParam("paymentMethod") String paymentMethod,
                                         @RequestParam("note") String note,
                                         @RequestParam("cashDate") String cashDate,
                                         @RequestParam("signature") int signature) {
        if (cid.toUpperCase().startsWith("DPL")) {
            cid = cid.substring(3, cid.length());
        }
        DecimalFormat df = new DecimalFormat("000000");
        cid = "DPL " + df.format(Integer.parseInt(cid.trim()));

        Map<String, String> map = new HashMap<String, String>();
        map.put("PageTitle", "Insert MR");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        Customer customer = csdService.getCustomer(cid);

        if (customer == null) {
            map.put("cidMsg", "Customer <label style=\"color: red; font-weight: bold\">" + cid + "</label> doesn't exists");
            return new ModelAndView("/csd/money_receipt_create_new", map);
        }
        //------------------------------------
        if (!paymentMethod.toUpperCase().equals("CHEQUE")) {
            MoneyDisburse md = customer.getMoneyDisburse();
            md.setUpdate("No");
            csdService.update(md);
        }
        //------------------------------------

        MoneyReceipt moneyReceiptDuplicate = null;
        try {
            moneyReceiptDuplicate = csdService.getMoneyReceipt(receiptNo);
        } catch (Exception ex) {
            //TODO...
        }
        if (moneyReceiptDuplicate != null) {
            map.put("receiptNoMsg", "<label style=\"color: red; font-weight: bold\">" + receiptNo + "</label> already exists");
            return new ModelAndView("/csd/money_receipt_create_new", map);
        } else {
            User operator = new CurrentUserCredentials().user;
            MoneyReceipt moneyReceipt = new MoneyReceipt();
            moneyReceipt.setReceiptNo(receiptNo);
            Date rcptDate = new TheDates().toDate(receiptDate);
            moneyReceipt.setReceiptDate(rcptDate);
            moneyReceipt.setAmount(amount);
            moneyReceipt.setCustomer(customer);
            moneyReceipt.setPaymentMethod(paymentMethod);
            moneyReceipt.setNote(note);
            moneyReceipt.setCashDate(new TheDates().toDate(cashDate));
            moneyReceipt.setEntryDate(new Date());
            moneyReceipt.setSignature(signature);
            moneyReceipt.setOperator(operator);
            customer.getMoneyReceipts().add(moneyReceipt);
            if (paymentMethod.toUpperCase().equals("CHEQUE")) {
                moneyReceipt.setStatus("Not Passed");
            } else {
                moneyReceipt.setStatus("Passed");
            }
            //operator.getMoneyReceipts().add(moneyReceipt);   //This line is the cause of LazyInitializationException
            //ReceiptThread rt = new ReceiptThread(moneyReceipt, csdService);
            //rt.start();
            if (csdService.save(moneyReceipt)) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                /**SMS Sending part*/
                if (!paymentMethod.toUpperCase().equals("CHEQUE"))
                    try {
                        String sms = "Dear Sir/Madam,\n" +
                                "Your recent (" + sdf.format(moneyReceipt.getReceiptDate()) + ") payment has been updated with apartment.\n" +
                                "ID: " + cid + "\n" +
                                "Name: " + customer.getName() + "\n" +
                                "New amount: " + amount + "0/-\n" +
                                "Receipt No.: " + receiptNo + "\n" +
                                "Balance: " + csdService.getTotalDeposit(customer) + "0/-\n";
                        SmsThread smsThread = new SmsThread(customer.getCellPhone(), sms);
                        smsThread.start();
                        SmsThread smsThread2 = new SmsThread("01717659287", sms);
                        smsThread2.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                /** Email Sending part*/
                String type = paymentMethod.toUpperCase().equals("CHEQUE") ? "cheque" : "amount";
                String message = "Dear Sir/Madam,\n" +
                        "Deposited " + type + " has been received with thanks against the booked apartment.\n" +
                        "Details are given below... " +
                        "\n\n" +
                        "Customer ID (CID) : " + cid + "\n" +
                        "Customer Name : " + customer.getName() + "\n" +
                        "Building Name : " + customer.getBuilding().getBuildingName() + "\n" +
                        "Apartment ID (AID) : " + customer.getAID() + "\n" +
                        "Receipt No. : " + receiptNo + "\n" +
                        "Receipt Date : " + sdf.format(rcptDate) + "\n" +
                        "Amount : " + amount + "\n" +
                        "Payment Type : " + paymentMethod + "\n\n";
                EmailThread emailThread = new EmailThread("cs.dorpan@gmail.com", customer.getEmailAddress(), "DORPAN : Money Receipt Record (" + receiptNo + ")", message);
                emailThread.start();
                map.put("receiptNoMsg", "<label style=\"color: green; font-weight: bold\">" + receiptNo + "</label> saved successfully.");
            } else {
                map.put("receiptNoMsg", "<label style=\"color: green; font-weight: bold\">" + receiptNo + "</label> can't be saved.");
            }

            return new ModelAndView("/csd/money_receipt_create_new", map);
        }
    }

    public static void main(String[] args) {
        SmsThread smsThread = new SmsThread("01717659287", "This is an SMS");
        smsThread.start();
    }

    //viewMoneyReceipt
    @RequestMapping(method = RequestMethod.GET, value = "/viewMoneyReceipt.erp")
    public ModelAndView viewMoneyReceipt(@RequestParam("mrNo") String mrNo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "View Receipt");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        MoneyReceipt mr = csdService.getMoneyReceipt(mrNo);
        map.put("mr", mr);
        return new ModelAndView("/csd/money_receipt_view", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/editMoneyReceipt.erp")
    public ModelAndView editMoneyReceipt(@RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        MoneyReceipt mr = csdService.getMoneyReceipt(id);
        map.put("PageTitle", "Update Receipt");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("mr", mr);
        map.put("id", id);
        map.put("receiptNo", mr.getReceiptNo());
        map.put("receiptDate", mr.getReceiptDate());
        map.put("amount", mr.getAmount());
        map.put("cid", mr.getCustomer().getCID());
        map.put("note", mr.getNote());
        map.put("cashDate", mr.getCashDate());
        map.put("entryDate", mr.getEntryDate());
        map.put("status", mr.getStatus());
        return new ModelAndView("/csd/money_receipt_edit", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateMoneyReceipt.erp")
    public ModelAndView updateMoneyReceipt(@RequestParam("id") Long id,
                                           @RequestParam("receiptNo") String receiptNo,
                                           @RequestParam("amount") Double amount,
                                           @RequestParam("oldCid") String oldCid,
                                           @RequestParam("newCid") String newCid,
                                           @RequestParam("paymentMethod") String paymentMethod,
                                           @RequestParam("note") String note,
                                           @RequestParam("cashDate") String cashDate,
                                           @RequestParam("receiptDate") String receiptDate,
                                           @RequestParam("status") String status) {
        DecimalFormat df = new DecimalFormat("000000");
        if (newCid.toUpperCase().startsWith("DPL")) {
            newCid = newCid.substring(3, newCid.length());
        }
        newCid = "DPL " + df.format(Integer.parseInt(newCid.trim()));

        Customer customerOld = csdService.getCustomer(oldCid);
        Customer customerNew = csdService.getCustomer(newCid);
        //------------------------------------

        User operator = new CurrentUserCredentials().user;
        MoneyReceipt receipt = csdService.getMoneyReceipt(id);

        receipt.setAmount(amount);
        receipt.setReceiptNo(receiptNo);
        receipt.setCustomer(customerNew);
        receipt.setNote(note);
        receipt.setCashDate(new TheDates().toDate(cashDate));
        receipt.setReceiptDate(new TheDates().toDate(receiptDate));
        receipt.setPaymentMethod(paymentMethod);
        receipt.setOperator(operator);
        receipt.setStatus(status);
        customerOld.getMoneyReceipts().remove(receipt);
        customerNew.getMoneyReceipts().add(receipt);

        //operator.getMoneyReceipts().add(moneyReceipt);   //This line is the cause of LazyInitializationException
        csdService.update(receipt);

        MoneyDisburse md = customerNew.getMoneyDisburse();
        md.setUpdate("No");
        csdService.update(md);

        Map<String, String> map = new HashMap<String, String>();
        map.put("mrNo", receiptNo);
        return new ModelAndView("redirect:/csd/viewMoneyReceipt.erp", map);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteMoneyReceipt.erp")
    public ModelAndView deleteMoneyReceipt(@RequestParam("id") Long id) {
        MoneyReceipt mr = csdService.getMoneyReceipt(id);
        mr.getCustomer().getMoneyReceipts().remove(mr);
        csdService.delete(mr);
        return new ModelAndView("redirect:/csd/moneyReceiptList.erp");
    }

    //Status Updating of cheque
    @RequestMapping(method = RequestMethod.GET, value = "/updateMoneyReceiptStatus.erp")
    public ModelAndView updateMoneyReceiptStatus(@RequestParam("id") Long id) {
        Map<String, String> map = new HashMap<String, String>();
        MoneyReceipt moneyReceipt = csdService.getMoneyReceipt(id);
        moneyReceipt.setId(id);
        moneyReceipt.setStatus("Passed");
        moneyReceipt.setStatusUpdateBy(new CurrentUserCredentials().user);
        csdService.update(moneyReceipt);

        return new ModelAndView("redirect:/csd/voucherListNa.erp", map);
    }

    //chequeListNp
    @RequestMapping(method = RequestMethod.GET, value = "/chequeListNotPassed.erp")
    public ModelAndView chequeListNotPassed(@RequestParam("status") String status) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (status.toUpperCase().equals("N")) {
            map.put("PageTitle", "Cheque List : Not Passed");
            map.put("status", "Not Passed");
        } else if (status.toUpperCase().equals("X")) {
            map.put("PageTitle", "Cheque List : Rejected");
            map.put("status", "Rejected");
        } else if (status.toUpperCase().equals("Y")) {
            map.put("PageTitle", "Cheque List : Passed");
            map.put("status", "Passed");
        } else if (status.toUpperCase().equals("ALL")) {
            map.put("PageTitle", "Cheque List : Not Passed (All)");
            //map.put("status", "All");
            map.put("status", "Not Passed");
        } else {
            map.put("PageTitle", "<span style='color: red'>Please type N / Y / X</span>");

        }


        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> listMap;
        List<MoneyReceipt> moneyReceipts;
        if (status.toUpperCase().equals("N")) {
            moneyReceipts = csdService.waitingMoneyReceiptForApprove();
        } else {
            moneyReceipts = csdService.findAllMoneyReceipt();
        }
        map.put("chequeList", moneyReceipts);


        return new ModelAndView("csd/cheque_list", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/chequeListNpJsonData.erp")
    public ModelAndView chequeListNpJsonData() {
        int start = 0;
        int limit = 10015;

        Map<String, Object> listMap = null;
        Map<String, Object> map = new HashMap<String, Object>();
        //List<MoneyReceipt> allData = csdService.findAllMaxDatedMoneyReceipt("Not Passed");
        List<MoneyReceipt> allData = csdService.findAllMoneyReceipt();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (MoneyReceipt data : allData) {
            if (data.getStatus().toUpperCase().equals("NOT PASSED")) {
                listMap = new HashMap<String, Object>();
                listMap.put("id", data.getId());
                listMap.put("chequeDetails", data.getNote());
                listMap.put("cid", data.getCustomer().getCID());
                listMap.put("name", data.getCustomer().getName());
                listMap.put("receiptDate", data.getReceiptDate());
                listMap.put("receiptNo", data.getReceiptNo());
                listMap.put("chequeDate", data.getCashDate());
                listMap.put("amount", data.getAmount());
                mapList.add(listMap);
            }
        }

        map.put("chequeList", mapList.subList(start, start + limit > mapList.size() ?
                mapList.size() : start + limit));
        map.put("totalCount", mapList.size());
        return new ModelAndView(new JSONView(), map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/updateChequeStatus.erp")
    public ModelAndView updateChequeStatus(@RequestParam("id") Long id,
                                           @RequestParam("passedDate") Date passedDate,
                                           @RequestParam("code") int code) {
        Customer customer = csdService.getMoneyReceipt(id).getCustomer();
        MoneyDisburse md = customer.getMoneyDisburse();
        md.setUpdate("No");
        csdService.update(md);

        Map<String, String> map = new HashMap<String, String>();
        MoneyReceipt moneyReceipt = csdService.getMoneyReceipt(id);
        moneyReceipt.setId(id);
        String state = "";
        String dateOf = "";
        if (code == 1111) {
            moneyReceipt.setStatus("Passed");
            state = "ENCASHED";
            dateOf = "Cash Date : ";
        } else if (code == 2222) {
            moneyReceipt.setStatus("Rejected");
            state = "REJECTED";
            dateOf = "Cheque Date : ";
        }
        moneyReceipt.setCashDate(passedDate);
        moneyReceipt.setStatusUpdateBy(new CurrentUserCredentials().user);


        if (csdService.update(moneyReceipt)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String message = "Dear Sir/Madam,\n" +
                    "Deposited cheque has been " + state + " against the booked apartment.\n" +
                    "Details are given below... " +
                    "\n\n" +
                    "Cheque Note/No. : " + moneyReceipt.getNote() + "\n" +
                    "Receipt Date : " + sdf.format(moneyReceipt.getReceiptDate()) + "\n" +
                    dateOf + sdf.format(passedDate) + "\n" +
                    "Customer ID (CID) : " + customer.getCID() + "\n" +
                    "Customer Name : " + customer.getName() + "\n" +
                    "Building Name : " + customer.getBuilding().getBuildingName() + "\n" +
                    "Apartment ID (AID) : " + customer.getAID() + "\n" +
                    "Receipt No. : " + moneyReceipt.getReceiptNo() + "\n" +
                    "Amount : " + moneyReceipt.getAmount() + "\n\n";
            EmailThread emailThread = new EmailThread("cs.dorpan@gmail.com", customer.getEmailAddress(), "DORPAN : Cheque Encashment (" + moneyReceipt.getNote() + ")", message);
            emailThread.start();

            try {
                String sms = "";
                if (code == 1111) {
                    sms = "Dear Sir/Madam,\n" +
                            "Your recent (" + sdf.format(moneyReceipt.getReceiptDate()) + ") cheque has been ENCASHED.\n" +
                            "ID: " + customer.getCID() + "\n" +
                            "Name: " + customer.getName() + "\n" +
                            "Receipt No: " + moneyReceipt.getReceiptNo() + "\n" +
                            "Cheque Details: " + moneyReceipt.getNote() + "\n" +
                            "Cheque Amount: " + moneyReceipt.getAmount() + "0/-\n";
                } else if (code == 2222) {
                    sms = "Dear Sir/Madam,\n" +
                            "Your recent (" + sdf.format(moneyReceipt.getReceiptDate()) + ") cheque has been REJECTED.\n" +
                            "ID: " + customer.getCID() + "\n" +
                            "Name: " + customer.getName() + "\n" +
                            "Receipt No: " + moneyReceipt.getReceiptNo() + "\n" +
                            "Cheque Details: " + moneyReceipt.getNote() + "\n" +
                            "Call 01938852224 for details.\n";
                }
                SmsThread smsThread = new SmsThread(customer.getCellPhone(), sms);
                smsThread.start();
                SmsThread smsThread2 = new SmsThread("01717659287", sms);
                smsThread2.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("redirect:/csd/chequeListNotPassed.erp?status=N", map);
    }


    //Other Payment Start .................
    //===========================================================================================
    @RequestMapping(method = RequestMethod.GET, value = "/createOtherPayment.erp")
    public ModelAndView createOtherPayment() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Customer> customers = csdService.findAllCustomer();
        map.put("PageTitle", "Insert Other Payment");
        map.put("errorMsg", "");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("customers", customers);
        return new ModelAndView("csd/other_payment_create", map);
    }

    //saveOtherPayment
    @RequestMapping(method = RequestMethod.POST, value = "/saveOtherPayment.erp")
    public ModelAndView saveOtherPayment(@RequestParam("cid") String cid,
                                         @RequestParam("numberOfPayment") int numberOfPayment,

                                         @RequestParam("paymentType1") String paymentType1,
                                         @RequestParam("paymentName1") String paymentName1,
                                         @RequestParam("amount1") Double amount1,
                                         @RequestParam("deadLine1") String deadLine1,
                                         @RequestParam("note1") String note1,

                                         @RequestParam("paymentType2") String paymentType2,
                                         @RequestParam("paymentName2") String paymentName2,
                                         @RequestParam("amount2") Double amount2,
                                         @RequestParam("deadLine2") String deadLine2,
                                         @RequestParam("note2") String note2,

                                         @RequestParam("paymentType3") String paymentType3,
                                         @RequestParam("paymentName3") String paymentName3,
                                         @RequestParam("amount3") Double amount3,
                                         @RequestParam("deadLine3") String deadLine3,
                                         @RequestParam("note3") String note3,

                                         @RequestParam("paymentType4") String paymentType4,
                                         @RequestParam("paymentName4") String paymentName4,
                                         @RequestParam("amount4") Double amount4,
                                         @RequestParam("deadLine4") String deadLine4,
                                         @RequestParam("note4") String note4,

                                         @RequestParam("paymentType5") String paymentType5,
                                         @RequestParam("paymentName5") String paymentName5,
                                         @RequestParam("amount5") Double amount5,
                                         @RequestParam("deadLine5") String deadLine5,
                                         @RequestParam("note5") String note5

                                         //There are more field to add here....
    ) {
        //This is to modify numeric or alphanumeric cid to 10 character alphanumeric format like "DPL 000125" from 125 or DPL 125
        if (cid.toUpperCase().startsWith("DPL")) {
            cid = cid.substring(3, cid.length());
        }
        DecimalFormat df = new DecimalFormat("000000");
        cid = "DPL " + df.format(Integer.parseInt(cid.trim()));
        //---------------------------------------------------------

        OtherPayment otherPayment;
        Customer customer = csdService.getCustomer(cid);
        TheDates td = new TheDates();
        if (numberOfPayment >= 1) {
            otherPayment = new OtherPayment();
            otherPayment.setCustomer(customer);
            otherPayment.setPaymentType(paymentType1);
            otherPayment.setPaymentName(paymentName1);
            otherPayment.setAmount(amount1);
            otherPayment.setNote(note1);
            otherPayment.setDeadLine(td.toDate(deadLine1));
            customer.getOtherPayments().add(otherPayment);
            csdService.save(otherPayment);
        }
        if (numberOfPayment >= 2) {
            otherPayment = new OtherPayment();
            otherPayment.setCustomer(customer);
            otherPayment.setPaymentType(paymentType2);
            otherPayment.setPaymentName(paymentName2);
            otherPayment.setAmount(amount2);
            otherPayment.setNote(note2);
            otherPayment.setDeadLine(td.toDate(deadLine2));
            customer.getOtherPayments().add(otherPayment);
            csdService.save(otherPayment);
        }
        if (numberOfPayment >= 3) {
            otherPayment = new OtherPayment();
            otherPayment.setCustomer(customer);
            otherPayment.setPaymentType(paymentType3);
            otherPayment.setPaymentName(paymentName3);
            otherPayment.setAmount(amount3);
            otherPayment.setNote(note3);
            otherPayment.setDeadLine(td.toDate(deadLine3));
            customer.getOtherPayments().add(otherPayment);
            csdService.save(otherPayment);
        }
        if (numberOfPayment >= 4) {
            otherPayment = new OtherPayment();
            otherPayment.setCustomer(customer);
            otherPayment.setPaymentType(paymentType4);
            otherPayment.setPaymentName(paymentName4);
            otherPayment.setAmount(amount4);
            otherPayment.setNote(note4);
            otherPayment.setDeadLine(td.toDate(deadLine4));
            customer.getOtherPayments().add(otherPayment);
            csdService.save(otherPayment);
        }
        if (numberOfPayment >= 5) {
            otherPayment = new OtherPayment();
            otherPayment.setCustomer(customer);
            otherPayment.setPaymentType(paymentType5);
            otherPayment.setPaymentName(paymentName5);
            otherPayment.setAmount(amount5);
            otherPayment.setNote(note5);
            otherPayment.setDeadLine(td.toDate(deadLine5));
            customer.getOtherPayments().add(otherPayment);
            csdService.save(otherPayment);
        }
        return new ModelAndView("redirect:/csd/findOtherPayment.erp?cid=" + cid);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/otherPaymentList.erp")
    public ModelAndView otherPaymentList(@RequestParam("cid") String cid) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (cid.toUpperCase().startsWith("DPL")) {
            cid = cid.substring(3, cid.length());
        }
        DecimalFormat df = new DecimalFormat("000000");
        cid = "DPL " + df.format(Integer.parseInt(cid.trim()));

        Customer customer = csdService.getCustomer(cid);
        List<OtherPayment> otherPayments = customer.getOtherPayments();
        List<Installment> installments = customer.getInstallments();

        map.put("PageTitle", "Payment Schedule");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("cid", cid);
        map.put("id", customer.getId());
        map.put("name", customer.getName());
        map.put("otherPayments", otherPayments);
        map.put("instInfo", csdService.getInstallmentInfo(customer));
        map.put("installments", installments);

        return new ModelAndView("csd/other_payment_list", map);
    }

    //deleteOtherPayment
    @RequestMapping(method = RequestMethod.GET, value = "/deleteOtherPayment.erp")
    public ModelAndView deleteOtherPayment(@RequestParam("id") Long id) {
        Map<String, String> map = new HashMap<String, String>();
        OtherPayment otherPayment = csdService.getOtherPayment(id);
        Customer c = otherPayment.getCustomer();
        c.getOtherPayments().remove(otherPayment);
        csdService.delete(otherPayment);

        return new ModelAndView("redirect:/csd/otherPaymentList.erp?cid=" + c.getCID(), map);
    }

    //findOtherPayment
    @RequestMapping(method = RequestMethod.GET, value = "/findOtherPayment.erp")
    public ModelAndView findOtherPayment(@RequestParam("cid") String cid) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (cid.toUpperCase().startsWith("DPL")) {
            cid = cid.substring(3, cid.length());
        }
        DecimalFormat df = new DecimalFormat("000000");
        cid = "DPL " + df.format(Integer.parseInt(cid.trim()));

        Customer customer = csdService.getCustomer(cid);
        List<OtherPayment> otherPayments = customer.getOtherPayments();

        map.put("PageTitle", "Payment Schedule");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("cid", cid);
        map.put("name", customer.getName());
        map.put("otherPayments", otherPayments);
        map.put("instInfo", csdService.getInstallmentInfo(customer));

        return new ModelAndView("csd/report/payment_schedule", map);
    }


    //Installment Start .................
    //===========================================================================================
    @RequestMapping(method = RequestMethod.GET, value = "/createInstallment.erp")
    public ModelAndView createInstallment() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Customer> customers = csdService.findAllCustomer();
        map.put("PageTitle", "Installment Create");
        map.put("errorMsg", "");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("customers", customers);
        return new ModelAndView("csd/installment_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveInstallment.erp")
    public ModelAndView saveInstallment(@RequestParam("cid") String cid,
                                        @RequestParam("noOfInstallment") int noOfInstallment,
                                        @RequestParam("noOfMonth") int noOfMonth,
                                        @RequestParam("amount") Double amount,
                                        @RequestParam("startFromMonth") int startFromMonth,
                                        @RequestParam("startFromYear") int startFromYear) {
        if (cid.toUpperCase().startsWith("DPL")) {
            cid = cid.substring(3, cid.length());
        }
        DecimalFormat df = new DecimalFormat("000000");
        cid = "DPL " + df.format(Integer.parseInt(cid.trim()));
        //--------------------------------------------------------------

        Map<String, String> map = new HashMap<String, String>();
        Installment installment;
        TheDates theDates = new TheDates();
//        OrdinalNumber on = new OrdinalNumber();
        Customer customer = csdService.getCustomer(cid);
        String months[] = {"January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October",
                "November", "December"};
        int monthIndex = startFromMonth;

        int year = startFromYear;

        int counter = 1;
        for (int j = 0; j < noOfInstallment; j++) {
            installment = new Installment();
            installment.setCustomer(customer);
            installment.setAmount(amount);
            installment.setNote("");
            installment.setMonthPerInst(noOfMonth);
            installment.setInstallmentName(counter + OrdinalNumber.getOrdinalNumber(counter) + " Installment");     //Set installment's Serial No.
            installment.setInstallmentOf(months[monthIndex] + " of " + year);
            installment.setDeadLine(theDates.toDate(year + "-" + (monthIndex + 1) + "-5"));   //Deadline is every 5th day of each month
            customer.getInstallments().add(installment);    //OMR
            csdService.save(installment);
            counter++;
            monthIndex = monthIndex + noOfMonth;
            if (monthIndex >= 12) {
                monthIndex = monthIndex - 12;
                year++;
            }
        }
        //OMR
        map.put("PageTitle", "Insert Installment");
        map.put("errorMsg", "");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        //return new ModelAndView("csd/installment_create", map);
        return new ModelAndView("redirect:/csd/findOtherPayment.erp?cid=" + cid);
    }

    //deleteInstallment
    @RequestMapping(method = RequestMethod.GET, value = "/deleteInstallment.erp")
    public ModelAndView deleteInstallment(@RequestParam("forWhat") String forWhat,
                                          @RequestParam("id") Long id) {
        Map<String, String> map = new HashMap<String, String>();

        if (forWhat.toUpperCase().equals("ALL")) {
            Customer customer = csdService.getCustomer(id);
            csdService.deleteAllInstallment(customer);
            return new ModelAndView("redirect:/csd/otherPaymentList.erp?cid=" + customer.getCID(), map);
        } else {
            Installment installment = csdService.getInstallment(id);
            installment.getCustomer().getInstallments().remove(installment);
            csdService.delete(installment);
            return new ModelAndView("redirect:/csd/otherPaymentList.erp?cid=" + installment.getCustomer().getCID(), map);
        }


    }

    //View Payment Report
    @RequestMapping(method = RequestMethod.GET, value = "/viewPaymentReport.erp")
    public ModelAndView viewPaymentReport(@RequestParam("cid") String cid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Payment Report");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        try {
            cid = cid.trim();
            String fileNo = "";
            if (cid.startsWith("D")) {
                fileNo = Integer.parseInt(cid.trim().substring(3, cid.length()).trim()) + "";
            } else {
                fileNo = Integer.parseInt(cid.trim()) + "";
            }


            if (cid.toUpperCase().startsWith("DPL")) {
                cid = cid.substring(3, cid.length());
            }
            map.put("fileNo", fileNo);

            DecimalFormat df = new DecimalFormat("000000");
            cid = "DPL " + df.format(Integer.parseInt(cid.trim()));
        } catch (NumberFormatException nfe) {
            map.put("errMsg", "Please insert only CID");
            return new ModelAndView("csd/report/payment_report", map);
        }

        Customer customer = csdService.getCustomer(cid);


        if (customer != null) {
            Double payableInstAmount = csdService.getPayableInstallmentAmount(customer);
            Double totalInstAmount = csdService.getTotalInstallmentAmount(customer);
            Double paidInstAmount = customer.getMoneyDisburse().getInstallment();
            Double totalInst = csdService.getTotalInstallment(customer);
            Double payableInst = csdService.getPayableInstallment(customer);
            Double amountPerInst = totalInstAmount / totalInst;
            Double paidInst = paidInstAmount / amountPerInst;
            Double dueInst = payableInst - paidInst;
            Double dueInstAmount = payableInstAmount - paidInstAmount;

            Double totalDp = csdService.getTotalOtherPaymentAmount(customer, "Down Payment");
            Double payableDp = csdService.getPayableOtherPaymentAmount(customer, "Down Payment");
            Double paidDp = customer.getMoneyDisburse().getDownPayment();
            Double dueDp = payableDp - paidDp;

            Double totalSp = csdService.getTotalOtherPaymentAmount(customer, "Special Payment");
            Double payableSp = csdService.getPayableOtherPaymentAmount(customer, "Special Payment");
            Double paidSp = customer.getMoneyDisburse().getSpecialPayment();
            Double dueSp = payableSp - paidSp;

            Double totalCp = csdService.getTotalOtherPaymentAmount(customer, "Car Park");
            Double payableCp = csdService.getPayableOtherPaymentAmount(customer, "Car Park");
            Double paidCp = customer.getMoneyDisburse().getCarPark();
            Double dueCp = payableCp - paidCp;

            Double totalOp = csdService.getTotalOtherPaymentAmount(customer, "Other Payment");
            Double payableOp = csdService.getPayableOtherPaymentAmount(customer, "Other Payment");
            Double paidOp = customer.getMoneyDisburse().getOtherPayment();
            Double dueOp = payableOp - paidOp;

            map.put("PageTitle", "Payment Report");
            map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
            map.put("customer", customer);
            map.put("today", new TheDates().getTodaySmart());
            map.put("aid", customer.getBuilding().getBuildingName().substring(0, 3) + " - " + customer.getAID());
            map.put("handOver", customer.getHandoverDate().toString().substring(0, 4));

            map.put("totalDp", totalDp);
            map.put("totalSp", totalSp);
            map.put("totalCp", totalCp);
            map.put("totalOp", totalOp);
            map.put("totalInst", totalInst);
            map.put("totalInstAmount", totalInstAmount);


            map.put("payableDp", payableDp);
            map.put("payableSp", payableSp);
            map.put("payableCp", payableCp);
            map.put("payableOp", payableOp);
            map.put("payableInst", payableInst);
            map.put("payableInstAmount", payableInstAmount);

            map.put("paidDp", paidDp);
            map.put("paidSp", paidSp);
            map.put("paidCp", paidCp);
            map.put("paidOp", paidOp);
            map.put("paidInst", paidInst);
            map.put("paidInstAmount", paidInstAmount);

            map.put("dueDp", dueDp);
            map.put("dueSp", dueSp);
            map.put("dueOp", dueOp);
            map.put("dueCp", dueCp);
            map.put("dueInst", dueInst);
            map.put("dueInstAmount", dueInstAmount);

            Double apartmentPrice = totalCp + totalDp + totalInstAmount + totalOp + totalSp;
            Double paidTotalActual = csdService.getTotalDeposit(customer);
            map.put("apartmentPrice", apartmentPrice);
            map.put("paidTotalActual", paidTotalActual);
            map.put("restTotal", apartmentPrice - paidTotalActual);
            map.put("paidTotalDisbursed", csdService.getTotalDisburse(customer));
            map.put("amountPerInst", amountPerInst);
            map.put("errMsg", "");
        } else {
            map.put("errMsg", "No such Customer found");
        }
        map.put("cid", cid);
        return new ModelAndView("csd/report/payment_report", map);
    }


    //sendGenInfoSms
    @RequestMapping(method = RequestMethod.GET, value = "/sendGenInfoSms.erp")
    public
    @ResponseBody
    String sendGenInfoSms(@RequestParam Long id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("PageTitle", "Insert MR");
        map.put("msg", "");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Customer customer = csdService.getCustomer(id);
        String cellNo = "01717659287";
        cellNo = customer.getCellPhone();

        String cid = customer.getCID();
        String name = customer.getName();
        Building building = customer.getBuilding();
        Project project = building.getProject();
        String aid = customer.getAID();
        String bookingDate = sdf.format(customer.getBookingDate());
        String floorSize = String.valueOf(customer.getFloorSize());
        Offer offer = customer.getOffer();

        String date = sdf.format(new Date());

        String sms = "Dear Sir/Madam,\n" +
                "Here are your apartment info." +
                "\nID: " + cid +
                "\nName: " + name +
                "\nProject: " + project.getProjectName() +
                "\nBuilding: " + building.getBuildingName() +
                "\nApt. ID: " + aid +
                "\nFloor Size: " + floorSize +
                "\nOffer: " + offer.getOfferName() +
                "\nBooking Date: " + bookingDate +
                ".";

        FollowUpHistory history = new FollowUpHistory();
        history.setDate(new Date());
        history.setAttentionOn(new Date());
        history.setActive(false);
        history.setCalledBy(SessionUtil.getSessionUser());
        history.setClientsComment("--");
        history.setRemarks("<b>SMS sent Apt. Info.</b><br/>" + sms);
        Customer customer1 = csdService.getCustomer(id);
        history.setCustomer(customer1);
        customer1.getHistoryList().add(history);
        csdService.save(history);


        try {
            SmsThread smsThread = new SmsThread(cellNo, sms);
            smsThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("msgHead", "SMS sent successfully");
        map.put("msgBody", sms);

        return new Gson().toJson(map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sendCustomSms.erp")
    public
    @ResponseBody
    String sendCustomSms(@RequestParam Long custSmsId, @RequestParam String customSmsText) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("PageTitle", "Insert MR");
        map.put("msg", "");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Customer customer = csdService.getCustomer(custSmsId);
        String cellNo = "01717659287";
        cellNo = customer.getCellPhone();

        FollowUpHistory history = new FollowUpHistory();
        history.setDate(new Date());
        history.setAttentionOn(new Date());
        history.setActive(false);
        history.setCalledBy(SessionUtil.getSessionUser());
        history.setClientsComment("--");
        history.setRemarks("<b>SMS sent Apt. Info.</b><br/>" + customSmsText);
        Customer customer1 = csdService.getCustomer(custSmsId);
        history.setCustomer(customer1);
        customer1.getHistoryList().add(history);
        csdService.save(history);


        try {
            SmsThread smsThread = new SmsThread(cellNo, customSmsText);
            smsThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("msgHead", "SMS sent successfully");
        map.put("msgBody", customSmsText);

        return new Gson().toJson(map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sendDueInfoSms.erp")
    public
    @ResponseBody
    String sendDueInfoSms(@RequestParam Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Payment Report");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        Customer customer = csdService.getCustomer(id);
        Double dueDp = 0.0;
        Double dueSp = 0.0;
        Double dueCp = 0.0;
        Double dueInstAmount = 0.0;
        String cellNo = "01717659287";

        if (customer != null) {
            Double payableInstAmount = csdService.getPayableInstallmentAmount(customer);
            Double paidInstAmount = customer.getMoneyDisburse().getInstallment();
            dueInstAmount = payableInstAmount - paidInstAmount;

            Double payableDp = csdService.getPayableOtherPaymentAmount(customer, "Down Payment");
            Double paidDp = customer.getMoneyDisburse().getDownPayment();
            dueDp = payableDp - paidDp;

            Double payableSp = csdService.getPayableOtherPaymentAmount(customer, "Special Payment");
            Double paidSp = customer.getMoneyDisburse().getSpecialPayment();
            dueSp = payableSp - paidSp;

            Double payableCp = csdService.getPayableOtherPaymentAmount(customer, "Car Park");
            Double paidCp = customer.getMoneyDisburse().getCarPark();
            dueCp = payableCp - paidCp;
/*
            map.put("dueDp", dueDp);
            map.put("dueSp", dueSp);
            map.put("dueCp", dueCp);
            map.put("dueInstAmount", dueInstAmount);*/

            cellNo = customer.getCellPhone();
            map.put("cid", customer.getCID());

        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(new Date());

        String inst = dueInstAmount > 0.0 ? "Inst." + dueInstAmount : "";
        String dp = dueDp > 0.0 ? "DP." + dueDp : "";
        String cp = dueCp > 0.0 ? "CP." + dueCp : "";

        String sms = "Dear Sir/Madam,\n" +
                "Assalamu Alaykum.\n" +
                "Your current due is Tk." + (dueCp + dueDp + dueSp + dueInstAmount) + "/-\n" +
                "(" + inst + " " + dp + " " + cp + ").\n" +
                "Till " + date + ".\n" +
                "ID: " + customer.getCID() + "\n" +
                "Name: " + customer.getName() +
                //"Check ur email for more info." +
                "";

        FollowUpHistory history = new FollowUpHistory();
        history.setDate(new Date());
        history.setAttentionOn(new Date());
        history.setActive(false);
        history.setCalledBy(SessionUtil.getSessionUser());
        history.setClientsComment("--");
        history.setRemarks("<b>SMS sent : Due Info.</b><br/>" + sms);
        Customer customer1 = csdService.getCustomer(id);
        history.setCustomer(customer1);
        customer1.getHistoryList().add(history);
        csdService.save(history);


        try {
            SmsThread smsThread = new SmsThread(cellNo, sms);
            smsThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("msgHead", "SMS sent successfully");
        map.put("msgBody", sms);

        return new Gson().toJson(map);
    }


    //View Payment Summery ALL
    @RequestMapping(method = RequestMethod.GET, value = "/viewPaymentSummaryAll.erp")
    public ModelAndView viewPaymentSummaryAll(@RequestParam("range") String range) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Statement");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        String[] array = range.split("-");
        if (array.length == 1 && array[0].toUpperCase().equals("ALL")) {
            map.put("reportHead", "Payment Summary");
        } else if (array.length == 1) {
            if (range.startsWith("=")) {
                map.put("reportHead", "Payment Summary : Paid " + range + "/-");
            } else if (range.startsWith(">")) {
                map.put("reportHead", "Payment Summary : Paid greater than " + range + "/-");
            } else if (range.startsWith("<")) {
                map.put("reportHead", "Payment Summary : Paid less than " + range + "/-");
            } else {
                map.put("reportHead", "Payment Summary : Paid up to BDT." + range);
            }
        } else {
            map.put("reportHead", "Payment Summary : Paid between BDT. " + array[0] + " - BDT. " + array[1]);
        }

        map.put("summaries", csdService.getAllPaymentSummary(range));
        return new ModelAndView("csd/report/payment_summary_all", map);
    }

    //viewOverdueReport
    @RequestMapping(method = RequestMethod.GET, value = "/viewOverdueReport.erp")
    public ModelAndView viewOverdueReport() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Statement");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return null;
    }

    //viewPaymentStatement
    @RequestMapping(method = RequestMethod.GET, value = "/viewPaymentStatement.erp")
    public ModelAndView viewPaymentStatement(@RequestParam("cid") String cid) {
        String fileNo = "";
        if (cid.startsWith("D")) {
            fileNo = Integer.parseInt(cid.trim().substring(3, cid.length()).trim()) + "";
        } else {
            fileNo = Integer.parseInt(cid.trim()) + "";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fileNo", fileNo);
        map.put("PageTitle", "Statement");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        if (cid.toUpperCase().startsWith("DPL")) {
            cid = cid.substring(3, cid.length());
        }
        try {
            DecimalFormat df = new DecimalFormat("000000");
            cid = "DPL " + df.format(Integer.parseInt(cid.trim()));
        } catch (NumberFormatException nfe) {
            map.put("errMsg", "Please insert only CID");
            return new ModelAndView("csd/report/payment_statement", map);
        }

        Customer customer = csdService.getCustomer(cid);
        if (customer != null) {
            Double totalCr = csdService.getTotalAdjustmentCr(customer) + csdService.getTotalByMoneyReceiptByMR(customer, "Passed");
            Double totalDr = csdService.getTotalAdjustmentDr(customer) + csdService.getTotalWithdraw(customer);
            map.put("customerName", customer.getName());
            map.put("cid", customer.getCID());
            map.put("paymentList", csdService.getAllReceiptAndVouchers(customer));
            map.put("totalDr", (totalDr == 0) ? "---" : totalDr);
            map.put("totalCr", (totalCr == 0) ? "---" : totalCr);
            map.put("totalDeposit", (totalCr - totalDr < 0) ? "(" + (-(totalCr - totalDr)) + ")" : (totalCr - totalDr));
            map.put("errMsg", "");
        } else {
            map.put("errMsg", "No such customer found");
        }

        map.put("customer", customer);
        map.put("today", new TheDates().getTodaySmart());
        map.put("cid", cid);
        return new ModelAndView("csd/report/payment_statement", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/viewInstallmentStatement.erp")
    public ModelAndView viewInstallmentStatement(@RequestParam("cid") String cid) {
        String fileNo = "";
        if (cid.startsWith("D")) {
            fileNo = Integer.parseInt(cid.trim().substring(3, cid.length()).trim()) + "";
        } else {
            fileNo = Integer.parseInt(cid.trim()) + "";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fileNo", fileNo);
        map.put("PageTitle", "Installment Statement");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        if (cid.toUpperCase().startsWith("DPL")) {
            cid = cid.substring(3, cid.length());
        }
        try {
            DecimalFormat df = new DecimalFormat("000000");
            cid = "DPL " + df.format(Integer.parseInt(cid.trim()));
        } catch (NumberFormatException nfe) {
            map.put("errMsg", "Please insert only CID");
            return new ModelAndView("csd/report/payment_statement", map);
        }

        Customer customer = csdService.getCustomer(cid);
        if (customer != null) {
            List<Installment> installmentList = customer.getInstallments();
            map.put("installmentList", installmentList);
            Double payable = csdService.getPayableInstallmentAmount(customer);
            Double paid = customer.getMoneyDisburse().getInstallment();
            map.put("payable", payable);
            map.put("paid", paid);
            map.put("today", new Date());
            map.put("errMsg", "");
        } else {
            map.put("errMsg", "No such customer found");
        }

        map.put("customer", customer);
        map.put("today", new TheDates().getTodaySmart());
        map.put("cid", cid);
        return new ModelAndView("csd/report/installment_statement", map);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/salesReport1.erp")
    public ModelAndView salesReport1() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "References");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        List<Customer> customers = csdService.findAllCustomer();
        Integer soldArea = 0;
        int soldApartment = 0;
        Double salesValue = 0.0;
        Double collectedAmount = 0.0;
        Double overDue = 0.0;

        int approved = 0;
        int cancelled = 0;
        int refunded = 0;
        int pending = 0;
        for (Customer customer : customers) {
            soldArea += customer.getFloorSize();
            soldApartment++;
            salesValue += customer.getPrice() * customer.getFloorSize();
            collectedAmount += csdService.getTotalDeposit(customer);
            approved += customer.getStatus().toUpperCase().equals("APPROVED") ? 1 : 0;
            cancelled += customer.getStatus().toUpperCase().equals("CANCELLED") ? 1 : 0;
            refunded += customer.getStatus().toUpperCase().equals("REFUNDED") ? 1 : 0;
            pending += customer.getStatus().toUpperCase().equals("PENDING") ? 1 : 0;
            overDue += csdService.getPayableInstallmentAmount(customer) + csdService.getPayableOtherPaymentAmount(customer, "ALL");
        }
        map.put("soldArea", soldArea);
        map.put("soldApartment", soldApartment);
        map.put("salesValue", salesValue);
        map.put("avgPrice", salesValue / soldArea);
        map.put("collectedAmount", collectedAmount);
        map.put("dueAmount", salesValue - collectedAmount);
        map.put("overDue", overDue - collectedAmount);

        map.put("approved", approved);
        map.put("cancelled", cancelled);
        map.put("refunded", refunded);
        map.put("pending", pending);

        return new ModelAndView("/csd/report/sales_report_1", map);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/salesReport2.erp")
    public ModelAndView salesReport2() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "References");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        map.put("statusSales", csdService.getSalesCountListByStatus());
        map.put("offerSales", csdService.getSalesCountListByOffer());

        Date lastBodMeetingDate = adminService.getLastBoardMeetingDate();
        map.put("lastBodMeetingDate", lastBodMeetingDate);

        return new ModelAndView("/csd/report/sales_report_2", map);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/salesReport3.erp")
    public ModelAndView salesReport3() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "References");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        map.put("personSales", csdService.getSalesCountListByMID());

        return new ModelAndView("/csd/report/sales_report_3", map);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/salesReport4.erp")
    public ModelAndView salesReport4() {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("PageTitle", "Due Report");
        param.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        List<Customer> customerList = csdService.findAllCustomer();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for (Customer customer : customerList) {
            map = new HashMap<String, Object>();
            Double payableInstAmount = csdService.getPayableInstallmentAmount(customer);
            Double totalInstAmount = csdService.getTotalInstallmentAmount(customer);
            Double paidInstAmount = customer.getMoneyDisburse().getInstallment();
            Double totalInst = csdService.getTotalInstallment(customer);
            Double payableInst = csdService.getPayableInstallment(customer);
            Double amountPerInst = totalInstAmount / totalInst;
            Double paidInst = paidInstAmount / amountPerInst;
            Double dueInst = payableInst - paidInst;
            Double dueInstAmount = payableInstAmount - paidInstAmount;


            map.put("customer", customer);
            map.put("price", csdService.getFloorPrice(customer));
            map.put("paid", csdService.getTotalDeposit(customer));
            map.put("dueInst", dueInst);
            map.put("instDueAmount", csdService.getPayableInstallmentAmount(customer));
            map.put("otherDueAmount", csdService.getPayableOtherPaymentAmount(customer, "ALL"));
            list.add(map);
        }

        param.put("dataList", list);

        return new ModelAndView("/csd/report/sales_report_4", param);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/salesReport5.erp")
    public ModelAndView salesReport5(@RequestParam String from, @RequestParam String to) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("PageTitle", "Collection");
        param.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (from.equals("today") || to.equals("today")) {
            param.put("salesList", csdService.getCollectionReport(sdf.format(new Date()), sdf.format(new Date())));
            param.put("reportOf", "Showing Result of TODAY");
        } else if (from.equals("thisMonth") || to.equals("thisMonth")) {
            param.put("salesList", csdService.getCollectionReport(sdf.format(new TheDates().getFirstDayOfMonth()), sdf.format(new Date())));// csdService.getCollectionReport(sdf.format(new Date()), sdf.format(new Date())));
            param.put("reportOf", "Showing Result of This Month");
        } else {
            param.put("salesList", csdService.getCollectionReport(from, to));
            param.put("reportOf", "Showing Result from " + from + " to " + to);
        }
        return new ModelAndView("/csd/report/collection_date_wise", param);

    }


    @RequestMapping(method = RequestMethod.GET, value = "/salesReport6.erp")
    public ModelAndView salesReport6() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "References");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        Date lastBodMeetingDate = adminService.getLastBoardMeetingDate();

        List<Object[]> paymentLogReport = csdService.getDisbursementLogReport(lastBodMeetingDate, new Date());
        List<Map<String, Object>> logList = new ArrayList<Map<String, Object>>();
        Map<String, Object> logMap;
        for (Object[] objArr : paymentLogReport) {
            logMap = new HashMap<String, Object>();
            logMap.put("head", objArr[0]);
            logMap.put("overDue", objArr[1]);
            logMap.put("regularDue", objArr[2]);
            logMap.put("advanced", objArr[3]);
            logList.add(logMap);
        }
        map.put("logList", logList);
        map.put("lastBodMeetingDate", lastBodMeetingDate);

        return new ModelAndView("/csd/report/sales_report_6", map);

    }

    //findPaymentSummary
    @RequestMapping(method = RequestMethod.GET, value = "/findPaymentSummary.erp")
    public ModelAndView findPaymentSummary(@RequestParam("cid") String cid) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (cid.toUpperCase().startsWith("DPL")) {
            cid = cid.substring(3, cid.length());
        }
        DecimalFormat df = new DecimalFormat("000000");
        cid = "DPL " + df.format(Integer.parseInt(cid.trim()));

        Customer customer = csdService.getCustomer(cid);
        map.put("PageTitle", "Payment Summary");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("customerName", customer.getName());
        map.put("cid", customer.getCID());

        List<MoneyReceipt> moneyReceipts = customer.getMoneyReceipts();
        Double deposit = 0.0;
        for (MoneyReceipt mr : moneyReceipts) {
            deposit = deposit + mr.getAmount();
        }
        //Calculating Adjustment deposit
        Double adjustmentCr = 0.0;
        Double adjustmentDr = 0.0;
        Double withdraw = 0.0;
        String vType = "";
        List<Voucher> vouchers = customer.getVouchers();
        for (Voucher vr : vouchers) {
            Double amount = vr.getAmount();
            vType = (vr.getClass().getSimpleName().equals("AdjustmentVoucher")) ? "Adjustment" : "Withdraw";
            if (vType.equals("Adjustment") && vr.getStatus().equals("Approved")) {
                if (amount >= 0)
                    adjustmentCr = adjustmentCr + amount;
                else if (amount < 0)
                    adjustmentDr = adjustmentDr + amount;
            } else if (vType.equals("Withdraw") && vr.getStatus().equals("Approved")) {
                withdraw = withdraw + amount;
            }
        }

        Double totalAdjustment = adjustmentCr + adjustmentDr;
        Double totalDr = -(adjustmentDr + withdraw);
        Double totalCr = (adjustmentCr + deposit);
        Double floorPrice = csdService.getFloorPrice(customer);
        /*//Calculate total price = all other payments + all installment amount
        List<OtherPayment> otherPayments = customer.getOtherPayments();
        for (OtherPayment op : otherPayments) {
            floorPrice = floorPrice + op.getAmount();
        }

        //Add total inst amount
        List<Installment> installments = customer.getInstallments();
        for (Installment inst : installments) {
            floorPrice = floorPrice + inst.getAmount();
        }*/

        map.put("adjustmentCr", (adjustmentCr == 0) ? "---" : adjustmentCr);
        map.put("adjustmentDr", (adjustmentDr == 0) ? "---" : -adjustmentDr);
        map.put("withdraw", (withdraw == 0) ? "---" : -withdraw);
        map.put("deposit", (deposit == 0) ? "---" : deposit);
        map.put("floorPrice", (floorPrice == 0) ? "---" : floorPrice);
        map.put("restAmount", ((floorPrice - deposit) == 0) ? "---" : (floorPrice - deposit));
        map.put("totalAdjustment", (totalAdjustment == 0) ? "---" : totalAdjustment);
        map.put("totalDr", (totalDr == 0) ? "---" : totalDr);
        map.put("totalCr", (totalCr == 0) ? "---" : totalCr);
        map.put("grandTotal", ((totalCr - totalDr) == 0) ? "---" : (totalCr - totalDr));


        return new ModelAndView("csd/report/payment_summary", map);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/paymentStatementJsonData.erp") //moneyReceiptJsonData
    public ModelAndView paymentStatementJsonData(@RequestParam("theCallbackFunction") String cid) {
        int start = 0;
        int limit = 15;
        //@RequestParam("cide") String cid
        Map<String, Object> listMap = null;
        Map<String, Object> map = new HashMap<String, Object>();
        List<MoneyReceipt> allData = csdService.findAllMoneyReceipt();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (MoneyReceipt data : allData) {
            listMap = new HashMap<String, Object>();
            listMap.put("id", data.getId());
            listMap.put("receiptNo", data.getReceiptNo());
            listMap.put("receiptDate", data.getReceiptDate());
            listMap.put("amount", data.getAmount());
            listMap.put("cid", data.getCustomer().getCID());
            listMap.put("name", data.getCustomer().getName());
            listMap.put("paymentMethod", data.getPaymentMethod());
            listMap.put("note", data.getNote());
            listMap.put("cashDate", data.getCashDate());
            listMap.put("entryDate", data.getEntryDate());
            listMap.put("signature", data.getSignature());
            //listMap.put("operator", data.getOperator());
            mapList.add(listMap);
        }
        //map.put("cids", "Hello");
        map.put("receiptList", mapList.subList(start, start + limit > mapList.size() ?
                mapList.size() : start + limit));
        map.put("totalCount", mapList.size());


        return new ModelAndView(new JSONView(), map);
    }

    //viewCustomerByMid
    @RequestMapping(method = RequestMethod.GET, value = "/viewCustomerByMid.erp")
    public ModelAndView viewCustomerByMid() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "References");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("reportHead", "Report : References");
        map.put("customers", csdService.getAllCustomerByMidType("all"));

        return new ModelAndView("csd/report/customer_all_by_mid", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/viewCustomerByName.erp")
    public ModelAndView viewCustomerByName(@RequestParam("customerName") String name) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Customers");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        map.put("customerList", csdService.getAllCustomerByName(name));
        map.put("status", "All");
        return new ModelAndView("csd/report/customer_find", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customerListByStatus.erp")
    public ModelAndView customerListByStatus(@RequestParam("status") String status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Customer");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        map.put("customerList", csdService.findAllCustomer());
        map.put("status", status);

        return new ModelAndView("csd/report/customer_find", map);
    }


    //Money Disburse All  report
    @RequestMapping(method = RequestMethod.GET, value = "/viewMoneyDisburse.erp")
    public ModelAndView viewMoneyDisburse(@RequestParam("findFor") String findFor) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Money Disbursement");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        if (findFor.equals("not")) {
            map.put("reportHead", "List : Not disbursed yet");
        } else if (findFor.equals("all")) {
            map.put("reportHead", "All");
        } else {
            map.put("reportHead", "Sorry");
        }

        map.put("disburses", csdService.findAllMoneyDisburses(findFor));
        return new ModelAndView("csd/report/money_disburse", map);
    }

    //Money Disburse All
    @RequestMapping(method = RequestMethod.GET, value = "/moneyDisburseList.erp")
    public ModelAndView moneyDisburseList(@RequestParam("findFor") String findFor) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Money Disbursement");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        if (findFor.toUpperCase().equals("NOT")) {
            map.put("reportHead", "List : Not disbursed yet");
        } else if (findFor.toUpperCase().equals("ALL")) {
            map.put("reportHead", "All");
        } else {
            if (findFor.toUpperCase().startsWith("DPL")) {
                findFor = findFor.substring(3, findFor.length());
            }
            DecimalFormat df = new DecimalFormat("000000");
            findFor = "DPL " + df.format(Integer.parseInt(findFor.trim()));
            map.put("reportHead", "Search result for " + csdService.getCustomer(findFor).getName());
        }
        map.put("findFor", findFor.toUpperCase());
        map.put("disburses", csdService.findAllMoneyDisbursesSummary(findFor));
        return new ModelAndView("csd/money_disburse_list", map);
    }

    //Edit Money Disburse
    @RequestMapping(method = RequestMethod.GET, value = "/editMoneyDisburse.erp")
    public ModelAndView editMoneyDisburse(@RequestParam("cid") String cid, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        Customer customer = csdService.getCustomer(cid);
        MoneyDisburse moneyDisburse = customer.getMoneyDisburse();
        map.put("PageTitle", cid + " : " + customer.getName());
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        PaymentDetailBean pdb = new PaymentDetailBean();

        pdb.setDisbursementId(customer.getMoneyDisburse().getId());
        pdb.setCid(cid);

        pdb.setTotalDp(csdService.getTotalOtherPaymentAmount(customer, "Down Payment"));
        pdb.setTotalSp(csdService.getTotalOtherPaymentAmount(customer, "Special Payment"));
        pdb.setTotalCp(csdService.getTotalOtherPaymentAmount(customer, "Car Park"));
        pdb.setTotalOp(csdService.getTotalOtherPaymentAmount(customer, "Other Payment"));
        pdb.setTotalInst(csdService.getTotalInstallmentAmount(customer));

        pdb.setPayableDp(csdService.getPayableOtherPaymentAmount(customer, "Down Payment"));
        pdb.setPayableSp(csdService.getPayableOtherPaymentAmount(customer, "Special Payment"));
        pdb.setPayableCp(csdService.getPayableOtherPaymentAmount(customer, "Car Park"));
        pdb.setPayableOp(csdService.getPayableOtherPaymentAmount(customer, "Other Payment"));
        pdb.setPayableInst(csdService.getPayableInstallmentAmount(customer));

        pdb.setDisburseDp(moneyDisburse.getDownPayment());
        pdb.setDisburseSp(moneyDisburse.getSpecialPayment());
        pdb.setDisburseCp(moneyDisburse.getCarPark());
        pdb.setDisburseOp(moneyDisburse.getOtherPayment());
        pdb.setDisburseInst(moneyDisburse.getInstallment());

        pdb.setPaidTotal(csdService.getTotalDeposit(customer));
        pdb.setDisbursedTotal(csdService.getTotalDisburse(customer));

        map.put("pdb", pdb);
        session.setAttribute(MirrorDataList.PAYMENT_DETAIL_BEAN, pdb);
        return new ModelAndView("csd/money_disburse_edit", map);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/updateMoneyDisburse.erp")
    public ModelAndView updateMoneyDisburse(@RequestParam("id") Long id,
                                            @RequestParam("cid") String cid,
                                            @RequestParam("instOld") Double instOld,
                                            @RequestParam(value = "instNew", required = false, defaultValue = "0.0") Double instNew,
                                            @RequestParam("dpOld") Double dpOld,
                                            @RequestParam(value = "dpNew", required = false, defaultValue = "0.0") Double dpNew,
                                            @RequestParam("spOld") Double spOld,
                                            @RequestParam("spNew") Double spNew,
                                            @RequestParam("cpOld") Double cpOld,
                                            @RequestParam("cpNew") Double cpNew,
                                            @RequestParam("opOld") Double opOld,
                                            @RequestParam("opNew") Double opNew,
                                            @RequestParam("rest") Double rest,
                                            HttpServletRequest request) {
        HttpSession session = request.getSession();
        PaymentDetailBean pdb = (PaymentDetailBean) session.getAttribute(MirrorDataList.PAYMENT_DETAIL_BEAN);
        Map<String, String> map = new HashMap<String, String>();
        map.put("PageTitle", "Money Disburse");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        MoneyDisburse md = new MoneyDisburse();
        md.setId(id);
        md.setInstallment(instOld + instNew);
        md.setDownPayment(dpOld + dpNew);
        md.setSpecialPayment(spOld + spNew);
        md.setCarPark(cpOld + cpNew);
        md.setOtherPayment(opOld + opNew);

        if (rest == 0.0) {
            md.setUpdate("Yes");
            csdService.update(md);
        } else if (rest > 0.0) {
            md.setUpdate("No");
            csdService.update(md);
        }


        DisbursementLog log;
        if (dpNew != 0.0) {
            log = makeDisbursementLog(MirrorConstants.PAYMENT_HEAD_DP[1], pdb.getPayableDp() - pdb.getDisburseDp(), dpNew);
            if (log.getTotal() != 0)
                csdService.save(log);
        }
        if (spNew != 0.0) {
            log = makeDisbursementLog(MirrorConstants.PAYMENT_HEAD_SP[1], pdb.getPayableSp() - pdb.getDisburseSp(), spNew);
            if (log.getTotal() != 0)
                csdService.save(log);
        }
        if (cpNew != 0.0) {
            log = makeDisbursementLog(MirrorConstants.PAYMENT_HEAD_CP[1], pdb.getPayableCp() - pdb.getDisburseCp(), cpNew);
            if (log.getTotal() != 0)
                csdService.save(log);
        }
        if (opNew != 0.0) {
            log = makeDisbursementLog(MirrorConstants.PAYMENT_HEAD_OP[1], pdb.getPayableOp() - pdb.getDisburseOp(), opNew);
            if (log.getTotal() != 0)
                csdService.save(log);
        }

        if (instNew != 0.0) {
            Customer customer = csdService.getCustomer(cid);
//            Double instAmt = customer.getTotalInstAmt() / customer.getTotalInst();
            //TODO.. add more intelligence
            log = new DisbursementLog();
            log.setCrDr("");
            log.setHead(MirrorConstants.PAYMENT_HEAD_INST[1]);
            log.setRecordDate(new Date());

            log.setRDP(0.0);
            log.setODP(0.0);
            log.setADP(0.0);
            if (customer.getDueInst() <= 0) {
                log.setADP(instNew);
            } else if (customer.getDueInst() <= 1) {
                log.setRDP(instNew);
            } else if (customer.getDueInst() > 1) {
                log.setODP(instNew);
            }
            if (log.getTotal() != 0)
                csdService.save(log);
        }

        return new ModelAndView("redirect:/csd/moneyDisburseList.erp?findFor=not", map);
    }

    private static DisbursementLog makeDisbursementLog(String head, Double dueAmt, Double newAmt) {
        Double rdp = 0.0;
        Double odp = 0.0;
        Double adp = 0.0;
        if (dueAmt <= 0.0) {
            adp = newAmt;
        } else if (dueAmt < newAmt) {
            odp = dueAmt;
            adp = newAmt - dueAmt;
        } else if (dueAmt > newAmt) {
            odp = newAmt;
        }

        DisbursementLog log = new DisbursementLog();
        log.setCrDr("");
        log.setHead(head);
        log.setRecordDate(new Date());
        log.setRDP(rdp);
        log.setODP(odp);
        log.setADP(adp);
        return log;

    }

    //Project start ................
    @RequestMapping(method = RequestMethod.GET, value = "/projectList.erp")
    public ModelAndView projectList() {
        Map<String, Object> buildingMap = new HashMap<String, Object>();
        buildingMap.put("PageTitle", "Project List");
        buildingMap.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("/csd/project_list", buildingMap);
    }

    //Project Json Data
    @RequestMapping(method = RequestMethod.GET, value = "/projectJsonData.erp")
    public ModelAndView projectJsonData() {
        int start = 0;
        int limit = 15;

        Map<String, Object> listMap = null;
        Map<String, Object> map = new HashMap<String, Object>();
        List<Project> allData = csdService.findAllProject();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (Project data : allData) {
            listMap = new HashMap<String, Object>();
            listMap.put("id", data.getId());
            listMap.put("projectName", data.getProjectName());
            listMap.put("location", data.getLocation());
            listMap.put("address", data.getAddress());
            listMap.put("hotLine", data.getHotLine());
            listMap.put("numberOfBuilding", data.getNumberOfBuilding());
            listMap.put("landArea", data.getLandArea());
            mapList.add(listMap);
        }

        map.put("projectList", mapList.subList(start, start + limit > mapList.size() ?
                mapList.size() : start + limit));
        map.put("totalCount", mapList.size());
        return new ModelAndView(new JSONView(), map);
    }
    /*
    @RequestMapping(method = RequestMethod.GET, value = "/createBuilding.erp")
    public ModelAndView createBuilding() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Create Building");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        List<Project> projects = csdService.findAllProject();
        map.put("projects", projects);

        return new ModelAndView("/csd/building_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveBuilding.erp")
    public ModelAndView saveBuilding(@RequestParam("buildingName") String buildingName,
                                     @RequestParam("floorSize") int floorSize,
                                     @RequestParam("numberOfFloor") int numberOfFloor,
                                     @RequestParam("numberOfUnit") int numberOfUnit,
                                     @RequestParam("landArea") int landArea,
                                     @RequestParam("pid") Long pid) {

        Project project = csdService.getProject(pid);

        Map<String, String> map = new HashMap<String, String>();
        Building building = new Building();
        building.setBuildingName(buildingName);
        building.setTotalFloorSize(floorSize);
        building.setNumberOfFloor(numberOfFloor);
        building.setNumberOfUnit(numberOfUnit);
        building.setLandArea(landArea);
        building.setProject(project);

        project.getBuildings().add(building);
        csdService.save(building);
        return new ModelAndView("redirect:/csd/buildingList.erp", map);
    }

    //editBuilding
    @RequestMapping(method = RequestMethod.GET, value = "/editBuilding.erp")
    public ModelAndView editBuilding(@RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        Building building = csdService.getBuilding(id);
        map.put("PageTitle", "Edit Building");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        map.put("id", id);
        map.put("buildingName", building.getBuildingName());
        map.put("floorSize", building.getTotalFloorSize());
        map.put("numberOfFloor", building.getNumberOfFloor());
        map.put("numberOfUnit", building.getNumberOfUnit());
        map.put("landArea", building.getLandArea());
        map.put("projectName", building.getProject().getProjectName());
        return new ModelAndView("/csd/building_update", map);
    }

    //updateBuilding
    @RequestMapping(method = RequestMethod.POST, value = "/updateBuilding.erp")
    public ModelAndView updateBuilding(@RequestParam("id") Long id,
                                       @RequestParam("leaveType") String leaveType,
                                       @RequestParam("leaveDuration") int leaveDuration,
                                       @RequestParam("leaveNotes") String leaveNotes) {
        Map<String, String> map = new HashMap<String, String>();
        Leave leave = new Leave();
        Building building = new Building();
        leave.setId(id);
        leave.setLeaveType(leaveType);
        leave.setLeaveDuration(leaveDuration);
        leave.setLeaveNotes(leaveNotes);

        csdService.update(building);
        return new ModelAndView("redirect:/leave/leaveList.erp", map);
    }

     */

    //Voucher  start
    //================================================================================================================

    @RequestMapping(method = RequestMethod.GET, value = "createVoucher.erp")
    public ModelAndView createVoucher() {
        Map<String, String> createRegularIncentiveMap = new HashMap<String, String>();
        createRegularIncentiveMap.put("PageTitle", "Add Voucher");

        return new ModelAndView("csd/voucher_create", createRegularIncentiveMap);
    }

    //
    @RequestMapping(method = RequestMethod.POST, value = "saveVoucher.erp")
    public ModelAndView saveVoucher(@RequestParam("voucherNo") String voucherNo,
                                    @RequestParam("voucherDate") String date,
                                    @RequestParam("cidFrom") String cidFrom,
                                    @RequestParam("cidTo") String cidTo,
                                    @RequestParam("type") String type,
                                    @RequestParam("note") String note,
                                    @RequestParam("amount") Double amount) {
        //Making CID Format like "DPL 000125"
        DecimalFormat df = new DecimalFormat("000000");
        if (cidFrom.toUpperCase().startsWith("DPL")) {
            cidFrom = cidFrom.substring(3, cidFrom.length());
        }
        cidFrom = "DPL " + df.format(Integer.parseInt(cidFrom.trim()));

        if (type.equals("adjustment")) {
            if (cidTo.toUpperCase().startsWith("DPL")) {
                cidTo = cidTo.substring(3, cidTo.length());
            }
            cidTo = "DPL " + df.format(Integer.parseInt(cidTo.trim()));
        }
        //------------------------------------------------------------

        Map<String, String> map = new HashMap<String, String>();
        map.put("PageTitle", "Add Incentive");
        Customer customer = null;
        Voucher voucher = null;

        if (type.equals("adjustment")) {
            voucher = new AdjustmentVoucher();
            customer = csdService.getCustomer(cidFrom);
            voucher.setVoucherNo(voucherNo.toUpperCase() + "/" + Integer.parseInt(cidTo.substring(4, 10)));
            voucher.setVoucherDate(new TheDates().toDate(date));
            voucher.setAmount(-amount);
            voucher.setCustomer(customer);
            voucher.setNote(note);
            //TODO... Set "Processing" instead of "Approved" for checking through A/C head
            voucher.setStatus("Approved");
            voucher.setApprovedBy(new CurrentUserCredentials().user);
            voucher.setOperator(new CurrentUserCredentials().user);
            customer.getVouchers().add(voucher);
            csdService.save(voucher);

            voucher = new AdjustmentVoucher();
            customer = csdService.getCustomer(cidTo);
            voucher.setVoucherNo(voucherNo.toUpperCase() + "/" + Integer.parseInt(cidFrom.substring(4, 10)));
            voucher.setVoucherDate(new TheDates().toDate(date));
            voucher.setAmount(amount);
            voucher.setCustomer(customer);
            voucher.setNote(note);
            voucher.setOperator(new CurrentUserCredentials().user);
            //Set "Processing" instead of "Approved" for checking through A/C head
            voucher.setStatus("Approved");
            voucher.setApprovedBy(new CurrentUserCredentials().user);
            customer.getVouchers().add(voucher);
            csdService.save(voucher);
        } else if (type.equals("withdraw")) {
            voucher = new WithdrawVoucher();
            customer = csdService.getCustomer(cidFrom);
            voucher.setVoucherNo(voucherNo);
            voucher.setVoucherDate(new TheDates().toDate(date));
            voucher.setAmount(-amount);
            voucher.setNote(note);
            voucher.setOperator(new CurrentUserCredentials().user);
            //Set "Processing" instead of "Approved" for checking through A/C head
            voucher.setStatus("Approved");
            voucher.setApprovedBy(new CurrentUserCredentials().user);
            voucher.setCustomer(customer);
            customer.getVouchers().add(voucher);
            csdService.save(voucher);
        }
        return new ModelAndView("redirect:/csd/createVoucher.erp", map);
    }


    //Not Approved Voucher List
    @RequestMapping(method = RequestMethod.GET, value = "/voucherList.erp")
    public ModelAndView voucherList() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Voucher List (Not Approved)");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        List<Voucher> voucherList = csdService.findAllVoucher();

        map.put("voucherList", voucherList);

        return new ModelAndView("csd/voucher_list", map);     //this is .ftl file in which the list will be showed
    }

    //Not Approved Voucher List
    @RequestMapping(method = RequestMethod.GET, value = "/voucherListNa.erp")
    public ModelAndView voucherListNa() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Voucher List (Not Approved)");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("csd/voucher_list_na", map);     //this is .ftl file in which the list will be showed
    }

    @RequestMapping(method = RequestMethod.GET, value = "/voucherListNaJsonData.erp")
    public ModelAndView voucherListNaJsonData() {
        int start = 0;
        int limit = 15;

        Map<String, Object> listMap = null;
        Map<String, Object> map = new HashMap<String, Object>();
        List<Voucher> vouchers = csdService.getVoucherList(MirrorConstants.PROCESSING);
        List<Map<String, Object>> incentiveMapList = new ArrayList<Map<String, Object>>();
        for (Voucher voucher : vouchers) {
            listMap = new HashMap<String, Object>();
            listMap.put("id", voucher.getId());
            listMap.put("voucherNo", voucher.getVoucherNo());
            listMap.put("voucherDate", voucher.getVoucherDate());
            listMap.put("amount", (voucher.getAmount() < 0) ? -voucher.getAmount() : voucher.getAmount());
            listMap.put("drCr", (voucher.getAmount() < 0) ? "Dr" : "Cr");
            listMap.put("note", voucher.getNote());
            listMap.put("operator", voucher.getOperator().getToken().getName());
            listMap.put("status", voucher.getStatus());
            listMap.put("type",
                    (voucher.getClass().getSimpleName()
                            .equals("AdjustmentVoucher")) ? "Adjustment" : "Withdraw");

            incentiveMapList.add(listMap);
        }

        map.put("voucherListNa", incentiveMapList.subList(start, start + limit > incentiveMapList.size() ?
                incentiveMapList.size() : start + limit));
        map.put("totalCount", incentiveMapList.size());

        return new ModelAndView(new JSONView(), map);
    }

    //approveVoucher
    @RequestMapping(method = RequestMethod.GET, value = "/approveVoucher.erp")
    public ModelAndView approveVoucher(@RequestParam("id") Long id) {
        Map<String, String> map = new HashMap<String, String>();
        Voucher voucher = csdService.getVoucher(id);
        voucher.setId(id);
        voucher.setStatus("Approved");
        voucher.setApprovedBy(new CurrentUserCredentials().user);
        csdService.update(voucher);
        return new ModelAndView("redirect:/csd/voucherListNa.erp", map);
    }

    //Approved Voucher List
    @RequestMapping(method = RequestMethod.GET, value = "/voucherListAp.erp")
    public ModelAndView voucherListAp() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Voucher List (Approved)");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("csd/voucher_list_ap", map);     //this is .ftl file in which the list will be showed
    }

    @RequestMapping(method = RequestMethod.GET, value = "/voucherListApJsonData.erp")
    public ModelAndView voucherListApJsonData() {
        int start = 0;
        int limit = 15;

        Map<String, Object> listMap = null;
        Map<String, Object> map = new HashMap<String, Object>();
        List<Voucher> vouchers = csdService.getVoucherList("Approved");
        List<Map<String, Object>> incentiveMapList = new ArrayList<Map<String, Object>>();
        for (Voucher voucher : vouchers) {
            listMap = new HashMap<String, Object>();
            listMap.put("id", voucher.getId());
            listMap.put("voucherNo", voucher.getVoucherNo());
            listMap.put("voucherDate", voucher.getVoucherDate());
            listMap.put("amount", (voucher.getAmount() < 0) ? -voucher.getAmount() : voucher.getAmount());
            listMap.put("drCr", (voucher.getAmount() < 0) ? "Dr" : "Cr");
            listMap.put("note", voucher.getNote());
            listMap.put("approvedBy", voucher.getApprovedBy().getToken().getName());
            listMap.put("status", voucher.getStatus());
            listMap.put("type",
                    (voucher.getClass().getSimpleName()
                            .equals("AdjustmentVoucher")) ? "Adjustment" : "Withdraw");

            incentiveMapList.add(listMap);
        }

        map.put("voucherListAp", incentiveMapList.subList(start, start + limit > incentiveMapList.size() ?
                incentiveMapList.size() : start + limit));
        map.put("totalCount", incentiveMapList.size());

        return new ModelAndView(new JSONView(), map);
    }

    //voucherListRe
    @RequestMapping(method = RequestMethod.GET, value = "/voucherListRe.erp")
    public ModelAndView voucherListRe() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Voucher List (Rejected)");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("csd/voucher_list_re", map);     //this is .ftl file in which the list will be showed
    }

    @RequestMapping(method = RequestMethod.GET, value = "/voucherListReJsonData.erp")
    public ModelAndView voucherListReJsonData() {
        int start = 0;
        int limit = 15;

        Map<String, Object> listMap = null;
        Map<String, Object> map = new HashMap<String, Object>();
        List<Voucher> vouchers = csdService.getVoucherList("Rejected");
        List<Map<String, Object>> incentiveMapList = new ArrayList<Map<String, Object>>();
        for (Voucher voucher : vouchers) {
            listMap = new HashMap<String, Object>();
            listMap.put("id", voucher.getId());
            listMap.put("voucherNo", voucher.getVoucherNo());
            listMap.put("voucherDate", voucher.getVoucherDate());
            listMap.put("amount", (voucher.getAmount() < 0) ? -voucher.getAmount() : voucher.getAmount());
            listMap.put("drCr", (voucher.getAmount() < 0) ? "Dr" : "Cr");
            listMap.put("note", voucher.getNote());
            listMap.put("rejectedBy", voucher.getApprovedBy().getToken().getName());
            listMap.put("reason", "Not Checked !");  // voucher.getReason());
            listMap.put("type",
                    (voucher.getClass().getSimpleName()
                            .equals("AdjustmentVoucher")) ? "Adjustment" : "Withdraw");

            incentiveMapList.add(listMap);
        }

        map.put("voucherListRe", incentiveMapList.subList(start, start + limit > incentiveMapList.size() ?
                incentiveMapList.size() : start + limit));
        map.put("totalCount", incentiveMapList.size());

        return new ModelAndView(new JSONView(), map);
    }

    //Booking Approve
    @RequestMapping(method = RequestMethod.GET, value = "/approveCustomer.erp")
    public ModelAndView approveCustomer(@RequestParam("id") Long id) {
        Map<String, String> map = new HashMap<String, String>();
        Customer customer = csdService.getCustomer(id);
        customer.setId(id);
        customer.setStatus("Approved");
        customer.setApprovedBy(new CurrentUserCredentials().user);
        csdService.update(customer);
        return new ModelAndView("redirect:/csd/customerListNa.erp", map);
    }

    //Not Approved Customer List
    @RequestMapping(method = RequestMethod.GET, value = "/customerListNa.erp")
    public ModelAndView customerListNa() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Application List (Not Approved)");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("csd/customer_list_na", map);     //this is .ftl file in which the list will be showed
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customerListNaJsonData.erp")
    public ModelAndView customerListNaJsonData() {
        int start = 0;
        int limit = 100015;

        Map<String, Object> listMap = null;
        Map<String, Object> map = new HashMap<String, Object>();
        List<Customer> customers = csdService.getCustomerListByStatus(MirrorConstants.PROCESSING);
        List<Map<String, Object>> incentiveMapList = new ArrayList<Map<String, Object>>();
        for (Customer customer : customers) {
            listMap = new HashMap<String, Object>();
            listMap.put("id", customer.getId());
            listMap.put("cid", customer.getCID());
            listMap.put("bookingDate", customer.getBookingDate());
            listMap.put("name", customer.getName());
            listMap.put("referredBy", customer.getReferenceBy().getName());
            listMap.put("floorSize", customer.getFloorSize());
            listMap.put("pps", customer.getPrice());
            listMap.put("paid", csdService.getTotalDeposit(customer.getCID()));
            listMap.put("handOver", customer.getHandoverDate());
            listMap.put("offer", customer.getOffer().getOfferName());

            incentiveMapList.add(listMap);
        }
        map.put("customerListNa", incentiveMapList.subList(start, start + limit > incentiveMapList.size() ?
                incentiveMapList.size() : start + limit));
        map.put("totalCount", incentiveMapList.size());

        return new ModelAndView(new JSONView(), map);
    }

    //Director
    @RequestMapping(method = RequestMethod.GET, value = "/createDirector.erp")
    public ModelAndView createDirector() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("PageTitle", "Create Director");
        map.put("errorMsg", "");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("/csd/director_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveDirector.erp")
    public ModelAndView saveDirector(@RequestParam("mid") String mid,
                                     @RequestParam("name") String name,
                                     @RequestParam("reference") String reference,
                                     @RequestParam("type") String type,
                                     @RequestParam("designation") String designation,
                                     @RequestParam("department") String department,
                                     @RequestParam("contactOne") String contactOne,
                                     @RequestParam("contactTwo") String contactTwo,
                                     @RequestParam("email") String email) {

        Map<String, String> map = new HashMap<String, String>();
        Director director = new Director();
        director.setMid(mid);
        director.setName(name.toUpperCase());
        director.setReference(reference);
        director.setType(type);
        director.setDesignation(designation);
        director.setDepartment(department);
        director.setContactOne(contactOne);
        director.setContactTwo(contactTwo);
        director.setEmail(email);
        csdService.save(director);

        MID marketingIdentity = new MID();
        marketingIdentity.setName(name);
        marketingIdentity.setDepartment(department);
        marketingIdentity.setMid(mid);
        marketingIdentity.setDesignation(designation);
        marketingIdentity.setType(type);
        csdService.save(marketingIdentity);

        return new ModelAndView("redirect:/csd/createDirector.erp", map);

    }

    //EmpCsd CSD
    @RequestMapping(method = RequestMethod.GET, value = "/createEmpCsd.erp")
    public ModelAndView createEmpCsd() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("PageTitle", "Create Employee");
        map.put("errorMsg", "");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("/csd/emp_csd_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveEmpCsd.erp")
    public ModelAndView saveEmpCsd(@RequestParam("mid") String mid,
                                   @RequestParam("name") String name,
                                   @RequestParam("reference") String reference,
                                   @RequestParam("joiningDate") Date joiningDate,
                                   @RequestParam("designation") String designation,
                                   @RequestParam("department") String department,
                                   @RequestParam("corporatePhone") String corporatePhone,
                                   @RequestParam("personalPhone") String personalPhone,
                                   @RequestParam("email") String email) {

        Map<String, String> map = new HashMap<String, String>();
        EmpCsd empCsd = new EmpCsd();
        empCsd.setEmail(email);
        empCsd.setName(name.toUpperCase());
        empCsd.setMid(mid);
        empCsd.setReference(reference);
        empCsd.setDepartment(department);
        empCsd.setDesignation(designation);
        empCsd.setJoiningDate(joiningDate);
        empCsd.setPersonalPhone(personalPhone);
        empCsd.setCorporatePhone(corporatePhone);
        csdService.save(empCsd);

        MID marketingIdentity = new MID();
        marketingIdentity.setName(name);
        marketingIdentity.setDepartment(department);
        marketingIdentity.setMid(mid);
        marketingIdentity.setDesignation(designation);
        marketingIdentity.setType("Employee");
        csdService.save(marketingIdentity);

        return new ModelAndView("redirect:/csd/createEmpCsd.erp", map);

    }

    //MID
    //Building start ................
    @RequestMapping(method = RequestMethod.GET, value = "/mIDList.erp")
    public ModelAndView mIDList() {
        Map<String, Object> buildingMap = new HashMap<String, Object>();
        buildingMap.put("PageTitle", "Marketing ID List");
        buildingMap.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("/csd/mid_list", buildingMap);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mIDJsonData.erp")
    public ModelAndView mIDJsonData() {
        int start = 0;
        int limit = 10015;

        Map<String, Object> listMap = null;
        Map<String, Object> map = new HashMap<String, Object>();
        List<MID> allData = csdService.findAllMID();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (MID data : allData) {
            listMap = new HashMap<String, Object>();
            listMap.put("id", data.getId());
            listMap.put("mid", data.getMid());
            listMap.put("name", data.getName());
            listMap.put("type", data.getType());
            listMap.put("designation", data.getDesignation());
            listMap.put("department", data.getDepartment());
            mapList.add(listMap);
        }

        map.put("mIDList", mapList.subList(start, start + limit > mapList.size() ?
                mapList.size() : start + limit));
        map.put("totalCount", mapList.size());
        return new ModelAndView(new JSONView(), map);
    }

    //Follow Up History.................
    //===========================================================================================

    @RequestMapping(
            method = {RequestMethod.POST},
            value = {"/saveFollowUpHistory.erp"}
    )
    public ModelAndView saveFollowUpHistory(@RequestParam("customerId") Long id, @RequestParam("comments") String comments, @RequestParam("remarks") String remarks, @RequestParam("attentionTo") Long attentionTo, @RequestParam("attentionOn") String knockingDate) {
        HashMap map = new HashMap();
        Customer customer = this.csdService.getCustomer(id);
        map.put("PageTitle", "History Create");
        map.put("DashboardLink", "../home.erp");
        map.put("customers", customer);
        this.csdService.setInactiveHistory(customer);
        FollowUpHistory history = new FollowUpHistory();
        history.setAttentionOn((new TheDates()).toDate(knockingDate));
        history.setAttentionTo(this.userDetailsService.getUser(id));
        history.setCalledBy((new CurrentUserCredentials()).user);
        history.setClientsComment(comments);
        history.setCustomer(customer);
        history.setRemarks(remarks);
        history.setDate(new Date());
        history.setActive(true);
        customer.getHistoryList().add(history);
        this.csdService.save(history);
        return new ModelAndView("redirect:/csd/viewCustomer.erp?cidView=" + customer.getCID(), map);
    }

    //Incentive
    @RequestMapping(method = RequestMethod.GET, value = "/createIncentiveDist.erp")
    public ModelAndView createIncentiveDist() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Incentive Application Form");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        map.put("customerList", csdService.findAllCustomer());

        return new ModelAndView("/csd/incentive_dist_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveIncentiveDist.erp")
    public ModelAndView saveIncentiveDist(@RequestParam("salesMan") Long id,
                                          //@RequestParam("mid") String mid,
                                          @RequestParam("salesManAmount") Double salesManAmount,
                                          @RequestParam("applicationDate") Date applicationDate,
                                          @RequestParam("personnelAmount") Double personnelAmount) {

        Map<String, Object> map = new HashMap<String, Object>();

        Customer customer = csdService.getCustomer(id);
        map.put("id", id);
        MID salesMan = customer.getReferenceBy();
        //MID salesMan = csdService.getMID(mid.split(":")[0].trim());

        IncentiveDist dist = new IncentiveDist();
        dist.setCustomer(customer);
        dist.setSalesMan(salesMan);
        dist.setSalesManAmount(salesManAmount);
        dist.setPersonnelAmount(personnelAmount);
        dist.setStatus(0);
        dist.setApplicationDate(applicationDate);
        dist.setRecordDate(new Date());

        customer.getDistList().add(dist);
        salesMan.getDistList().add(dist);

        csdService.save(dist);
        return new ModelAndView("redirect:/csd/viewIncentiveDist.erp", map);
    }

    //Application
    @RequestMapping(method = RequestMethod.GET, value = "/createCancellationApp.erp")
    public ModelAndView createCancellationApp() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Cancellation Application Form");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        map.put("appList", csdService.findAllApplication());
        map.put("util", new MirrorUtil());

        return new ModelAndView("/csd/cancellation_app_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveCancellationApp.erp")
    public ModelAndView saveCancellationApp(@RequestParam String applicationDate,
                                          @RequestParam String cid,
                                          @RequestParam String applicantName,
                                          @RequestParam Double depositedAmt,
                                          @RequestParam Double demandedAmt,
                                          @RequestParam String refAccountName,
                                          @RequestParam String refAccountNo,
                                          @RequestParam String refBank,
                                          @RequestParam String refBranch) {
        cid = MirrorUtil.makeCid(cid);
        CancellationApp app = new CancellationApp();
        app.setApplicationDate(DateUtil.toDate(applicationDate, "DD/MM/YYYY"));
        Customer customer = csdService.getCustomer(cid);
        app.setCustomer(customer);
        customer.getApplicationList().add(app);
        app.setApplicantName(applicantName);
        app.setDepositedAmt(depositedAmt);
        app.setDemandedAmt(demandedAmt);
        app.setRefAccountName(refAccountName);
        app.setRefAccountNo(refAccountNo);
        app.setRefBank(refBank);
        app.setStatus(Integer.parseInt(MirrorDataList.APP_STATUS[0][0]));
        app.setRefBranch(refBranch);
        app.setDataOperator(SessionUtil.getSessionUser());
        csdService.save(app);
        return new ModelAndView("redirect:/csd/createCancellationApp.erp");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/incentiveDistList.erp")
    public ModelAndView createIncentiveDist(@RequestParam("status") String status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Incentive Distribution List");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        if (status.toUpperCase().equals("ALL")) {
            map.put("distributionList", csdService.findAllIncentiveDist());
        } else {
            map.put("distributionList", csdService.findAllIncentiveDist(Integer.parseInt(status)));
        }

        map.put("status", status);

        return new ModelAndView("/csd/incentive_dist_list", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/viewIncentiveDist.erp")
    public ModelAndView viewIncentiveDist(@RequestParam Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Incentive Distribution View");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        Customer customer = csdService.getCustomer(id);
        List<IncentiveDist> distributionList;
        try {
            distributionList = customer.getDistList();
        } catch (NullPointerException npe) {
            distributionList = null;
        }
        map.put("distributionList", distributionList);
        map.put("customer", customer);


        return new ModelAndView("/csd/incentive_dist_view", map);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/checkIncentiveDist.erp")
    public ModelAndView checkIncentiveDist(@RequestParam Long id,
                                           @RequestParam Integer status) {
        Map<String, Object> map = new HashMap<String, Object>();
        IncentiveDist dist = csdService.getIncentiveDist(id);
        dist.setStatus(status);
        dist.setCheckedBy(new CurrentUserCredentials().user);

        csdService.update(dist);
        map.put("id", dist.getCustomer().getId());
        return new ModelAndView("redirect:/csd/viewIncentiveDist.erp", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/distributeIncentiveDist.erp")
    public ModelAndView distributeIncentiveDist(@RequestParam Long id,
                                                @RequestParam Integer status,
                                                @RequestParam String voucherNo,
                                                @RequestParam Date distributionDate) {
        Map<String, Object> map = new HashMap<String, Object>();
        IncentiveDist dist = csdService.getIncentiveDist(id);
        dist.setStatus(status);
        dist.setVoucherNo(voucherNo);
        dist.setDistributionDate(distributionDate);
        dist.setDistributedBy(new CurrentUserCredentials().user);

        csdService.update(dist);
        map.put("id", dist.getCustomer().getId());
        return new ModelAndView("redirect:/csd/viewIncentiveDist.erp", map);
    }

    @RequestMapping(value = "/getIncentiveDistData.erp", method = RequestMethod.GET)
    public
    @ResponseBody
    String checkDuplicateProject(@RequestParam("cid") String cid) {
        if (cid.toUpperCase().startsWith("DPL")) {
            cid = cid.substring(3, cid.length());
        }
        DecimalFormat df = new DecimalFormat("000000");
        cid = "DPL " + df.format(Integer.parseInt(cid.trim()));

        Customer customer = csdService.getCustomer(cid);

        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        if (customer != null) {
            map.put("msg", "Yes");
            map.put("customer", customer);
            map.put("apartmentPrice", csdService.getFloorPrice(customer));
            map.put("distributedAmount", csdService.getDistributedIncentive(customer));
            map.put("distributedAmountString", csdService.findAllIncentiveDistString(customer));
        } else {
            map.put("msg", "No");
        }
        String json = gson.toJson(map);
        return json;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteIncentiveDist.erp")
    public ModelAndView deleteIncentiveDist(@RequestParam Long id) {
        IncentiveDist dist = csdService.getIncentiveDist(id);
        dist.getSalesMan().getDistList().remove(dist);
        dist.getCustomer().getDistList().remove(dist);

        Map<String, Object> map = new HashMap<String, Object>();
        csdService.delete(dist);

        map.put("id", dist.getCustomer().getId());
        return new ModelAndView("redirect:/csd/viewIncentiveDist.erp", map);
    }


}




