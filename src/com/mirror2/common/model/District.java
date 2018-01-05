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
@Table(name = "COM_DISTRICT")
public class District implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Integer population;

    private Double area;

    @Column(name = "PPK")
    private Double populationDensity;

    @ManyToOne
    private Division division;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "district")
    @Fetch(FetchMode.SELECT)
    private List<PoliceStation> policeStationList = new ArrayList<PoliceStation>(0);

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

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
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

    public Double getPopulationDensity() {
        return populationDensity;
    }

    public void setPopulationDensity(Double populationDensity) {
        this.populationDensity = populationDensity;
    }

    public List<PoliceStation> getPoliceStationList() {
        return policeStationList;
    }

    public void setPoliceStationList(List<PoliceStation> policeStationList) {
        this.policeStationList = policeStationList;
    }
}
