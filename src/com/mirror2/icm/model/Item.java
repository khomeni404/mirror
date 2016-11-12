package com.mirror2.icm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ICM_ITEM")
public class Item implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private ItemCategory itemCategory;

    private String description;

    private Double approximatePrice;

    @ManyToOne
    private Unit unit;

    @OneToMany
    @JoinTable(name = "ICM_JT_ITEM_ITEM_HISTORY",
            joinColumns = @JoinColumn(name = "ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name = "HISTORY_ID"))
    private List<ItemUsageHistory> itemUsageHistories = new ArrayList<ItemUsageHistory>();

    @OneToMany
    @JoinTable(name = "ICM_JT_ITEM_ITEM_OUT",
            joinColumns = @JoinColumn(name = "ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_OUT_ID"))
    private List<ItemOut> itemOuts = new ArrayList<ItemOut>();

    @OneToMany
    @JoinTable(name = "ICM_JT_ITEM_ITEM_IN",
            joinColumns = @JoinColumn(name = "ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_IN_ID"))
    private List<ItemIn> itemIns = new ArrayList<ItemIn>();

    @OneToMany
    @JoinTable(name = "ICM_JT_ITEM_INTERNAL_REQUISITION",
            joinColumns = @JoinColumn(name = "ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name = "REQUISITION_ID"))
    private List<InternalRequisition> internalRequisitions = new ArrayList<InternalRequisition>();


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

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<ItemUsageHistory> getItemUsageHistories() {
        return itemUsageHistories;
    }

    public void setItemUsageHistories(List<ItemUsageHistory> itemUsageHistories) {
        this.itemUsageHistories = itemUsageHistories;
    }

    public List<ItemOut> getItemOuts() {
        return itemOuts;
    }

    public void setItemOuts(List<ItemOut> itemOuts) {
        this.itemOuts = itemOuts;
    }

    public List<ItemIn> getItemIns() {
        return itemIns;
    }

    public void setItemIns(List<ItemIn> itemIns) {
        this.itemIns = itemIns;
    }

    public Double getApproximatePrice() {
        return approximatePrice;
    }

    public void setApproximatePrice(Double approximatePrice) {
        this.approximatePrice = approximatePrice;
    }

    public List<InternalRequisition> getInternalRequisitions() {
        return internalRequisitions;
    }

    public void setInternalRequisitions(List<InternalRequisition> internalRequisitions) {
        this.internalRequisitions = internalRequisitions;
    }
}
