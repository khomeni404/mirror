package com.mirror2.messaging.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 29/08/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 29/08/15
 * Version : 1.0
 */

@Entity
@Table(name = "MSG_SMS_CLUSTER")
public class SMSCluster  implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String cellNo;

    private String messageBody;

    @Temporal(TemporalType.TIMESTAMP)
    private Date queuedOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date pushedOn;

    private boolean sent;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Date getQueuedOn() {
        return queuedOn;
    }

    public void setQueuedOn(Date queuedOn) {
        this.queuedOn = queuedOn;
    }

    public Date getPushedOn() {
        return pushedOn;
    }

    public void setPushedOn(Date pushedOn) {
        this.pushedOn = pushedOn;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
