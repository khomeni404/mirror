//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mirror2.csd.model;

import com.mirror2.csd.model.Customer;
import com.mirror2.security.model.User;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(
        name = "CSD_FOLLOW_UP_HISTORY"
)
public class FollowUpHistory implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Customer customer;
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne
    private User calledBy;
    @Column(
            columnDefinition = "text"
    )
    private String clientsComment;
    @ManyToOne
    private User attentionTo;
    @Temporal(TemporalType.DATE)
    private Date attentionOn;
    @Column(
            columnDefinition = "text"
    )
    private String remarks;
    private boolean active;

    public FollowUpHistory() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return this.date;
    }

    public Date getAttentionOn() {
        return this.attentionOn;
    }

    public void setAttentionOn(Date attentionOn) {
        this.attentionOn = attentionOn;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getCalledBy() {
        return this.calledBy;
    }

    public void setCalledBy(User calledBy) {
        this.calledBy = calledBy;
    }

    public String getClientsComment() {
        return this.clientsComment;
    }

    public void setClientsComment(String clientsComment) {
        this.clientsComment = clientsComment;
    }

    public User getAttentionTo() {
        return this.attentionTo;
    }

    public void setAttentionTo(User attentionTo) {
        this.attentionTo = attentionTo;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
