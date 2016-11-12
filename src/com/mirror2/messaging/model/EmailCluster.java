package com.mirror2.messaging.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 29/08/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 29/08/15
 * Version : 1.0
 */

@Entity
@Table(name = "MSG_EMAIL_CLUSTER")
public class EmailCluster   implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String from;

    private String to;

    private String cc;

    private String bcc;

    private String subject;

    private String mailBody;

    @Temporal(TemporalType.TIMESTAMP)
    private Date queuedOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date sentOn;

    private boolean sent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMailBody() {
        return mailBody;
    }

    public void setMailBody(String mailBody) {
        this.mailBody = mailBody;
    }

    public Date getQueuedOn() {
        return queuedOn;
    }

    public void setQueuedOn(Date queuedOn) {
        this.queuedOn = queuedOn;
    }

    public Date getSentOn() {
        return sentOn;
    }

    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
