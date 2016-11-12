package com.mirror2.messaging.threads;

import com.mirror2.util.MyProperty;
import org.smslib.AGateway;
import org.smslib.InboundMessage;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 02/09/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 02/09/15
 * Version : 1.0
 */

public class SmsThreadOut extends Thread {
    static List<InboundMessage> msgList = new ArrayList<InboundMessage>();

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
                    System.out.println(msgCounter + " text_msg = " + msgText);
                    msgText = msgText.trim().replaceAll(" +", " ");
                    //logger.info("\nFrom: " + sender);
                    //logger.info("Text: " + text_msg);
                    String[] wordsList = msgText.split(" ");

                    String key = wordsList[0];

                    //if (!key.toUpperCase().equals("DUE"))
                    //Service.
                    OutboundMessage msgOut = new OutboundMessage(sender.substring(2, 13), "This is ans of ur SMS,\n\"" + msgText + "\"\n -" + MyProperty.COMPANY_NAME);
                    Service.getInstance().sendMessage(msgOut);

                    Service.getInstance().deleteMessage(msg);
                    //Service.getInstance().stopService();
                    //Service.getInstance().removeGateway(gateway);
                    //Thread.sleep(500);
                    //msgList.remove(msg);
                }
                msgList.clear();
                Thread.sleep(10000);

                Service.getInstance().stopService();
                Service.getInstance().removeGateway(gateway);
            }


            /*
            Service.getInstance().stopService();
            Service.getInstance().removeGateway(gateway);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
