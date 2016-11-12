package com.mirror2.icm.model;

import com.mirror2.security.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Khomeni
 * Date: 10/20/14
 * Time: 10:40 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="ICM_INTERNAL_REQUISITION")
public class InternalRequisition implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Item item;

    private Double qty;

    @ManyToOne
    private User requestBy;

    private String status;      //Requested | Accepted | Rejected

    private String note;

    @Temporal(TemporalType.DATE)
    private Date requestDate;

    @ManyToOne
    private User acceptedBy;

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

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public User getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(User requestBy) {
        this.requestBy = requestBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(User acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
}
