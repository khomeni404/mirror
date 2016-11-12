package com.mirror2.csd.model;

import com.mirror2.security.model.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: M Ayatullah
 * Date: 9/3/14
 * Time: 11:56 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name="TYPE",discriminatorType = DiscriminatorType.STRING)
@Table(name="CSD_VOUCHER",
        uniqueConstraints = @UniqueConstraint(columnNames = {"VOUCHER_NO"}))
public abstract class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "VOUCHER_NO")
    private String voucherNo;

    @Column(name = "VOUCHER_DATE")
    @Temporal(TemporalType.DATE)
    private Date voucherDate;

    private Double amount;

    @Column(columnDefinition = "text")
    private String note;

    private String status;

    @ManyToOne
    private User operator;

    @ManyToOne
    private User approvedBy;

    @ManyToOne
    private Customer customer;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOperator() {
        return operator;
    }

    public void setOperator(User operator) {
        this.operator = operator;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public Date getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(Date voucherDate) {
        this.voucherDate = voucherDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }
}
