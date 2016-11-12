package com.mirror2.csd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 9/9/14
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="CSD_EMP")
public class EmpCsd implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="MARKETING_ID")
    private String mid;

    private String name;

    @Column(name="REFERENCE")
    private String reference;

    @Temporal(TemporalType.DATE)
    private Date joiningDate;

    private String designation;
    private String department;

    @Column(name="corporate_Phone")
    private String corporatePhone;

    @Column(name="personal_Phone")
    private String personalPhone;

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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
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

    public String getCorporatePhone() {
        return corporatePhone;
    }

    public void setCorporatePhone(String corporatePhone) {
        this.corporatePhone = corporatePhone;
    }

    public String getPersonalPhone() {
        return personalPhone;
    }

    public void setPersonalPhone(String personalPhone) {
        this.personalPhone = personalPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}