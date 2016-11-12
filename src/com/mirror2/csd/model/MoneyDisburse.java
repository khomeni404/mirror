package com.mirror2.csd.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * @author M Ayatullah
 */
@Entity
@Table (name="CSD_MONEY_DISBURSE")
public class MoneyDisburse implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="INSTALLMENT")
    private Double installment;

    @Column(name="DOWN_PAYMENT")
    private Double downPayment;

    @Column(name="SPECIAL_PAYMENT")
    private Double specialPayment;

    @Column(name="CAR_PARK")
    private Double carPark;

    @Column(name="OTHER_PAYMENT")
    private Double otherPayment;        //Unit : decimal

    @Column(name="IS_UPDATED")
    private String isUpdate;

    @OneToOne(mappedBy = "moneyDisburse")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getInstallment() {
        return installment;
    }

    public void setInstallment(Double installment) {
        this.installment = installment;
    }

    public Double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(Double downPayment) {
        this.downPayment = downPayment;
    }

    public Double getSpecialPayment() {
        return specialPayment;
    }

    public void setSpecialPayment(Double specialPayment) {
        this.specialPayment = specialPayment;
    }

    public Double getCarPark() {
        return carPark;
    }

    public void setCarPark(Double carPark) {
        this.carPark = carPark;
    }

    public Double getOtherPayment() {
        return otherPayment;
    }

    public void setOtherPayment(Double otherPayment) {
        this.otherPayment = otherPayment;
    }

    public String getUpdate() {
        return isUpdate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setUpdate(String update) {
        isUpdate = update;
    }
}
