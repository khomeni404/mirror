package com.mirror2.aop;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 18/10/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 18/10/15
 * Version : 1.0
 */

import com.mirror2.util.MyProperty;
import org.smslib.AGateway;
import org.smslib.InboundMessage;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

//@Singleton
//@Startup
//@Component
public class LifecycleBean {
    static List<InboundMessage> msgList = new ArrayList<InboundMessage>();

   // @Autowired
   // CsdService csdService;

    @PostConstruct
    public void init() {
        try {
            Thread.sleep(240000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startService();
        //SmsThread startUpMsg =  new SmsThread("01717659287", "MIRROR has been started.");
        //startUpMsg.start();
       // SmsThreadOut smsThread =  new SmsThreadOut();
        //smsThread.start();
        /*try {
            SerialModemGateway gateway = new SerialModemGateway("id", MyProperty.PORT, MyProperty.BAUD_RATE, MyProperty.MANUFACTURER, MyProperty.MODEL);
            gateway.setInbound(true);
            gateway.setOutbound(true);
            gateway.setProtocol(AGateway.Protocols.PDU);
            gateway.getATHandler().setStorageLocations("SM");
            Service.getInstance().addGateway(gateway);
            Service.getInstance().startService();

            while (true) {
                Service.getInstance().readMessages(msgList, InboundMessage.MessageClasses.ALL);
                System.out.println("msgList = " + msgList.size());
                //myDriver();
                int msgCounter = 0;
                for (InboundMessage msg : msgList) {

                    msgCounter++;
                    String sender = msg.getOriginator();
                    System.out.println("sender = " + sender);
                    String msgText = msg.getText();
                    System.out.println(msgCounter + " text_msg = " + msgText);
                    msgText = msgText.trim().replaceAll(" +", " ");
                    //logger.info("\nFrom: " + sender);
                    //logger.info("Text: " + text_msg);
                    String[] wordsList = msgText.split(" ");

                    String key = wordsList[0];
                    OutboundMessage msgOut = new OutboundMessage(sender.substring(2,13), "This is ans of ur SMS,\n Fron DORPAN System \""+msgText+"\"\n -"+MyProperty.COMPANY_NAME);
                    Service.getInstance().sendMessage(msgOut);
                    Service.getInstance().deleteMessage(msg);
                }
                msgList.clear();
                Thread.sleep(1500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

    @PreDestroy
    public void destroy() {
    /* Shutdown stuff here */
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
                    System.out.println(msgCounter + " text_msg = " + msgText);
                    msgText = msgText.trim().replaceAll(" +", " ");
                    //logger.info("\nFrom: " + sender);
                    //logger.info("Text: " + text_msg);
                    String[] wordsList = msgText.split(" ");

                    String key = wordsList[0];
                    String cid = wordsList[1];
                    Double deposit = 0.0;
                    if (cid.toUpperCase().startsWith("DPL")) {
                        cid = cid.substring(3, cid.length());
                    }
                    DecimalFormat df = new DecimalFormat("000000");
                    cid = "DPL " + df.format(Integer.parseInt(cid.trim()));
                    //Customer c = csdService.getCustomer(cid);
                    String name = "Lalon"; // c.getName();
                    if (key.toUpperCase().equals("DEP")) {
                       // deposit =  csdService.getTotalDeposit(c);
                    }
                    String message = "CID : " + cid + "\n" +
                            "Name : " + name + "\n" +
                            "Deposit : " + BigDecimal.valueOf(deposit).toPlainString();
                    OutboundMessage msgOut = new OutboundMessage(sender.substring(2, 13), message + "\n-" + MyProperty.COMPANY_NAME);
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

}
