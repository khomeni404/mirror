package com.mirror2.time.model;

import com.mirror2.security.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Khomeni
 * Date: 11/3/14
 * Time: 1:03 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TIME_LEAVE_APPLICATION")
public class LeaveApplication implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User appliedBy;        // Applied by           status = 0 = Applied

    @ManyToOne
    private User proxyBy;           //Duty in charge        status = 1 = Settled

    @ManyToOne
    private User acceptedBy; ;    //Head of Department     status = 2 = Accepted

    @ManyToOne
    private User receivedBy;        // Admin in charge      status = 3 = Received

    @ManyToOne
    private User approvedBy;        // Managing Director    status = 4 = Approved

    @ManyToOne
    private User rejectBy;         // Admin in charge     status = 5 = Rejected


    @Temporal(TemporalType.DATE)
    private Date appliedOn;

    private Integer days;

    @Temporal(TemporalType.DATE)
    private Date fromDate;

    @Temporal(TemporalType.DATE)
    private Date toDate;

    private String issue;

    private String leaveType;     // 0 = CL | 1 = SL | 2 = EL

    private Integer status;        // 0 = Applied | 1 = Settled | 2 = Accepted | 3 = Received | 4 = Approved | 5 = Rejected

    private String note;

    private String phoneOnLeave;

    private String locationOnLeave;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAppliedBy() {
        return appliedBy;
    }

    public void setAppliedBy(User appliedBy) {
        this.appliedBy = appliedBy;
    }

    public User getProxyBy() {
        return proxyBy;
    }

    public void setProxyBy(User proxyBy) {
        this.proxyBy = proxyBy;
    }

    public Date getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(Date appliedOn) {
        this.appliedOn = appliedOn;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhoneOnLeave() {
        return phoneOnLeave;
    }

    public void setPhoneOnLeave(String phoneOnLeave) {
        this.phoneOnLeave = phoneOnLeave;
    }

    public String getLocationOnLeave() {
        return locationOnLeave;
    }

    public void setLocationOnLeave(String locationOnLeave) {
        this.locationOnLeave = locationOnLeave;
    }

    public User getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(User acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public User getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(User receivedBy) {
        this.receivedBy = receivedBy;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }

    public User getRejectBy() {
        return rejectBy;
    }

    public void setRejectBy(User rejectBy) {
        this.rejectBy = rejectBy;
    }
}
