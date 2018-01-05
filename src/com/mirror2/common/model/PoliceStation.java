package com.mirror2.common.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 2016-10-12 for qrf
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Version : 1.0
 */
@Entity
@Table(name = "COM_POLICE_STATION")
public class PoliceStation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Integer population;

    private Double area;

    @ManyToOne
    private District district;


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

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
