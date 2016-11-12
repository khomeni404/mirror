package com.mirror2.icm.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ICM_CATEGORY")
public class ItemCategory implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private ItemCategory parentItemCategory;

    @OneToMany
    @JoinTable(name = "ICM_JT_CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<Item> items = new ArrayList<Item>();

    @OneToMany
    @JoinTable(name = "ICM_JT_CATEGORY_PARENT_CATEGORY",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "PARENT_CATEGORY_ID"))
    private List<ItemCategory> itemCategories = new ArrayList<ItemCategory>();

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

    public ItemCategory getParentItemCategory() {
        return parentItemCategory;
    }

    public void setParentItemCategory(ItemCategory parentItemCategory) {
        this.parentItemCategory = parentItemCategory;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<ItemCategory> getItemCategories() {
        return itemCategories;
    }

    public void setItemCategories(List<ItemCategory> itemCategories) {
        this.itemCategories = itemCategories;
    }

}
