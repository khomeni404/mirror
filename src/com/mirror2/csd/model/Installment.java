package com.mirror2.csd.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author M Ayatullah
 */
@Entity
@Table (name="CSD_INSTALLMENT")
public class Installment implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    private Customer customer;
    
    @Column(name="INSTALLMENT_NAME")
    private String installmentName;
    

    @Column(name="INSTALLMENT_OF")
    private String installmentOf;

    @Column(name="MONTH_PER_INSTALLMENT")
    private Integer monthPerInst;
    
    private double amount;
    
    @Temporal (TemporalType.DATE)
    @Column(name="DEAD_LINE")
    private Date deadLine;
    
    private String note;

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

    public String getInstallmentName() {
        return installmentName;
    }

    public void setInstallmentName(String installmentName) {
        this.installmentName = installmentName;
    }

    public Integer getMonthPerInst() {
        return monthPerInst;
    }

    public void setMonthPerInst(Integer monthPerInst) {
        this.monthPerInst = monthPerInst;
    }

    public String getInstallmentOf() {
        return installmentOf;
    }

    public void setInstallmentOf(String installmentOf) {
        this.installmentOf = installmentOf;
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
