package com.mirror2.csd.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 9/3/14
 * Time: 11:56 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@DiscriminatorValue("adjustment")
public class AdjustmentVoucher extends Voucher {

    //private String adjustTo;
    //private String adjustFrom;
}
