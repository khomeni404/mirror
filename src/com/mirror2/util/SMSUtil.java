package com.mirror2.util;

/**
 * Copyright to Soft Engine Inc.
 * Created on 23/08/15
 * Edited By : Khomeni,
 * Last Edited by : Khomeni
 * Last Edited on : 23/08/15
 * Version : 1.0
 */

import com.mirror2.messaging.threads.SmsThread;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SMSUtil {

    static String port = "COM7";
    static int baudRate = 921600;
    static String mfr = "ZTE Corporation";
    static String model = "3G";
    static String company = "-DORPAN";

    public static void sendSMS(String number, String message) {
        try {
            // System.out.println("It is being done.....");
            SerialModemGateway gateway = new SerialModemGateway("id", port, baudRate, mfr, model);
            gateway.setInbound(true);
            gateway.setOutbound(true);

            Service.getInstance().addGateway(gateway);
            Service.getInstance().startService();

            //String number = "01945544306";
            //String message = "Hello 45,000/= has been Deposited. -"+company;

            if (number.length() == 11 && number.matches("[0-9]+")) {
                OutboundMessage msg = new OutboundMessage(
                        number, message + company);
                Service.getInstance().sendMessage(msg);
                System.out.println("Sent to : " + number);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //
    public static void main(String args[]) {
        /*try {
            SMSUtil.sendSMS("01945544306", "Sent From main()\n");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        String date = sdf.format(new Date());

        String sms = "Dear Sir/Madam,\n" +
                "Assalamualaykum.\n" +
                "Your current due is 56000/-\n" +
                "(Inst. 35000/- + DP 3200/-).\n" +
                "Till " +date+"\n"+
                "Check ur email for more info." +
                "";
        try {
            SmsThread smsThread = new SmsThread("0193885224", sms);     /*01945544306*/
            smsThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
