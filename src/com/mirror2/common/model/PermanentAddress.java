package com.mirror2.common.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Permanent")
public class PermanentAddress extends Address implements Serializable {

}
