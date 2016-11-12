package com.mirror2.icm.model;


import com.mirror2.security.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ICM_ITEM_OUT")
public class ItemOut implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User outFor;

    @ManyToOne
    private Item item;

    @Temporal(TemporalType.DATE)
    private Date dateOut;

    private double qty;

    @ManyToOne
    private User outBy;

    @ManyToOne
    private Store from;

    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOutFor() {
        return outFor;
    }

    public void setOutFor(User outFor) {
        this.outFor = outFor;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public User getOutBy() {
        return outBy;
    }

    public void setOutBy(User outBy) {
        this.outBy = outBy;
    }

    public Store getFrom() {
        return from;
    }

    public void setFrom(Store from) {
        this.from = from;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
