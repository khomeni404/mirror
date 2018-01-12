package com.mirror2.csd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author M Ayatullah
 */
@Entity
@Table(name = "CSD_LOCATION")
public class Location implements Serializable {
    public Location(){}
    public Location(Long id){this.id = id;}

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String country;

    @Override
    public String toString() {
        return this.name + ", " + country;
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
