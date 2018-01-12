package com.mirror2.csd.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * @author M Ayatullah
 */
@Entity
@Table(name = "CSD_BUILDING")
public class Building implements Serializable {
    public Building(){}
    public Building(Long id){this.id = id;}
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "BUILDING_NAME")
    private String buildingName;

    @Column(name = "NAME_ALIAS")
    private String nameAlias;

    @Column(name = "FLOOR_SIZES")
    private String floorSizes;

    @Column(name = "FLOOR_SIZE")
    private Integer totalFloorSize;

    @Column(name = "NUMBER_OF_FLOOR")
    private Integer numberOfFloor;

    @Column(name = "NUMBER_OF_UNIT")
    private Integer numberOfUnit;

    @Column(columnDefinition = "text", name = "APT_IDS")
    private String aptIds;     //101|102|103|201|202|203|301|302|303|401|402|403|501|502|503|601|602|603|701|702|703|801|802|803|901|902|903


    @Column(name = "HAND_OVER")
    private String handOver;

    @Column(name = "LAND_AREA")
    private Double landArea;        //Unit : decimal

    @ManyToOne
    Project project;

    @OneToMany
    @JoinTable(name = "CSDJT_BUILDING_CUSTOMER", joinColumns = @JoinColumn(name = "BUILDING_ID"),
            inverseJoinColumns = @JoinColumn(name = "CUSTOMER_ID"))
    private List<Customer> customers = new ArrayList<Customer>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getNameAlias() {
        return nameAlias;
    }

    public void setNameAlias(String nameAlias) {
        this.nameAlias = nameAlias;
    }

    public String getFloorSizes() {
        return floorSizes;
    }

    public void setFloorSizes(String floorSizes) {
        this.floorSizes = floorSizes;
    }

    public Integer getTotalFloorSize() {
        return totalFloorSize;
    }

    public void setTotalFloorSize(Integer totalFloorSize) {
        this.totalFloorSize = totalFloorSize;
    }

    public Integer getNumberOfFloor() {
        return numberOfFloor;
    }

    public void setNumberOfFloor(Integer numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    public Integer getNumberOfUnit() {
        return numberOfUnit;
    }

    public void setNumberOfUnit(Integer numberOfUnit) {
        this.numberOfUnit = numberOfUnit;
    }

    public String getAptIds() {
        return aptIds;
    }

    public void setAptIds(String aptIds) {
        this.aptIds = aptIds;
    }

    public String getHandOver() {
        return handOver;
    }

    public void setHandOver(String handOver) {
        this.handOver = handOver;
    }

    public Double getLandArea() {
        return landArea;
    }

    public void setLandArea(Double landArea) {
        this.landArea = landArea;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
