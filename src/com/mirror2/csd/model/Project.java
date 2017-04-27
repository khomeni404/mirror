package com.mirror2.csd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: M Ayatullah
 * Date: 8/22/14
 * Time: 11:31 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="CSD_PROJECT")
public class Project implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="PROJECT_NAME")
    private String projectName;

    @Column(name="LOCATION")
    private String location;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="HOT_LINE")
    private String hotLine;

    @Column(name="NUMBER_OF_BUILDING")
    private Integer numberOfBuilding;

    @Column(name="LAND_AREA")
    private Double landArea;        //Unit : decimal

    @OneToMany
    @JoinTable(name="CSDJT_PROJECT_BUILDING", joinColumns = @JoinColumn(name="PROJECT_ID"),
            inverseJoinColumns = @JoinColumn(name = "BUILDING_ID"))
    private List<Building> buildings = new ArrayList<Building>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotLine() {
        return hotLine;
    }

    public void setHotLine(String hotLine) {
        this.hotLine = hotLine;
    }

    public Integer getNumberOfBuilding() {
        return numberOfBuilding;
    }

    public void setNumberOfBuilding(Integer numberOfBuilding) {
        this.numberOfBuilding = numberOfBuilding;
    }

    public Double getLandArea() {
        return landArea;
    }

    public void setLandArea(Double landArea) {
        this.landArea = landArea;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }
}
