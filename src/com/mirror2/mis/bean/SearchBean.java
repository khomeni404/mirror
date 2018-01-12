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

    private String bookingStatus; // Approved, Canceled, Refunded etc

    private String yesStatus;

    private String notStatus;

    private String handoverYear;

    private String buildingHandover;

    private Integer floorSize;

    private String payMode;
    private Long offerId;

    private String reportFileName;

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

    public String getBuildingHandover() {
        return buildingHandover;
    }

    public void setBuildingHandover(String buildingHandover) {
        this.buildingHandover = buildingHandover;
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

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }
}
