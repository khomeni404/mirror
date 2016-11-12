package com.mirror2.home;

import com.mirror2.admin.service.AdminService;
import com.mirror2.csd.model.Customer;
import com.mirror2.csd.service.CsdService;
import com.mirror2.csd.util.CurrentUserCredentials;
import com.mirror2.messaging.threads.SmsThreadOut;
import com.mirror2.util.MyProperty;
import com.mirror2.util.TheDates;
import com.mirror2.security.model.User;
import com.mirror2.security.service.UserDetailsService;
import com.mirror2.time.model.AttendanceRegister;
import com.mirror2.time.service.AttendanceService;
import org.smslib.AGateway;
import org.smslib.InboundMessage;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    CsdService csdService;
    @Autowired
    AttendanceService attendanceService;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    AdminService adminService;


    static List<InboundMessage> msgList = new ArrayList<InboundMessage>();

    @RequestMapping(method = RequestMethod.GET, value = "/home.erp")
    public ModelAndView home() {
        // startService();
        Long empId = new CurrentUserCredentials().authorizeId;
        Date dateAndTime = new Date();
        User emp = userDetailsService.getUser(empId);

        List<AttendanceRegister> registers = attendanceService.getAttendanceRegisterList(dateAndTime, emp);
        HashMap<String, Object> map = new HashMap<String, Object>();

        if (registers.size() == 0) {
            map.put("totalAttend", 0);
            AttendanceRegister register = new AttendanceRegister();
            register.setEmployee(emp);
            register.setDate(dateAndTime);
            register.setTimeIn(dateAndTime);
            register.setEntryType("Auto");
            register.setNote("");
            register.setApproved(true);
            emp.getRegisters().add(register);
            attendanceService.save(register);
        } else {
            map.put("totalAttend", registers.size());
        }
        map.put("appWaiting", 3);
        map.put("appToApprove", 2);
        map.put("onLeave", 1);

        //===================================
        map.put("PageTitle", "mirror - Home");
        map.put("PageLink", "home");
        map.put("templateType", "home");
        map.put("today", new TheDates().getToday());
        List<Customer> customers = csdService.findAllCustomer();
        int booking = 0;
        Double collectedAmount = 0.0;
        int approved = 0;
        int cancelled = 0;
        int refunded = 0;
        int pending = 0;
        for (Customer customer : customers) {
            booking++;
            collectedAmount += csdService.getTotalDeposit(customer);
            approved += customer.getStatus().toUpperCase().equals("APPROVED") ? 1 : 0;
            cancelled += customer.getStatus().toUpperCase().equals("CANCELLED") ? 1 : 0;
            refunded += customer.getStatus().toUpperCase().equals("REFUNDED") ? 1 : 0;
            pending += customer.getStatus().toUpperCase().equals("PENDING") ? 1 : 0;
        }
        map.put("numberOfBooking", booking);
        map.put("approved", approved);
        map.put("cancelled", cancelled);
        map.put("refunded", refunded);
        map.put("pending", pending);
        map.put("collectedAmount", collectedAmount);

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

        if (new TheDates().isLicenceOk())
            return new ModelAndView("/home/index", map);
        else
            return new ModelAndView("/home/index2", map);
    }

    public static void main(String[] args) {
        new HomeController().startService();
    }

    public void startService() {
        try {
            while (true) {
                SerialModemGateway gateway = new SerialModemGateway("id", MyProperty.PORT, MyProperty.BAUD_RATE, MyProperty.MANUFACTURER, MyProperty.MODEL);
                gateway.setInbound(true);
                gateway.setOutbound(true);
                gateway.setProtocol(AGateway.Protocols.PDU);
                gateway.getATHandler().setStorageLocations("SM");
                Service.getInstance().addGateway(gateway);
                Service.getInstance().startService();

                Service.getInstance().readMessages(msgList, InboundMessage.MessageClasses.ALL);
                System.out.println("msgList = " + msgList.size());
                //myDriver();
                int msgCounter = 0;
                for (InboundMessage msg : msgList) {

                    msgCounter++;
                    String sender = msg.getOriginator();
                    System.out.println("sender = " + sender);
                    String msgText = msg.getText();
                    msgText = processMessage(msgText);
                    OutboundMessage msgOut = new OutboundMessage(sender.substring(2, 13), msgText + "\n-" + MyProperty.COMPANY_NAME);
                    Service.getInstance().sendMessage(msgOut);
                    Service.getInstance().deleteMessage(msg);
                }
                msgList.clear();
                Thread.sleep(500);

                Service.getInstance().stopService();
                Service.getInstance().removeGateway(gateway);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String processMessage(String msgText) {
        msgText = msgText.trim().replaceAll(" +", " ");
        String[] wordsList = msgText.split(" ");
        if (wordsList.length == 2) {
            try {
                String key = wordsList[0];
                String cid = wordsList[1];
                Double deposit = 0.0;
                if (cid.toUpperCase().startsWith("DPL")) {
                    cid = cid.substring(3, cid.length());
                }
                DecimalFormat df = new DecimalFormat("000000");
                cid = "DPL " + df.format(Integer.parseInt(cid.trim()));
                Customer c = csdService.getCustomer(cid);
                String name = c.getName();
                if (key.toUpperCase().equals("T")) {
                    deposit = 45000.00; // csdService.getTotalDeposit(c);
                } else if (key.toUpperCase().equals("D")) {
                    deposit = 25000.00; // csdService.getTotalDeposit(c);
                } else {
                    return "Use Sohih KEY";
                }
                return "CID : " + cid + "\n" +
                        "Name : " + name + "\n" +
                        "Deposit : " + BigDecimal.valueOf(deposit).toPlainString();
            } catch (Exception e) {
                return "Exception";
            }
        } else {
            return "Format is not valid.";
        }
    }


}
