package com.mirror2.csd.model;

import com.mirror2.admin.model.BoardMeeting;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 9/9/14
 * Time: 10:59 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "CSD_MARKETING_IDENTITY")
public class MID implements Serializable {
    public MID(){}
    public MID(Long id){this.id = id;}
    @Id
    @GeneratedValue
    private Long id;

    private String mid;

    private String name;

    private String type;

    private String designation;

    private String department;

    @OneToMany
    @JoinTable(name = "CSDJT_REFER_CUSTOMER", joinColumns = @JoinColumn(name = "REFER_ID"),
            inverseJoinColumns = @JoinColumn(name = "CUSTOMER_ID"))
    private List<Customer> customersReferred = new ArrayList<Customer>();

    @OneToMany
    @JoinTable(name = "CSDJT_TAKE_CARE_CUSTOMER", joinColumns = @JoinColumn(name = "EMP_ID"),
            inverseJoinColumns = @JoinColumn(name = "CUSTOMER_ID"))
    private List<Customer> customersCared = new ArrayList<Customer>();

    @OneToMany
    @JoinTable(name = "CSD_JT_SALESMAN_INCENTIVE_DIST", joinColumns = @JoinColumn(name = "SALESMAN_ID"),
            inverseJoinColumns = @JoinColumn(name = "DIST_ID"))
    private List<IncentiveDist> distList = new ArrayList<IncentiveDist>();

    public List<String> getReferedCutomerIdList(String except) {
        List<String> list = new ArrayList<String>(0);
        for (Customer c : this.customersReferred) {
            if (!c.getStatus().equals(except))
                list.add(c.getId().toString());
        }
        return list;
    }

    public List<String> getReferedCutomerIdListBetweenDate(String except, Date from, Date to) {
        List<String> list = new ArrayList<String>(0);
        for (Customer c : this.customersReferred) {
            if (!c.getStatus().equals(except)
                    && c.getBookingDate().after(from)
                    && c.getBookingDate().before(to)) {
                list.add(c.getId().toString());
            }
        }
        return list;
    }

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

    public List<Customer> getCustomersReferred() {
        return customersReferred;
    }

    public void setCustomersReferred(List<Customer> customersReferred) {
        this.customersReferred = customersReferred;
    }

    public List<Customer> getCustomersCared() {
        return customersCared;
    }

    public void setCustomersCared(List<Customer> customersCared) {
        this.customersCared = customersCared;
    }

    public List<IncentiveDist> getDistList() {
        return distList;
    }

    public void setDistList(List<IncentiveDist> distList) {
        this.distList = distList;
    }
}
