package com.mirror2.common.model;

import com.mirror2.csd.model.Customer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khomeni on 23-Jan-17.
 */

@Entity
@Table(name = "COM_BADGE")
public class Badge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String badgeSign;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "badgeList")
    @Fetch(FetchMode.SELECT)
    private List<Customer> customerList = new ArrayList<Customer>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBadgeSign() {
        return badgeSign;
    }

    public void setBadgeSign(String badgeSign) {
        this.badgeSign = badgeSign;
    }
}
