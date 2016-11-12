package com.mirror2.csd.service;

import com.mirror2.csd.model.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CsdService {
    //Report
    public List<Map<String, Object>> getSalesCountListByStatus();

    public List<Map<String, Object>> getSalesCountListByOffer();

    public Map<String, Object> getSalesCountListByMID();

    public Double getTotalInstallment(Customer customer);

    public Double getTotalInstallmentAmount(Customer customer);

    public Double getPayableInstallment(Customer customer);

    public Double getPayableInstallmentAmount(Customer customer);

    public Double getTotalOtherPaymentAmount(Customer customer, String paymentType);

    public Double getPayableOtherPaymentAmount(Customer customer, String paymentType);

    public List getAllPaymentSummary(String range);

    public Double getTotalDeposit(String cid);

    public Double getTotalDeposit(Customer customer);

    public Double getFloorPrice(Customer customer);

    public Double getAllDeposit();

    public List getReceiptVoucherForStatement();

    public List getAllReceiptAndVouchers(Customer customer);

    public Double getTotalByMoneyReceiptByMR(Customer customer, String status);

    public Double getTotalAdjustmentCr(Customer customer);

    public Double getTotalAdjustmentDr(Customer customer);

    public Double getTotalWithdraw(Customer customer);

    public List<Customer> getAllCustomerByMidType(String finFor);

    public List<Customer> getAllCustomerByName(String name);

    public List getCollectionReport(String start, String end);

    public List<Map<String, Comparable>> getInstallmentInfo(Customer customer);

    //Customer .............
    public boolean save(Customer customer);

    public boolean update(Customer customer);

    public boolean delete(Customer customer);

    public List<Customer> findAllCustomer();

    public Customer getCustomer(Long id);

    public Customer getCustomer(String cid);

    public List<Customer> getCustomerListByStatus(String status);

    public List<Customer> getCustomerListByBookingDate(String status, Date from, Date to);

    public List<Customer> getCustomerListByBookingDate(Offer offer, Date from, Date to);

    // Disbursement Log
    public boolean save(DisbursementLog log);

    public boolean update(DisbursementLog log);

    public boolean delete(DisbursementLog log);

    public List<DisbursementLog> findAllDisbursementLog();

    public DisbursementLog getDisbursementLog(Long id);

    public List<Object[]> getDisbursementLogReport(Date from, Date to);

    //Building ................

    public boolean save(Building building);

    public boolean update(Building building);

    public boolean delete(Building building);

    public List<Building> findAllBuilding();

    public Building getBuilding(Long id);

    //Director
    public boolean save(Director director);

    public boolean update(Director director);

    public boolean delete(Director director);

    public List<Director> findAllDirector();

    public Director getDirector(Long id);

    //Application
    public boolean save(Application application);

    public boolean update(Application application);

    public boolean delete(Application application);

    public List<Application> findAllApplication();

    public Application getApplication(Long id);

    //CancellationApp
    public boolean save(CancellationApp app);

    public boolean update(CancellationApp app);

    public boolean delete(CancellationApp app);

    public List<CancellationApp> findAllCancellationApp();

    public CancellationApp getCancellationApp(Long id);

    //Installment
    public boolean save(Installment installment);

    public boolean update(Installment installment);

    public boolean delete(Installment installment);

    public boolean deleteAllInstallment(Customer customer);

    public List<Installment> findAllInstallment();

    public Installment getInstallment(Long id);

    //Money Receipt
    public boolean save(MoneyReceipt moneyReceipt);

    public boolean update(MoneyReceipt moneyReceipt);

    public boolean delete(MoneyReceipt moneyReceipt);

    public List<MoneyReceipt> findAllMoneyReceipt();

    public List<MoneyReceipt> findAllMoneyReceipt(Integer start, Integer limit);

    public List<MoneyReceipt> waitingMoneyReceiptForApprove();

    public List<MoneyReceipt> findAllMoneyReceipt(String status);

    public MoneyReceipt getMoneyReceipt(Long id);

    public MoneyReceipt getMoneyReceipt(String mrNo);

    //Follow Up History
    public boolean setInactiveHistory(Customer customer);

    public boolean save(FollowUpHistory history);

    public boolean update(FollowUpHistory history);

    public boolean delete(FollowUpHistory history);

    public List<FollowUpHistory> findAllFollowUpHistory();

    public List<FollowUpHistory> findAllFollowUpHistory(Customer customer);

    public List<FollowUpHistory> findAllFollowUpHistory(Date date, String of);

    public FollowUpHistory getFollowUpHistory(Long id);

    //Other payments
    public boolean save(OtherPayment otherPayment);

    public boolean update(OtherPayment otherPayment);

    public boolean delete(OtherPayment otherPayment);

    public List<OtherPayment> findAllOtherPayment();

    public OtherPayment getOtherPayment(Long id);

    //
    public boolean save(MoneyDisburse moneyDisburse);

    public boolean update(MoneyDisburse moneyDisburse);

    public boolean delete(MoneyDisburse moneyDisburse);

    public List<MoneyDisburse> findAllMoneyDisburse();

    public List<MoneyDisburse> findAllMoneyDisburses(String findFor);

    public List findAllMoneyDisbursesSummary(String findFor);

    public Double getTotalDisburse(Customer customer);

    //Money Disburse
    public MoneyDisburse getMoneyDisburse(Long id);

    //Emp CSD
    public boolean save(EmpCsd empCsd);

    public boolean update(EmpCsd empCsd);

    public boolean delete(EmpCsd empCsd);

    public List<EmpCsd> findAllEmpCsd();

    public EmpCsd getEmpCsd(Long id);

    //Project
    public boolean save(Project project);

    public boolean update(Project project);

    public boolean delete(Project project);

    public List<Project> findAllProject();

    public Project getProject(Long id);

    //Shareholder
    public boolean save(Shareholder shareholder);

    public boolean update(Shareholder shareholder);

    public boolean delete(Shareholder shareholder);

    public List<Shareholder> findAllShareholder();

    public Shareholder getShareholder(Long id);

    //Voucher
    public boolean save(Voucher voucher);

    public boolean update(Voucher voucher);

    public boolean delete(Voucher voucher);

    public List<Voucher> findAllVoucher();

    public Voucher getVoucher(Long id);

    public List<Voucher> getVoucherList(String status);

    public List<Voucher> findAllVoucher(Boolean b);

    //MID
    public boolean save(MID mid);

    public boolean update(MID mid);

    public boolean delete(MID mid);

    public List<MID> findAllMID();

    public List<MID> findAllMID(String type);

    public MID getMID(Long id);

    public MID getMID(String mid);

    //Offer
    public boolean save(Offer offer);

    public boolean update(Offer offer);

    public boolean delete(Offer offer);

    public List<Offer> findAllOffer();

    public Offer getOffer(Long id);

    //IncentiveDist
    public boolean save(IncentiveDist dist);

    public boolean update(IncentiveDist dist);

    public boolean delete(IncentiveDist dist);

    public List<IncentiveDist> findAllIncentiveDist();

    public List<IncentiveDist> findAllIncentiveDist(Customer customer);

    public List<IncentiveDist> findAllIncentiveDist(Integer status);

    public String findAllIncentiveDistString(Customer customer);

    public IncentiveDist getIncentiveDist(Long id);

    public Double getDistributedIncentive(MID mid);

    public Double getDistributedIncentive(Customer customer);
}
