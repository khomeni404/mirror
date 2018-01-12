package com.mirror2.csd.model;

import com.mirror2.security.model.User;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Date;

/**
 * @author M Ayatullah
 */

@Entity
@Table(name="CSD_CUSTOMER",
        uniqueConstraints = @UniqueConstraint(columnNames = {"CUSTOMER_ID"}))
public class Customer implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="BOOKING_DATE")
    @Temporal (TemporalType.DATE)
    private Date bookingDate;

    @Column(name="CUSTOMER_ID")
    private String CID;

    private String name;

    private String keyName;

    @Column(name="APARTMENT_ID")
    private String AID;

    @Column(name="COUNTRY_LOCATION")
    private String countryLocation;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "loc_id")
    private Location location;

    @ManyToOne
    private MID referenceBy;

    @ManyToOne
    private User approvedBy;

    @ManyToOne
    private Offer offer;

    private String status;	//Processing | approved | cancelled | not approved  | Cancelled & Refunded | Cancelled & Adjusted | Cancelled & Transferred

    @Column(name = "sales_type")
    private String salesType;

    @ManyToOne
    private MID careBy;

    @Column(name="CELL_PHONE")
    private String cellPhone;

    @Column(name="ALTERNATIVE_PHONE")
    private String altPhone;

    @Column(name="EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name="MAILING_ADDRESS")
    private String mailingAddress;

    @Column(name="FLOOR_SIZE")
    private Integer floorSize;

    private Double price;

    @Column(columnDefinition="text")
    private String notes;

    @Column(name="HANDOVER_DATE")
    @Temporal (TemporalType.DATE)
    private Date handoverDate;

    @Column(name="PAYMENT_TYPE")
    private String paymentType; //Installment | Onetime | Others

    @ManyToOne
    private Building building;

    @OneToOne
    private MoneyDisburse moneyDisburse;

    //Collections ORM
    @OneToMany
    @JoinTable(name="CSDJT_CUSTOMER_HISTORY", joinColumns=@JoinColumn(name="CUSTOMER_ID"),
            inverseJoinColumns=@JoinColumn(name="HISTORY_ID"))
    private List<FollowUpHistory> historyList = new ArrayList<FollowUpHistory>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    @Fetch(FetchMode.SELECT)
    private List<Application> applicationList = new ArrayList<Application>();


    @OneToMany
	@JoinTable(name="CSDJT_CUSTOMER_RECEIPT", joinColumns=@JoinColumn(name="CUSTOMER_ID"),
				inverseJoinColumns=@JoinColumn(name="RECEIPT_ID"))
    private List<MoneyReceipt> moneyReceipts = new ArrayList<MoneyReceipt>();

    @OneToMany
    @JoinTable(name="CSDJT_CUSTOMER_OTHER_PAYMENT", joinColumns = @JoinColumn(name="CUSTOMER_ID"),
                inverseJoinColumns = @JoinColumn(name="OTHER_PAYMENT_ID"))
    private List<OtherPayment> otherPayments = new ArrayList<OtherPayment>();

    @OneToMany
    @JoinTable(name="CSDJT_CUSTOMER_INSTALLMENT", joinColumns = @JoinColumn(name="Customer_ID"),
                inverseJoinColumns = @JoinColumn(name="INSTALLMENT_ID"))
    private List<Installment> installments = new ArrayList<Installment>();

    @OneToMany
    @JoinTable(name="CSDJT_CUSTOMER_VOUCHER", joinColumns = @JoinColumn(name="CUSTOMER_ID"),
            inverseJoinColumns = @JoinColumn(name="VOUCHER_ID"))
    private List<Voucher> vouchers = new ArrayList<Voucher>();

    @OneToMany
    @JoinTable(name="CSD_JT_CUSTOMER_INCENTIVE_DIST", joinColumns = @JoinColumn(name="CUSTOMER_ID"),
            inverseJoinColumns = @JoinColumn(name="INCENTIVE_DIST_ID"))
    private List<IncentiveDist> distList = new ArrayList<IncentiveDist>();


    @Transient
    private Integer totalInst;
    @Transient
    private Double totalInstAmt;
    @Transient
    private Integer recoverableInst;
    @Transient
    private Double paidInst;
    @Transient
    private Double dueInst;


    public Integer getTotalInst() {
        return totalInst;
    }

    public void setTotalInst(Integer totalInst) {
        this.totalInst = totalInst;
    }

    public Double getTotalInstAmt() {
        return totalInstAmt;
    }

    public void setTotalInstAmt(Double totalInstAmt) {
        this.totalInstAmt = totalInstAmt;
    }

    public Integer getRecoverableInst() {
        return recoverableInst;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Double getPaidInst() {
        return paidInst;
    }

    public void setPaidInst(Double paidInst) {
        this.paidInst = paidInst;
    }

    public void setRecoverableInst(Integer recoverableInst) {
        this.recoverableInst = recoverableInst;
    }

    public Double getDueInst() {
        return dueInst;
    }

    public void setDueInst(Double dueInst) {
        this.dueInst = dueInst;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public List<Application> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<Application> applicationList) {
        this.applicationList = applicationList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<FollowUpHistory> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<FollowUpHistory> historyList) {
        this.historyList = historyList;
    }

    public String getAID() {
        return AID;
    }

    public void setAID(String AID) {
        this.AID = AID;
    }

    public String getCountryLocation() {
        return countryLocation;
    }

    public void setCountryLocation(String countryLocation) {
        this.countryLocation = countryLocation;
    }

    public String getSalesType() {
        return salesType;
    }

    public void setSalesType(String salesType) {
        this.salesType = salesType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public MoneyDisburse getMoneyDisburse() {
        return moneyDisburse;
    }

    public void setMoneyDisburse(MoneyDisburse moneyDisburse) {
        this.moneyDisburse = moneyDisburse;
    }

    public String getAltPhone() {
        return altPhone;
    }

    public void setAltPhone(String altPhone) {
        this.altPhone = altPhone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public Integer getFloorSize() {
        return floorSize;
    }

    public void setFloorSize(Integer floorSize) {
        this.floorSize = floorSize;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getHandoverDate() {
        return handoverDate;
    }

    public void setHandoverDate(Date handoverDate) {
        this.handoverDate = handoverDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<MoneyReceipt> getMoneyReceipts() {
        return moneyReceipts;
    }

    public void setMoneyReceipts(List<MoneyReceipt> moneyReceipts) {
        this.moneyReceipts = moneyReceipts;
    }

    public List<OtherPayment> getOtherPayments() {
        return otherPayments;
    }

    public void setOtherPayments(List<OtherPayment> otherPayments) {
        this.otherPayments = otherPayments;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Installment> installments) {
        this.installments = installments;
    }

    public List<Voucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<Voucher> vouchers) {
        this.vouchers = vouchers;
    }

    public MID getReferenceBy() {
        return referenceBy;
    }

    public void setReferenceBy(MID referenceBy) {
        this.referenceBy = referenceBy;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public MID getCareBy() {
        return careBy;
    }

    public void setCareBy(MID careBy) {
        this.careBy = careBy;
    }

    public List<IncentiveDist> getDistList() {
        return distList;
    }

    public void setDistList(List<IncentiveDist> distList) {
        this.distList = distList;
    }
}