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
@Table(name = "TIME_THE_DAY_OFF")
public class TheDayOff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User employee;

    @ManyToOne
    private User approveBy;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public User getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(User approveBy) {
        this.approveBy = approveBy;
    }
}
