package com.mirror2.icm.model;

import com.mirror2.security.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ICM_ITEM_USAGE_HISTORY")
public class ItemUsageHistory implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Item item;

    @ManyToOne
    private User owner;

    @Temporal(TemporalType.DATE)
    private Date historyDate;

    private Double quantity;

    private String status;       //New | Received | Used | Lost | Back

    private String note;

    private String isVerified;

    @ManyToOne
    private User verifiedBy;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(Date historyDate) {
        this.historyDate = historyDate;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(User verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getVerified() {
        return isVerified;
    }

    public void setVerified(String verified) {
        isVerified = verified;
    }
}

