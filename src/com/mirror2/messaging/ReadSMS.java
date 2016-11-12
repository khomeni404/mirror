package com.mirror2.messaging;

import com.mirror2.messaging.util.SMSInNotification;
import org.smslib.Message;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

public class ReadSMS {

    static String port = "COM7";
    static int baudRate = 921600;
    static String mrf = "ZTE Corporation";
    static String model = "3G";
    static String company = "\n-DORPAN";

    public void sendSMS(String number, String message) throws Exception {
        // System.out.println("It is being done.....");
        SerialModemGateway gateway = new SerialModemGateway("id", port, baudRate, mrf, model);
        gateway.setInbound(true);
        gateway.setOutbound(true);
        Service.getInstance().addGateway(gateway);
        Service.getInstance().startService();

        // String number = "01945544306";
        // String message = "Hello 45,000/= has been Deposited. -"+company;
        try {
            if (number.length() == 11 && number.matches("[0-9]+")) {
                OutboundMessage msg = new OutboundMessage(number, message+company);
                //Service.getInstance().sendMessage(msg);
                System.out.println("Sent to : " + number);
                new SMSInNotification().process(gateway, Message.MessageTypes.INBOUND, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Service.getInstance().stopService();
        Service.getInstance().removeGateway(gateway);
    }

    //
    public static void main(String args[]) {
        try {
           new ReadSMS().sendSMS("01945544306", "Hello Razib Viaa.");
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error(e);
        }
    }
}