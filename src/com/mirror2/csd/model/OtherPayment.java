package com.mirror2.csd.model;

import com.mirror2.security.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: M Ayatullah
 * Date: 8/25/14
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="CSD_OTHER_PAYMENTS")
public class OtherPayment implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer customer;

    @Column(name = "PAYMENT_TYPE")
    private String paymentType;

    @Column(name = "PAYMENT_NAME")
    private String paymentName;

    @Column(name = "AMOUNT")
    private double amount;

    @Temporal(TemporalType.DATE)
    @Column(name = "DEAD_LINE")
    private Date deadLine;

    @Column(name = "NOTE")
    private String note;

    @ManyToOne
    private User user;


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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
