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
@Table(name="CSD_APPLICATION_MASTER")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal (TemporalType.DATE)
    private Date applicationDate;
    @ManyToOne
    private Customer customer;
    private String applicantName;
    private Integer status;
    @ManyToOne
    private User approveBy;
    @ManyToOne
    private User dataOperator;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public User getDataOperator() {
        return dataOperator;
    }

    public void setDataOperator(User dataOperator) {
        this.dataOperator = dataOperator;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(User approveBy) {
        this.approveBy = approveBy;
    }
}
