package com.mirror2.admin.model;

import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.Director;
import com.mirror2.csd.model.MID;
import com.mirror2.csd.model.Project;
import com.mirror2.security.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author M Ayatullah
 */
@Entity
@Table(name = "ADMIN_BOARD_MEETING")
public class BoardMeeting implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "meeting_name")
    private String meetingName;     //1st Meeting, 2nd Meeting, 3rd Meeting, 4th Meeting  as well.

    @Temporal(TemporalType.DATE)
    private Date heldOn;

    @Column(columnDefinition = "text")
    private String remarks;


    @OneToMany
    @JoinTable(name="ADMIN_JT_MEMBER_BOD_MEETING", joinColumns=@JoinColumn(name="MEMBER_ID"),
            inverseJoinColumns=@JoinColumn(name="MEETING_ID"))
    List<Director> directorList = new ArrayList<Director>();


    @Temporal(TemporalType.DATE)
    private Date recordOn;

    @ManyToOne
    private User recordBy;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public Date getHeldOn() {
        return heldOn;
    }

    public void setHeldOn(Date heldOn) {
        this.heldOn = heldOn;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getRecordOn() {
        return recordOn;
    }

    public void setRecordOn(Date recordOn) {
        this.recordOn = recordOn;
    }

    public User getRecordBy() {
        return recordBy;
    }

    public void setRecordBy(User recordBy) {
        this.recordBy = recordBy;
    }

    public List<Director> getDirectorList() {
        return directorList;
    }

    public void setDirectorList(List<Director> directorList) {
        this.directorList = directorList;
    }
}
