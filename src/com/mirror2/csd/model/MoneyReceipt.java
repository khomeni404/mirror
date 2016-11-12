package com.mirror2.csd.model;

import com.mirror2.security.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "CSD_MONEY_RECEIPT",
        uniqueConstraints = @UniqueConstraint(columnNames = {"RECEIPT_NO"}))
public class MoneyReceipt implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "RECEIPT_NO")
    private String receiptNo;

    @Column(name = "RECEIPT_DATE")
    @Temporal(TemporalType.DATE)
    private Date receiptDate;

    private Double amount;

    @ManyToOne
    private Customer customer;

    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;  //Cash | Cheque | TT | DD | adjustment

    private String note;

    @Column(name = "CASH_DATE")
    @Temporal(TemporalType.DATE)
    private Date cashDate;

    @Column(name = "ENTRY_DATE")
    @Temporal(TemporalType.DATE)
    private Date entryDate;

    private int signature;

    private String status;           //Not Passed  /  Passed   / Rejected

    @ManyToOne
    private User statusUpdateBy;

    @ManyToOne
    private User operator;

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

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getStatusUpdateBy() {
        return statusUpdateBy;
    }

    public void setStatusUpdateBy(User statusUpdateBy) {
        this.statusUpdateBy = statusUpdateBy;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCashDate() {
        return cashDate;
    }

    public void setCashDate(Date cashDate) {
        this.cashDate = cashDate;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public int getSignature() {
        return signature;
    }

    public void setSignature(int signature) {
        this.signature = signature;
    }

    public User getOperator() {
        return operator;
    }

    public void setOperator(User operator) {
        this.operator = operator;
    }
}
