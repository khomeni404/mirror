package com.mirror2.live;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 21/10/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 21/10/15
 * Version : 1.0
 */

import com.mirror2.csd.model.Customer;
import com.mirror2.csd.service.CsdService;
import com.mirror2.messaging.threads.SmsThread;
import com.mirror2.util.MyProperty;
import org.smslib.AGateway;
import org.smslib.InboundMessage;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

//@Component
public class ATM /*extends Thread*/  implements InitializingBean, DisposableBean{
  //  @Autowired
   // private CsdService csdService;

    static List<InboundMessage> msgList = new ArrayList<InboundMessage>();


    @Override
    public void afterPropertiesSet() throws Exception {
        SmsThread smsThread = new SmsThread("01717659287", "ATM System Started");
        //SmsThreadOut smsThread =  new SmsThreadOut();
        smsThread.start();
        //startService();
        System.out.println("ATM's initialization method called. Connecting to bank's network.");

    }

    @Override
    public void destroy() throws Exception {
        System.out.println("ATM's destroy method called. Disconnecting from bank's network.");
        Service.getInstance().stopService();
    }

    public void run() {
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
        if(wordsList.length == 2) {
            try{
                String key = wordsList[0];
                String cid = wordsList[1];
                Double deposit = 0.0;
                if (cid.toUpperCase().startsWith("DPL")) {
                    cid = cid.substring(3, cid.length());
                }
                DecimalFormat df = new DecimalFormat("000000");
                cid = "DPL " + df.format(Integer.parseInt(cid.trim()));
                Customer c = null; //csdService.getCustomer(cid);
                String name = c.getName();
                if (key.toUpperCase().equals("T")) {
                    deposit = 45000.00; // csdService.getTotalDeposit(c);
                } else if (key.toUpperCase().equals("D")) {
                    deposit = 25000.00; // csdService.getTotalDeposit(c);
                } else{
                    return "Use Sohih KEY";
                }
                return "CID : " + cid + "\n" +
                        "Name : " + name + "\n" +
                        "Deposit : " + BigDecimal.valueOf(deposit).toPlainString();
            }   catch (Exception e){
                return "Exception";
            }
        }else{
            return "Format is not valid.";
        }
    }

    public static void main(String[] args) {
        SmsThread thread = new SmsThread("01717659287", "Test Sender");
        thread.start();
    }
}