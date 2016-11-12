package com.mirror2.messaging.util;

import org.smslib.AGateway;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message.MessageTypes;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 01/09/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 01/09/15
 * Version : 1.0
 */

public class SMSInNotification implements IInboundMessageNotification {
    public void process(AGateway gateway, MessageTypes msgType, InboundMessage msg) {
        switch (msgType) {
            case INBOUND:
                System.out.println(">>> New Inbound message detected from " + "+" + msg.getOriginator() + " " + msg.getText());
                break;
            case STATUSREPORT:
                System.out.println("Found no msg");
                break;
        }
    }
}
