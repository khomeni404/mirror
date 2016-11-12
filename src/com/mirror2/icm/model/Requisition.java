package com.mirror2.icm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Khomeni
 * Date: 10/20/14
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "ICM_REQUISITION")
public class Requisition  implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
