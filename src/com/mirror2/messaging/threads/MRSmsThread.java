package com.mirror2.messaging.threads;

import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.MoneyReceipt;
import com.mirror2.csd.service.CsdService;
import com.mirror2.csd.service.CsdServiceImpl;
import com.mirror2.util.MyProperty;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 02/09/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 02/09/15
 * Version : 1.0
 */

public class MRSmsThread extends Thread {
    private Customer customer;
    private MoneyReceipt receipt;
    private String cellNo;
    private String cid;
    private String name;
    private Double amount;
    private Double balance;

    public MRSmsThread(Customer customer, MoneyReceipt receipt) {
          this.customer = customer;
        this.receipt = receipt;
    }

    public void run() {
        try {
            cellNo = customer.getCellPhone();
            cid = customer.getCID();
            name = customer.getName();
            amount = receipt.getAmount();
            balance = new CsdServiceImpl().getAllDeposit();

            SerialModemGateway gateway = new SerialModemGateway("id", MyProperty.PORT, MyProperty.BAUD_RATE, MyProperty.MANUFACTURER, MyProperty.MODEL);
            gateway.setInbound(true);
            gateway.setOutbound(true);
            Service.getInstance().addGateway(gateway);
            Service.getInstance().startService();

            String message = "Dear Sir/Madam,\n" +
                    "Your apartment account in DSI is updated.\n" +
                    "ID: "+cid+"\n" +
                    "Name: " + name+"\n" +
                    "Received amount: BDT."+amount +  "0/-\n"+
                    "Total Balance: "+balance+"/-\n" +
                    " \n" +
                    "Regards,\n" +
                    "Customer Care Department,"+
                    "\n-"+MyProperty.COMPANY_NAME;
            try {
                if (cellNo.length() == 11 && cellNo.matches("[0-9]+")) {
                    OutboundMessage msg = new OutboundMessage(cellNo, message);
                    Service.getInstance().sendMessage(msg);
                    System.out.println("Sent to : " + cellNo);
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
