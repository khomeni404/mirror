package com.mirror2.csd.model;

import com.mirror2.security.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by DORPAN Server on 01/05/2015.
 * createIncentiveDist.erp
 */
@Entity
@Table(name = "CSD_INCENTIVE_DISTRIBUTION")
public class IncentiveDist implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private MID salesMan;

    private Double salesManAmount;

    private Double personnelAmount;

    private Integer status;  // 0 = Requested, 1 = Checked By CCD, 2 = Approved, 3 = Distributed

    @ManyToOne
    private User checkedBy;

    @ManyToOne
    private User approvedBy;

    @ManyToOne
    private User distributedBy;

    private String voucherNo;

    @Temporal(TemporalType.DATE)
    private Date applicationDate;

    @Temporal(TemporalType.DATE)
    private Date distributionDate;

    @Temporal(TemporalType.DATE)
    private Date recordDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public MID getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(MID salesMan) {
        this.salesMan = salesMan;
    }

    public Double getSalesManAmount() {
        return salesManAmount;
    }

    public void setSalesManAmount(Double salesManAmount) {
        this.salesManAmount = salesManAmount;
    }

    public Double getPersonnelAmount() {
        return personnelAmount;
    }

    public void setPersonnelAmount(Double personnelAmount) {
        this.personnelAmount = personnelAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(User checkedBy) {
        this.checkedBy = checkedBy;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(Date distributionDate) {
        this.distributionDate = distributionDate;
    }

    public User getDistributedBy() {
        return distributedBy;
    }

    public void setDistributedBy(User distributedBy) {
        this.distributedBy = distributedBy;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
}
