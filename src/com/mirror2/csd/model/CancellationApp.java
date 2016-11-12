package com.mirror2.csd.model;

import com.mirror2.security.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author M Ayatullah
 */

@Entity
@DiscriminatorValue("Cancellation")
public class CancellationApp extends Application implements Serializable{

    @Column(columnDefinition="Decimal(10,2) default '0.00'")
    private Double depositedAmt;
    @Column(columnDefinition="Decimal(10,2) default '0.00'")
    private Double demandedAmt;
    private String refAccountName;
    private String refAccountNo;
    private String refBank;
    private String refBranch;


    public Double getDepositedAmt() {
        return depositedAmt;
    }

    public void setDepositedAmt(Double depositedAmt) {
        this.depositedAmt = depositedAmt;
    }

    public Double getDemandedAmt() {
        return demandedAmt;
    }

    public void setDemandedAmt(Double demandedAmt) {
        this.demandedAmt = demandedAmt;
    }

    public String getRefAccountName() {
        return refAccountName;
    }

    public void setRefAccountName(String refAccountName) {
        this.refAccountName = refAccountName;
    }

    public String getRefAccountNo() {
        return refAccountNo;
    }

    public void setRefAccountNo(String refAccountNo) {
        this.refAccountNo = refAccountNo;
    }

    public String getRefBank() {
        return refBank;
    }

    public void setRefBank(String refBank) {
        this.refBank = refBank;
    }

    public String getRefBranch() {
        return refBranch;
    }

    public void setRefBranch(String refBranch) {
        this.refBranch = refBranch;
    }
}