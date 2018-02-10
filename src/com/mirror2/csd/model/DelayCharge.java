package com.mirror2.csd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Copyright @ Soft Engine [www.soft-engine.net].
 * Created on 10-Feb-18 at 11:38 PM
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 10-Feb-18
 * Version : 1.0
 */

@Entity
@Table(name="CSD_DELAY_CHARGE")
public class DelayCharge  implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer customer;


    @Column(name = "AMOUNT")
    private Double amount;

    @Temporal(TemporalType.DATE)
    @Column(name = "DEAD_LINE")
    private Date deadLine;

    @Column(name = "CHARGE_OF")
    private String chargeOf;

    @Column(name = "NOTE")
    private String note; // 0100000000000000

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getChargeOf() {
        return chargeOf;
    }

    public void setChargeOf(String chargeOf) {
        this.chargeOf = chargeOf;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
