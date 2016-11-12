package com.mirror2.icm.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ICM_STORE")
public class Store implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String address;

    @OneToMany
    @JoinTable(name = "ICM_JT_STORE_ITEM_IN",
            joinColumns = @JoinColumn(name = "STORE_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_IN_ID"))
    private List<ItemIn> itemIn = new ArrayList<ItemIn>();

    @OneToMany
    @JoinTable(name = "ICM_JT_STORE_ITEM_OUT",
            joinColumns = @JoinColumn(name = "STORE_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_OUT_ID"))
    private List<ItemOut> itemOuts = new ArrayList<ItemOut>();

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ItemIn> getItemIn() {
        return itemIn;
    }

    public void setItemIn(List<ItemIn> itemIn) {
        this.itemIn = itemIn;
    }

    public List<ItemOut> getItemOuts() {
        return itemOuts;
    }

    public void setItemOuts(List<ItemOut> itemOuts) {
        this.itemOuts = itemOuts;
    }
}
