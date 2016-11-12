package com.mirror2.csd.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: M Ayatullah
 * Date: 8/22/14
 * Time: 10:24 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="CSD_DIRECTOR")
public class Shareholder implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="MARKETING_ID")
    private String mid;

    private String name;

    @Column(name="REFERENCE_ID")
    private String referenceId;

    private String category;

    private double amount;

    @Column(name="CONTACT_ONE")
    private String contactOne;

    @Column(name="CONTACT_TWO")
    private String contactTwo;

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getContactOne() {
        return contactOne;
    }

    public void setContactOne(String contactOne) {
        this.contactOne = contactOne;
    }

    public String getContactTwo() {
        return contactTwo;
    }

    public void setContactTwo(String contactTwo) {
        this.contactTwo = contactTwo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
