package com.mirror2.csd.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * * User: M Ayatullah
 * Date: 10/2/14
 * Time: 6:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class PhoneInquary implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String contactOne;

    private String contactTwo;

    @Temporal(TemporalType.DATE)
    private Date callDate;

    private String reference;

    private String noteUpdate;

    private Date schedule;


    private String status;


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

    public String getContactOne() {
        return contactOne;
    }

    public void setContactOne(String contactOne) {
        this.contactOne = contactOne;
    }

    public String getContactTwo() {
        return contactTwo;
    }

    public void setContactTwo(String contactTwo) {
        this.contactTwo = contactTwo;
    }

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNoteUpdate() {
        return noteUpdate;
    }

    public void setNoteUpdate(String noteUpdate) {
        this.noteUpdate = noteUpdate;
    }

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
