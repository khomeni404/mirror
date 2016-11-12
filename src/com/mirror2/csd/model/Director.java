package com.mirror2.csd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: M Ayatullah
 * Date: 8/22/14
 * Time: 9:57 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="CSD_DIRECTOR")
public class Director  implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="MARKETING_ID")
    private String mid;

    private String name;

    @Column(name="REFERENCE")
    private String reference;

    private String type;

    private String designation;
    private String department;

    @Column(name="CONTACT_ONE")
    private String contactOne;

    @Column(name="CONTACT_TWO")
    private String contactTwo;

    private String email;

    private boolean active;
    //OMR
    @OneToMany
    @JoinTable(name="CSDJT_DIRECTOR_VOUCHER", joinColumns = @JoinColumn(name="DIRECTOR_ID"),
            inverseJoinColumns = @JoinColumn(name="VOUCHER_ID"))
    private List<Voucher> vouchers = new ArrayList<Voucher>();

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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContactOne() {
        return contactOne;
    }

    public void setContactOne(String contactOne) {
        this.contactOne = contactOne;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public List<Voucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<Voucher> vouchers) {
        this.vouchers = vouchers;
    }
}