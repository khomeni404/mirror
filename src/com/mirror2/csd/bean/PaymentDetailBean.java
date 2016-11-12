package com.mirror2.csd.bean;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 02/02/16
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 02/02/16
 * Version : 1.0
 */

public class PaymentDetailBean {
    private Long disbursementId;
    private Long customerId;
    private String cid;

    private Double totalDp;
    private Double totalSp;
    private Double totalCp;
    private Double totalOp;
    private Double totalInst;

    private Double payableDp;
    private Double payableSp;
    private Double payableCp;
    private Double payableOp;
    private Double payableInst;

    private Double disburseDp;
    private Double disburseSp;
    private Double disburseCp;
    private Double disburseOp;
    private Double disburseInst;

    private Double paidTotal;
    private Double disbursedTotal;




    public Long getDisbursementId() {
        return disbursementId;
    }

    public void setDisbursementId(Long disbursementId) {
        this.disbursementId = disbursementId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Double getTotalDp() {
        return totalDp;
    }

    public void setTotalDp(Double totalDp) {
        this.totalDp = totalDp;
    }

    public Double getTotalSp() {
        return totalSp;
    }

    public void setTotalSp(Double totalSp) {
        this.totalSp = totalSp;
    }

    public Double getTotalCp() {
        return totalCp;
    }

    public void setTotalCp(Double totalCp) {
        this.totalCp = totalCp;
    }

    public Double getTotalOp() {
        return totalOp;
    }

    public void setTotalOp(Double totalOp) {
        this.totalOp = totalOp;
    }

    public Double getTotalInst() {
        return totalInst;
    }

    public void setTotalInst(Double totalInst) {
        this.totalInst = totalInst;
    }

    public Double getPayableDp() {
        return payableDp;
    }

    public void setPayableDp(Double payableDp) {
        this.payableDp = payableDp;
    }

    public Double getPayableSp() {
        return payableSp;
    }

    public void setPayableSp(Double payableSp) {
        this.payableSp = payableSp;
    }

    public Double getPayableCp() {
        return payableCp;
    }

    public void setPayableCp(Double payableCp) {
        this.payableCp = payableCp;
    }

    public Double getPayableOp() {
        return payableOp;
    }

    public void setPayableOp(Double payableOp) {
        this.payableOp = payableOp;
    }

    public Double getPayableInst() {
        return payableInst;
    }

    public void setPayableInst(Double payableInst) {
        this.payableInst = payableInst;
    }

    public Double getDisburseDp() {
        return disburseDp;
    }

    public void setDisburseDp(Double disburseDp) {
        this.disburseDp = disburseDp;
    }

    public Double getDisburseSp() {
        return disburseSp;
    }

    public void setDisburseSp(Double disburseSp) {
        this.disburseSp = disburseSp;
    }

    public Double getDisburseCp() {
        return disburseCp;
    }

    public void setDisburseCp(Double disburseCp) {
        this.disburseCp = disburseCp;
    }

    public Double getDisburseOp() {
        return disburseOp;
    }

    public void setDisburseOp(Double disburseOp) {
        this.disburseOp = disburseOp;
    }

    public Double getDisburseInst() {
        return disburseInst;
    }

    public void setDisburseInst(Double disburseInst) {
        this.disburseInst = disburseInst;
    }

    public Double getPaidTotal() {
        return paidTotal;
    }

    public void setPaidTotal(Double paidTotal) {
        this.paidTotal = paidTotal;
    }

    public Double getDisbursedTotal() {
        return disbursedTotal;
    }

    public void setDisbursedTotal(Double disbursedTotal) {
        this.disbursedTotal = disbursedTotal;
    }
}


/* map.put("totalDp", csdService.getTotalOtherPaymentAmount(customer, "Down Payment"));
        map.put("totalSp", csdService.getTotalOtherPaymentAmount(customer, "Special Payment"));
        map.put("totalCp", csdService.getTotalOtherPaymentAmount(customer, "Car Park"));
        map.put("totalOp", csdService.getTotalOtherPaymentAmount(customer, "Other Payment"));
        map.put("totalInst", csdService.getTotalInstallmentAmount(customer));

        map.put("payableDp", csdService.getPayableOtherPaymentAmount(customer, "Down Payment"));
        map.put("payableSp", csdService.getPayableOtherPaymentAmount(customer, "Special Payment"));
        map.put("payableCp", csdService.getPayableOtherPaymentAmount(customer, "Car Park"));
        map.put("payableOp", csdService.getPayableOtherPaymentAmount(customer, "Other Payment"));
        map.put("payableInst", csdService.getPayableInstallmentAmount(customer));

        map.put("disburseDp", moneyDisburse.getDownPayment());
        map.put("disburseSp", moneyDisburse.getSpecialPayment());
        map.put("disburseCp", moneyDisburse.getCarPark());
        map.put("disburseOp", moneyDisburse.getOtherPayment());
        map.put("disburseInst", moneyDisburse.getInstallment());

        map.put("paidTotal", csdService.getTotalDeposit(customer));
        map.put("disbursedTotal", csdService.getTotalDisburse(customer));*/
