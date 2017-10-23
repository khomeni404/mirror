package com.mirror2.mis.bean;

import java.util.Date;

/**
 * Copyright @ Soft Engine [www.soft-engine.net].
 * Created on 23-Oct-17 at 9:25 PM
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 23-Oct-17
 * Version : 1.0
 */

public class SearchBean {

    private String bookingYear;

    private String CID;

    private Date paymentDeadLine;

    private String yesStatus;

    private String notStatus;

    private String handoverYear;

    private Integer floorSize;

    public String getBookingYear() {
        return bookingYear;
    }

    public void setBookingYear(String bookingYear) {
        this.bookingYear = bookingYear;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public Date getPaymentDeadLine() {
        return paymentDeadLine;
    }

    public void setPaymentDeadLine(Date paymentDeadLine) {
        this.paymentDeadLine = paymentDeadLine;
    }

    public String getNotStatus() {
        return notStatus;
    }

    public void setNotStatus(String notStatus) {
        this.notStatus = notStatus;
    }

    public String getHandoverYear() {
        return handoverYear;
    }

    public void setHandoverYear(String handoverYear) {
        this.handoverYear = handoverYear;
    }

    public Integer getFloorSize() {
        return floorSize;
    }

    public void setFloorSize(Integer floorSize) {
        this.floorSize = floorSize;
    }

    public String getYesStatus() {
        return yesStatus;
    }

    public void setYesStatus(String yesStatus) {
        this.yesStatus = yesStatus;
    }
}
