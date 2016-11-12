package com.mirror2.messaging.threads;

import com.mirror2.util.MyProperty;
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

public class SmsThread extends Thread {
    static List<InboundMessage> msgList = new ArrayList<InboundMessage>();

    private String number;
    private String message;

    public SmsThread(String number, String message) {
        this.number = number;
        this.message = message;
    }

    public void run() {
        try {
            SerialModemGateway gateway = new SerialModemGateway("id", MyProperty.PORT, MyProperty.BAUD_RATE, MyProperty.MANUFACTURER, MyProperty.MODEL);
            gateway.setInbound(true);
            gateway.setOutbound(true);
            Service.getInstance().addGateway(gateway);
            Service.getInstance().startService();

            try {
                if (number.length() == 11 && number.matches("[0-9]+")) {
                    OutboundMessage msg = new OutboundMessage(number, message + "\n-"+MyProperty.COMPANY_NAME);
                    msg.setFrom("TEST");
                    Service.getInstance().sendMessage(msg);
                    //System.out.println("Sent to : " + number);
//                   OutboundMessage msg2 = new OutboundMessage("01717659287", message + "\n-"+MyProperty.COMPANY_NAME);
//                   Service.getInstance().sendMessage(msg2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Service.getInstance().stopService();
            Service.getInstance().removeGateway(gateway);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
