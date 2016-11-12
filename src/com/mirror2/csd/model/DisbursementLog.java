package com.mirror2.csd.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author M Ayatullah
 */
@Entity
@Table (name="CSD_DISBURSEMENT_LOG")
public class DisbursementLog implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String crDr;

    @Temporal(TemporalType.DATE)
    private Date recordDate;

    private String head; // = MirrorConstants.PAYMENT_HEAD_CP[1];        // INST, DP, CP, SP

    private Double RDP;   //RDP = Regular Due Payment,
    private Double ODP;     // = Over-due Payment
    private Double ADP;     // = Advance Payment

    @Transient
    private Double total;

    public Double getTotal() {
        return RDP+ODP+ADP;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrDr() {
        return crDr;
    }

    public void setCrDr(String crDr) {
        this.crDr = crDr;
    }


    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Double getRDP() {
        return RDP;
    }

    public void setRDP(Double RDP) {
        this.RDP = RDP;
    }

    public Double getODP() {
        return ODP;
    }

    public void setODP(Double ODP) {
        this.ODP = ODP;
    }

    public Double getADP() {
        return ADP;
    }

    public void setADP(Double ADP) {
        this.ADP = ADP;
    }
}
