package com.mirror2.icm.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


public class MyItem implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private Double r;
}
