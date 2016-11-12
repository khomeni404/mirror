package com.mirror2.icm.model;

import com.mirror2.security.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Khomeni
 * Date: 10/17/14
 * Time: 2:45 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="ICM_ITEM_IN")
public class ItemIn implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String source;    // Purchase | Back | Store | Donation

    @ManyToOne
    private Item item;

    private double qty;

    @Temporal(TemporalType.DATE)
    private java.util.Date dateIn;

    @ManyToOne
    private User storeBy;

    @ManyToOne
    private Store to;

    private String isApprove;     //  Yes | No

    @ManyToOne
    private User approveBy;

    private String isChecked;     // Yes | No

    @ManyToOne
    private User checkedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public User getStoreBy() {
        return storeBy;
    }

    public void setStoreBy(User storeBy) {
        this.storeBy = storeBy;
    }

    public Store getTo() {
        return to;
    }

    public void setTo(Store to) {
        this.to = to;
    }

    public String getApprove() {
        return isApprove;
    }

    public void setApprove(String approve) {
        isApprove = approve;
    }

    public User getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(User approveBy) {
        this.approveBy = approveBy;
    }

    public String getChecked() {
        return isChecked;
    }

    public void setChecked(String checked) {
        isChecked = checked;
    }

    public User getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(User checkedBy) {
        this.checkedBy = checkedBy;
    }
}
