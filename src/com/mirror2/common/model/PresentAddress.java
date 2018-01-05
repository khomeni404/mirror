package com.mirror2.common.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Present")
public class PresentAddress extends Address implements Serializable {


}
