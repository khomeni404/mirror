package com.mirror2.common.model;

import com.mirror2.security.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 24/08/16
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 24/08/16
 * Version : 1.0
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "COM_DOCUMENT_MASTER")
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "given_name")
    private String givenName;

    @Column(name = "dir")
    private String resourceDirectory;

    private String extension;

    @ManyToOne
    private User owner;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "record_on")
    private Date recordDate;

    @Transient
    public String getDiscriminatorValue() {
        DiscriminatorValue val = this.getClass().getAnnotation(DiscriminatorValue.class);
        return val == null ? this.getClass().getSimpleName() : val.value();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getExtension() {
        return extension;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getResourceDirectory() {
        return resourceDirectory;
    }

    public void setResourceDirectory(String resourceDirectory) {
        this.resourceDirectory = resourceDirectory;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}