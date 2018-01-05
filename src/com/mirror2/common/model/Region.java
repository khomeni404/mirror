package com.mirror2.common.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 2016-10-12 for qrf
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Version : 1.0
 */
@Entity
@Table(name = "COM_REGION")
public class Region  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "region")
    @Fetch(FetchMode.SELECT)
    private List<Country> countryList = new ArrayList<Country>(0);

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }
}
