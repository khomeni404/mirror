package com.mirror2.csd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 9/9/14
 * Time: 9:44 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="CSD_OFFER")
public class Offer  implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="OFFER_NAME")
    private String offerName;

    @Column(name="OFFER_DESCRIPTION")
    private String offerDescription;

    @Column(name="DEAD_LINE")
    private String deadLine;

    @OneToMany
    @JoinTable(name="CSDJT_OFFER_CUSTOMER", joinColumns=@JoinColumn(name="OFFER_ID"),
            inverseJoinColumns=@JoinColumn(name="CUSTOMER_ID"))
    private List<Customer> customers = new ArrayList<Customer>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }


}
