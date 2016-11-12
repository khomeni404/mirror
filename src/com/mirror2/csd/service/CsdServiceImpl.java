package com.mirror2.csd.service;

import com.mirror2.admin.model.BoardMeeting;
import com.mirror2.admin.service.AdminService;
import com.mirror2.csd.dao.*;
import com.mirror2.csd.model.*;
import com.mirror2.util.MirrorConstants;
import com.mirror2.util.MyProperty;
import com.mirror2.util.TheDates;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.ResultTransformer;
import org.smslib.AGateway;
import org.smslib.InboundMessage;
import org.smslib.OutboundMessage;
import org.smslib.modem.SerialModemGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CsdServiceImpl implements CsdService {
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private BuildingDAO buildingDAO;
    @Autowired
    private DirectorDAO directorDAO;
    @Autowired
    private InstallmentDAO installmentDAO;
    @Autowired
    private ApplicationDAO applicationDAO;
    @Autowired
    private CancellationAppDAO cancellationAppDAO;

    @Autowired
    private ProjectDAO projectDAO;
    @Autowired
    private ShareholderDAO shareholderDAO;
    @Autowired
    private MoneyReceiptDAO moneyReceiptDAO;
    @Autowired
    private OtherPaymentDAO otherPaymentDAO;
    @Autowired
    private VoucherDAO voucherDAO;
    @Autowired
    private MidDAO midDAO;
    @Autowired
    private OfferDAO offerDAO;
    @Autowired
    private MoneyDisburseDAO moneyDisburseDAO;
    @Autowired
    private EmpCsdDAO empCsdDAO;

    @Autowired
    private FollowUpHistoryDAO historyDAO;

    @Autowired
    private IncentiveDistDAO incentiveDistDAO;

    @Autowired
    private AdminService adminService;
    @Autowired
    private DisbursementLogDAO disbursementLogDAO;

    //==========================================

    //Reporting Methods

    //TODO...
    //Date from = new TheDates().toDate("2015-06-17");

    @Override
    public List<Map<String, Object>> getSalesCountListByStatus() {
        //BoardMeeting lastBodMeeting  = adminService.getLastBoardMeetingDate();
        //Date from = lastBodMeeting.getHeldOn();
        Date from = adminService.getLastBoardMeetingDate();
        String[] statusArr = {"Approved", MirrorConstants.PROCESSING, "Cancelled", "Refunded"};
        String[] statusDisplayNameArr = {"Approved", MirrorConstants.PROCESSING, "On Cancellation", "Refunded"};
        List<Map<String, Object>> rtList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for (int i = 0; i < statusArr.length; i++) {
            map = new HashMap<String, Object>();
            String status = statusArr[i];
            map.put("name", statusDisplayNameArr[i]);
            map.put("sales", customerDAO.getCustomerListByStatus(status).size());
            map.put("bod", customerDAO.getCustomerListByBookingDate(status, from, new Date()).size());
            rtList.add(map);
        }
        return rtList;
    }

    @Override
    public List<Map<String, Object>> getSalesCountListByOffer() {
        //BoardMeeting lastBodMeeting  = adminService.getLastBoardMeetingDate();
        Date from = adminService.getLastBoardMeetingDate();

        List<Offer> offers = offerDAO.findAllOffer();
        List<Map<String, Object>> rtList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for (Offer o : offers) {
            map = new HashMap<String, Object>();
            String name = o.getOfferName();
            map.put("offerName", name);
            map.put("sales", customerDAO.getTotalCustomerByOffer(o));
            map.put("bod", customerDAO.getCustomerListByBookingDate(o, from, new Date()).size());
            rtList.add(map);
        }
        return rtList;
    }

    @Override
    public Map<String, Object> getSalesCountListByMID() {
        String[] salesmanList = {"Director", "Share Holder", "Marketing", "FMT"};
        List<MID> midList = midDAO.findAllMID();
        Integer dir = 0;
        Integer sha = 0;
        Integer mkt = 0;
        Integer fmt = 0;
        Integer other = 0;
        for (MID m : midList) {
            Integer nos = customerDAO.getTotalCustomerByMID(m);
            if (m.getMid().startsWith("D")) {
                dir += nos;
            } else if (m.getMid().startsWith("S")) {
                sha += nos;
            } else if (m.getMid().startsWith("M")) {
                mkt += nos;
            } else if (m.getMid().startsWith("F")) {
                fmt += nos;
            } else {
                other += nos;
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("director", dir);
        map.put("shareHolder", sha);
        map.put("mkt", mkt);
        map.put("fmt", fmt);
        map.put("others", other);
        return map;
    }

    @Override
    public Double getTotalInstallment(Customer customer) {
        List<Installment> installments = customer.getInstallments();
        Double counter = 0.0;
        if (installments != null) {
            for (Installment installment : installments)
                counter++;
        }
        return counter;
    }

    @Override
    public Double getPayableInstallment(Customer customer) {
        List<Installment> installments = customer.getInstallments();
        Double counter = 0.0;
        if (installments != null) {
            for (Installment installment : installments) {
                if (installment.getDeadLine().compareTo(new Date()) <= 0) {
                    counter++;
                }
            }
        }
        return counter;
    }

    @Override
    public Double getTotalInstallmentAmount(Customer customer) {
        List<Installment> installments = customer.getInstallments();
        Double amount = 0.0;
        if (installments != null) {
            for (Installment installment : installments)
                amount = amount + installment.getAmount();
        }
        return amount;
    }

    @Override
    public Double getPayableInstallmentAmount(Customer customer) {
        List<Installment> installments = customer.getInstallments();
        Double amount = 0.0;
        if (installments != null) {
            for (Installment installment : installments) {
                if (installment.getDeadLine().compareTo(new Date()) <= 0) {
                    amount = amount + installment.getAmount();
                }
            }
        }
        return amount;
    }

    @Override
    public Double getTotalOtherPaymentAmount(Customer customer, String paymentType) {
        String type = paymentType.toUpperCase();
        List<OtherPayment> otherPayments = customer.getOtherPayments();
        Double amount = 0.0;
        if (otherPayments != null) {
            for (OtherPayment otherPayment : otherPayments) {
                if (otherPayment.getPaymentType().toUpperCase().equals(type)) {
                    amount = amount + otherPayment.getAmount();
                }
            }
        }
        return amount;
    }

    @Override
    public Double getPayableOtherPaymentAmount(Customer customer, String paymentType) {
        String type = paymentType.toUpperCase();
        List<OtherPayment> otherPayments = customer.getOtherPayments();
        Double amount = 0.0;
        if (otherPayments != null) {
            for (OtherPayment otherPayment : otherPayments) {
                if (otherPayment.getDeadLine().compareTo(new Date()) <= 0 && otherPayment.getPaymentType().toUpperCase().equals(type)) {
                    amount = amount + otherPayment.getAmount();
                } else if (otherPayment.getDeadLine().compareTo(new Date()) <= 0 && type.equals("ALL")) {
                    amount = amount + otherPayment.getAmount();
                }
            }
        }
        return amount;
    }

    @Override
    public Double getFloorPrice(Customer customer) {
        Double floorPrice = 0.0;
        //Calculate total price = all other payments + all installment amount
        List<OtherPayment> otherPayments = customer.getOtherPayments();
        for (OtherPayment op : otherPayments) {
            floorPrice = floorPrice + op.getAmount();
        }

        //Add total inst amount
        List<Installment> installments = customer.getInstallments();
        for (Installment inst : installments) {
            floorPrice = floorPrice + inst.getAmount();
        }
        return floorPrice;
    }

    @Override
    public List getAllPaymentSummary(String range) {
        String[] array = range.split("-");

        List<Map<String, Comparable>> summaryList = new ArrayList<Map<String, Comparable>>();
        Map<String, Comparable> summaryMap;

        List<Customer> customers = findAllCustomer();
        List<MoneyReceipt> moneyReceipts;
        List<Voucher> vouchers;
        Double paidAmount;
        Double totalPrice;

        for (Customer customer : customers) {
            if (!customer.getStatus().equals("Refunded")) {
                paidAmount = +getTotalByMoneyReceiptByMR(customer, "Passed")
                        - getTotalAdjustmentDr(customer)
                        + getTotalAdjustmentCr(customer)
                        - getTotalWithdraw(customer);
                totalPrice = getFloorPrice(customer);
                if (array.length == 1 && array[0].toUpperCase().equals("ALL")) {
                    summaryMap = new HashMap<String, Comparable>();
                    summaryMap.put("cid", customer.getCID());
                    summaryMap.put("name", customer.getName());
                    summaryMap.put("totalPrice", totalPrice);
                    summaryMap.put("paidAmount", paidAmount);
                    summaryMap.put("status", customer.getStatus());
                    summaryList.add(summaryMap);
                } else if (array[0].startsWith("=")) {
                    if (paidAmount == Integer.parseInt(array[0].substring(1, array[0].length()).trim())) {
                        summaryMap = new HashMap<String, Comparable>();
                        summaryMap.put("cid", customer.getCID());
                        summaryMap.put("name", customer.getName());
                        summaryMap.put("totalPrice", totalPrice);
                        summaryMap.put("paidAmount", paidAmount);
                        summaryMap.put("status", customer.getStatus());
                        summaryList.add(summaryMap);
                    } else {
                        //TODO...
                    }
                } else if (array[0].startsWith(">")) {
                    //TODO...
                } else if (array[0].startsWith("<")) {
                    //TODO...
                } else if (array.length == 1 && (paidAmount <= Integer.parseInt(array[0]))) {
                    summaryMap = new HashMap<String, Comparable>();
                    summaryMap.put("cid", customer.getCID());
                    summaryMap.put("name", customer.getName());
                    summaryMap.put("totalPrice", totalPrice);
                    summaryMap.put("paidAmount", paidAmount);
                    summaryMap.put("status", customer.getStatus());
                    summaryList.add(summaryMap);
                } else if (array.length == 2 && (paidAmount >= Integer.parseInt(array[0]) && paidAmount <= Integer.parseInt(array[1]))) {
                    summaryMap = new HashMap<String, Comparable>();
                    summaryMap.put("cid", customer.getCID());
                    summaryMap.put("name", customer.getName());
                    summaryMap.put("totalPrice", totalPrice);
                    summaryMap.put("paidAmount", paidAmount);
                    summaryMap.put("status", customer.getStatus());
                    summaryList.add(summaryMap);
                } else {
                    //TODO...
                }
            } else {
                //TODO...
            }
        }
        //Sorting List
        Collections.sort(summaryList, new Comparator<Map<String, Comparable>>() {
            @Override
            public int compare(Map<String, Comparable> o1, Map<String, Comparable> o2) {
                return o1.get("cid").compareTo(o2.get("cid"));
            }
        });

        return summaryList;
    }

    @Override
    public Double getTotalDeposit(String cid) {
        Customer customer = getCustomer(cid);
        return +getTotalByMoneyReceiptByMR(customer, "Passed")
                - getTotalAdjustmentDr(customer)
                + getTotalAdjustmentCr(customer)
                - getTotalWithdraw(customer);
    }

    @Override
    public Double getTotalDeposit(Customer customer) {

        return +getTotalByMoneyReceiptByMR(customer, "Passed")
                - getTotalAdjustmentDr(customer)
                + getTotalAdjustmentCr(customer)
                - getTotalWithdraw(customer);
    }

    @Override
    public Double getTotalByMoneyReceiptByMR(Customer customer, String status) {
        Double total = 0.0;
        List<MoneyReceipt> moneyReceipts;
        try {
            moneyReceipts = moneyReceiptDAO.getDepositedAmountByMR(customer, status);
            if (moneyReceipts != null) {
                for (MoneyReceipt moneyReceipt : moneyReceipts) {
                    total += moneyReceipt.getAmount();
                }
            }
            return total;
        } catch (NullPointerException npe) {
            return 0.0;
        }

    }

    @Override
    public Double getTotalAdjustmentCr(Customer customer) {
        Double amount = 0.0;
        List<Voucher> vouchers = customer.getVouchers();
        if (vouchers != null) {
            for (Voucher vr : vouchers) {
                String vType = (vr.getClass().getSimpleName().equals("AdjustmentVoucher")) ? "Adjustment" : "Withdraw";
                if (vType.equals("Adjustment") && vr.getStatus().equals("Approved") && vr.getAmount() >= 0) {
                    amount = amount + vr.getAmount();
                }
            }
        }
        return amount;
    }

    @Override
    public Double getTotalAdjustmentDr(Customer customer) {
        Double amount = 0.0;
        List<Voucher> vouchers = customer.getVouchers();
        if (vouchers != null) {
            for (Voucher vr : vouchers) {
                String vType = (vr.getClass().getSimpleName().equals("AdjustmentVoucher")) ? "Adjustment" : "Withdraw";
                if (vType.equals("Adjustment") && vr.getStatus().equals("Approved") && vr.getAmount() < 0) {
                    amount = amount + vr.getAmount();
                }
            }
        }
        return -amount;
    }

    @Override
    public Double getTotalWithdraw(Customer customer) {
        Double amount = 0.0;
        List<Voucher> vouchers = customer.getVouchers();
        if (vouchers != null) {
            for (Voucher vr : vouchers) {
                String vType = (vr.getClass().getSimpleName().equals("AdjustmentVoucher")) ? "Adjustment" : "Withdraw";
                if (vType.equals("Withdraw") && vr.getStatus().equals("Approved")) {
                    amount = amount + vr.getAmount();
                }
            }
        }
        return -amount;
    }

    public Double getAllDeposit() {
        Double amount = 0.0;
        List<MoneyReceipt> moneyReceipts = findAllMoneyReceipt();
        List<Voucher> vouchers = findAllVoucher();
        if (moneyReceipts != null) {
            for (MoneyReceipt mr : moneyReceipts) {
                amount = amount + mr.getAmount();
            }
        }
        if (vouchers != null) {
            for (Voucher vr : vouchers) {
                amount = amount + vr.getAmount();
            }
        }
        return amount;
    }

    @Override
    public List getReceiptVoucherForStatement() {
        List<MoneyReceipt> moneyReceipts = findAllMoneyReceipt();
        List<Voucher> vouchers = findAllVoucher();

        List<Map<String, Comparable>> list = new ArrayList<Map<String, Comparable>>();
        Map<String, Comparable> map = null;

        for (MoneyReceipt mr : moneyReceipts) {
            map = new HashMap<String, Comparable>();
            map.put("date", mr.getReceiptDate());
            map.put("no", mr.getReceiptNo());
            map.put("mode", mr.getPaymentMethod());
            if (mr.getStatus().toUpperCase().equals("PASSED") || mr.getStatus().toUpperCase().equals("OK")) {
                map.put("dr", "");
                map.put("cr", mr.getAmount());
                map.put("status", "---");
            } else {
                map.put("dr", "");
                map.put("cr", "");
                map.put("status", mr.getStatus());
            }
            list.add(map);
        }

        for (Voucher vr : vouchers) {
            map = new HashMap<String, Comparable>();
            String vType = (vr.getClass().getSimpleName().equals("AdjustmentVoucher")) ? "Adjustment" : "Withdraw";
            map.put("date", vr.getVoucherDate());
            map.put("no", vr.getVoucherNo());
            map.put("mode", vType);

            if (vr.getStatus().toUpperCase().equals("APPROVED")) {
                if (vr.getAmount() < 0) {
                    map.put("dr", -vr.getAmount());
                    map.put("cr", "");
                } else if (vr.getAmount() > 0) {
                    map.put("dr", "");
                    map.put("cr", vr.getAmount());
                } else if (vr.getAmount() == 0) {
                    map.put("dr", "");
                    map.put("cr", "");
                }
                map.put("status", "---");
            } else {
                map.put("dr", "");
                map.put("cr", "");
                map.put("status", vr.getStatus());
            }
            list.add(map);
        }

        Collections.sort(list, new Comparator<Map<String, Comparable>>() {
            @Override
            public int compare(Map<String, Comparable> o1, Map<String, Comparable> o2) {
                return o1.get("date").compareTo(o2.get("date"));
            }
        });

        return list;
    }

    @Override
    public List getAllReceiptAndVouchers(Customer customer) {
        List<MoneyReceipt> moneyReceipts = customer.getMoneyReceipts();
        List<Voucher> vouchers = customer.getVouchers();

        List<Map<String, Comparable>> list = new ArrayList<Map<String, Comparable>>();
        Map<String, Comparable> map;
        //https://www.mkyong.com/java/how-to-sort-a-map-in-java/
        for (MoneyReceipt mr : moneyReceipts) {
            map = new HashMap<String, Comparable>();
            map.put("date", mr.getReceiptDate());
            map.put("cashDate", mr.getCashDate());
            map.put("note", mr.getNote());
            map.put("type", "MR");
            map.put("no", mr.getReceiptNo());
            map.put("mode", mr.getPaymentMethod());
            if (mr.getStatus().toUpperCase().equals("PASSED") || mr.getStatus().toUpperCase().equals("OK")) {
                map.put("dr", "");
                map.put("cr", mr.getAmount());
                map.put("status", mr.getStatus());
            } else {
                map.put("dr", "");
                map.put("cr", "");
                map.put("status", mr.getStatus() + " (" + mr.getAmount() + ")");
            }
            list.add(map);
        }

        for (Voucher vr : vouchers) {
            map = new HashMap<String, Comparable>();
            String vType = (vr.getClass().getSimpleName().equals("AdjustmentVoucher")) ? "Adjustment" : "Withdraw";
            map.put("date", vr.getVoucherDate());
            map.put("cashDate", "---");
            map.put("note", vr.getNote());
            map.put("type", "VR");
            map.put("no", vr.getVoucherNo());
            map.put("mode", vType);

            if (vr.getStatus().toUpperCase().equals("APPROVED")) {
                if (vr.getAmount() < 0) {
                    map.put("dr", -vr.getAmount());
                    map.put("cr", "");
                } else if (vr.getAmount() > 0) {
                    map.put("dr", "");
                    map.put("cr", vr.getAmount());
                } else if (vr.getAmount() == 0) {
                    map.put("dr", "");
                    map.put("cr", "");
                }
                map.put("status", vr.getStatus());
            } else {
                map.put("dr", "");
                map.put("cr", "");
                map.put("status", vr.getStatus() + " (" + vr.getAmount() + ")");
            }
            list.add(map);
        }

        Collections.sort(list, new Comparator<Map<String, Comparable>>() {
            @Override
            public int compare(Map<String, Comparable> o1, Map<String, Comparable> o2) {
                return o1.get("date").compareTo(o2.get("date"));
            }
        });

        return list;
    }

    public List<Customer> getAllCustomerByMidType(String finFor) {
        return findAllCustomer();
    }

    @Override
    public List<Customer> getAllCustomerByName(String name) {
        return customerDAO.getAllCustomerByName(name);
    }

    @Override
    public List<Map<String, Comparable>> getInstallmentInfo(Customer customer) {
        List<Installment> installments = customer.getInstallments();
        List<Map<String, Comparable>> list = new ArrayList<Map<String, Comparable>>();
        Map<String, Comparable> map;
        int counter = 0;
        int monthPerInst = 0;
        int set = 1;
        int instNumber = 0;
        Double amount = 0.0;
        String start = null;
        String end = null;
        for (Installment installment : installments) {
            if (counter == 0) {
                //This part will reinitialize the variables value for first set of Installment
                amount = installment.getAmount();
                start = installment.getInstallmentOf();
                end = installment.getInstallmentOf();
                monthPerInst = installment.getMonthPerInst();
            } else if (amount == installment.getAmount()) {
                end = installment.getInstallmentOf();
            }
            if (amount != installment.getAmount() && counter != 0) {
                //This map will store all set of Installment except last
                map = new HashMap<String, Comparable>();      //Map instantiation
                map.put("set", set);
                map.put("instNumber", instNumber);
                map.put("start", start);
                map.put("end", end);
                map.put("amount", amount);
                map.put("months", monthPerInst);
                list.add(map);

                //This part will reset the initial values for new set of Installment
                counter = 1;
                instNumber = 1;
                set++;
                amount = installment.getAmount();
                start = installment.getInstallmentOf();
                end = installment.getInstallmentOf();
                monthPerInst = installment.getMonthPerInst();
            } else {
                counter++;
                instNumber++;
            }
        }
        //This map will store last set of Installment
        map = new HashMap<String, Comparable>();
        map.put("set", set);
        map.put("instNumber", instNumber);
        map.put("start", start);
        map.put("end", end);
        map.put("amount", amount);
        map.put("months", monthPerInst);

        list.add(map);
        return list;
    }

    @Override
    public List getCollectionReport(String start, String end) {
            return customerDAO.getCollectionReport(start, end);
    }

    //Customer
    //==========================================
    @Override
    public boolean save(Customer customer) {
        return customerDAO.save(customer);
    }

    @Override
    public boolean update(Customer customer) {
        return customerDAO.update(customer);
    }

    @Override
    public boolean delete(Customer customer) {
        return customerDAO.delete(customer);
    }

    @Override
    public List<Customer> findAllCustomer() {
        return customerDAO.findAllCustomer();
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerDAO.getCustomer(id);
    }

    @Override
    public Customer getCustomer(String cid) {
        return customerDAO.getCustomer(cid);
    }

    @Override
    public List<Customer> getCustomerListByStatus(String status) {
        return customerDAO.getCustomerListByStatus(status);
    }

    @Override
    public List<Customer> getCustomerListByBookingDate(String status, Date from, Date to) {
        return customerDAO.getCustomerListByBookingDate(status, from, to);
    }

    @Override
    public List<Customer> getCustomerListByBookingDate(Offer offer, Date from, Date to) {
        return customerDAO.getCustomerListByBookingDate(offer, from, to);
    }
    //Building ..............

    @Override
    public boolean save(Building building) {
        return buildingDAO.save(building);
    }

    @Override
    public boolean update(Building building) {
        return buildingDAO.update(building);
    }

    @Override
    public boolean delete(Building building) {
        return buildingDAO.delete(building);
    }

    @Override
    public List<Building> findAllBuilding() {
        return buildingDAO.findAllBuilding();
    }

    @Override
    public Building getBuilding(Long id) {
        return buildingDAO.getBuilding(id);
    }

    //EmpCsd

    @Override
    public boolean save(EmpCsd empCsd) {
        return empCsdDAO.save(empCsd);
    }

    @Override
    public boolean update(EmpCsd empCsd) {
        return empCsdDAO.update(empCsd);
    }

    @Override
    public boolean delete(EmpCsd empCsd) {
        return empCsdDAO.delete(empCsd);
    }

    @Override
    public List<EmpCsd> findAllEmpCsd() {
        return empCsdDAO.findAllEmpCsd();
    }

    @Override
    public EmpCsd getEmpCsd(Long id) {
        return empCsdDAO.getEmpCsd(id);
    }


    //DisbursementLog

    @Override
    public boolean save(DisbursementLog log) {
        return disbursementLogDAO.save(log);
    }

    @Override
    public boolean update(DisbursementLog log) {
        return disbursementLogDAO.update(log);
    }

    @Override
    public boolean delete(DisbursementLog log) {
        return disbursementLogDAO.delete(log);
    }

    @Override
    public List<DisbursementLog> findAllDisbursementLog() {
        return disbursementLogDAO.findAllDisbursementLog();
    }

    @Override
    public DisbursementLog getDisbursementLog(Long id) {
        return disbursementLogDAO.getDisbursementLog(id);
    }

    @Override
    public List<Object[]> getDisbursementLogReport(Date from, Date to) {
        return disbursementLogDAO.getDisbursementLogReport(from, to);
    }

    //Director ..............
    @Override
    public boolean save(Director director) {
        return directorDAO.save(director);
    }

    @Override
    public boolean update(Director director) {
        return directorDAO.update(director);
    }

    @Override
    public boolean delete(Director director) {
        return directorDAO.delete(director);
    }

    @Override
    public List<Director> findAllDirector() {
        return directorDAO.findAllDirector();
    }

    @Override
    public Director getDirector(Long id) {
        return directorDAO.getDirector(id);
    }

    //Application
    @Override
    public boolean save(Application application) {
        return applicationDAO.save(application);
    }

    @Override
    public boolean update(Application application) {
        return applicationDAO.update(application);
    }

    @Override
    public boolean delete(Application application) {
        return applicationDAO.delete(application);
    }

    @Override
    public List<Application> findAllApplication() {
        return applicationDAO.findAllApplication();
    }

    @Override
    public Application getApplication(Long id) {
        return applicationDAO.getApplication(id);
    }

    
    //CancellationApp
    @Override
    public boolean save(CancellationApp cancellationApp) {
        return cancellationAppDAO.save(cancellationApp);
    }

    @Override
    public boolean update(CancellationApp cancellationApp) {
        return cancellationAppDAO.update(cancellationApp);
    }

    @Override
    public boolean delete(CancellationApp cancellationApp) {
        return cancellationAppDAO.delete(cancellationApp);
    }

    @Override
    public List<CancellationApp> findAllCancellationApp() {
        return cancellationAppDAO.findAllCancellationApp();
    }

    @Override
    public CancellationApp getCancellationApp(Long id) {
        return cancellationAppDAO.getCancellationApp(id);
    }



    //Installment ..............
    @Override
    public boolean save(Installment installment) {
        return installmentDAO.save(installment);
    }

    @Override
    public boolean update(Installment installment) {
        return installmentDAO.update(installment);
    }

    @Override
    public boolean delete(Installment installment) {
        return installmentDAO.delete(installment);
    }

    @Override
    public boolean deleteAllInstallment(Customer customer) {
        customer.getInstallments().removeAll(customer.getInstallments());
        return installmentDAO.deleteAllInstallment(customer);
    }

    @Override
    public List<Installment> findAllInstallment() {
        return installmentDAO.findAllInstallment();
    }

    @Override
    public Installment getInstallment(Long id) {
        return installmentDAO.getInstallment(id);
    }

    //Money Receipt
    @Override
    public boolean save(MoneyReceipt moneyReceipt) {
        return moneyReceiptDAO.save(moneyReceipt);
    }

    @Override
    public boolean update(MoneyReceipt moneyReceipt) {
        return moneyReceiptDAO.update(moneyReceipt);
    }

    @Override
    public boolean delete(MoneyReceipt moneyReceipt) {
        return moneyReceiptDAO.delete(moneyReceipt);
    }

    @Override
    public List<MoneyReceipt> findAllMoneyReceipt() {
        return moneyReceiptDAO.findAllMoneyReceipt();
    }

    @Override
    public List<MoneyReceipt> findAllMoneyReceipt(Integer start, Integer limit) {
        return moneyReceiptDAO.findAllMoneyReceipt(start, limit);
    }

    @Override
    public List<MoneyReceipt> waitingMoneyReceiptForApprove() {
        return moneyReceiptDAO.waitingMoneyReceiptForApprove();
    }


    @Override
    public List<MoneyReceipt> findAllMoneyReceipt(String status) {
        List<MoneyReceipt> moneyReceipts = moneyReceiptDAO.findAllMoneyReceipt();
        List<MoneyReceipt> returnList = null;
        for (MoneyReceipt mr : moneyReceipts) {
            if (mr.getStatus().toUpperCase().equals(status)) {
                returnList.add(mr);
            }
        }
        return returnList;
    }

    @Override
    public MoneyReceipt getMoneyReceipt(Long id) {
        return moneyReceiptDAO.getMoneyReceipt(id);
    }

    @Override
    public MoneyReceipt getMoneyReceipt(String mrNo) {
        return moneyReceiptDAO.getMoneyReceipt(mrNo);
    }

    //Other payments
    @Override
    public boolean save(OtherPayment otherPayment) {
        return otherPaymentDAO.save(otherPayment);
    }

    @Override
    public boolean update(OtherPayment otherPayment) {
        return otherPaymentDAO.update(otherPayment);
    }

    @Override
    public boolean delete(OtherPayment otherPayment) {
        return otherPaymentDAO.delete(otherPayment);
    }

    @Override
    public List<OtherPayment> findAllOtherPayment() {
        return otherPaymentDAO.findAllOtherPayment();
    }

    @Override
    public OtherPayment getOtherPayment(Long id) {
        return otherPaymentDAO.getOtherPayment(id);
    }


    //Project ..............
    @Override
    public boolean save(Project project) {
        return projectDAO.save(project);
    }

    @Override
    public boolean update(Project project) {
        return projectDAO.update(project);
    }

    @Override
    public boolean delete(Project project) {
        return projectDAO.delete(project);
    }

    @Override
    public List<Project> findAllProject() {
        return projectDAO.findAllProject();
    }

    @Override
    public Project getProject(Long id) {
        return projectDAO.getProject(id);
    }

    //Share Holder ..............
    @Override
    public boolean save(Shareholder shareholder) {
        return shareholderDAO.save(shareholder);
    }

    @Override
    public boolean update(Shareholder shareholder) {
        return shareholderDAO.update(shareholder);
    }

    @Override
    public boolean delete(Shareholder shareholder) {
        return shareholderDAO.delete(shareholder);
    }

    @Override
    public List<Shareholder> findAllShareholder() {
        return shareholderDAO.findAllShareholder();
    }

    @Override
    public Shareholder getShareholder(Long id) {
        return shareholderDAO.getShareholder(id);
    }

    //Voucher
    @Override
    public boolean save(Voucher voucher) {
        return voucherDAO.save(voucher);
    }

    @Override
    public boolean update(Voucher voucher) {
        return voucherDAO.update(voucher);
    }

    @Override
    public boolean delete(Voucher voucher) {
        return voucherDAO.delete(voucher);
    }

    @Override
    public List<Voucher> findAllVoucher() {
        return voucherDAO.findAllVoucher();
    }

    @Override
    public List<Voucher> getVoucherList(String status) {
        return voucherDAO.getVoucherList(status);
    }

    @Override
    public Voucher getVoucher(Long id) {
        return voucherDAO.getVoucher(id);
    }

    @Override
    public List<Voucher> findAllVoucher(Boolean b) {
        return voucherDAO.findAllVoucher(b);
    }

    //MID
    @Override
    public boolean save(MID mid) {
        return midDAO.save(mid);
    }

    @Override
    public boolean update(MID mid) {
        return midDAO.update(mid);
    }

    @Override
    public boolean delete(MID mid) {
        return midDAO.delete(mid);
    }

    @Override
    public List<MID> findAllMID() {
        return midDAO.findAllMID();
    }

    @Override
    public List<MID> findAllMID(String type) {
        return midDAO.findAllMID(type);
    }

    @Override
    public MID getMID(Long id) {
        return midDAO.getMID(id);
    }

    @Override
    public MID getMID(String mid) {
        return midDAO.getMID(mid);
    }

    //Offer
    @Override
    public boolean save(Offer offer) {
        return offerDAO.save(offer);
    }

    @Override
    public boolean update(Offer offer) {
        return offerDAO.update(offer);
    }

    @Override
    public boolean delete(Offer offer) {
        return offerDAO.delete(offer);
    }

    @Override
    public List<Offer> findAllOffer() {
        return offerDAO.findAllOffer();
    }

    @Override
    public Offer getOffer(Long id) {
        return offerDAO.getOffer(id);
    }

    //Money Disburse
    @Override
    public boolean save(MoneyDisburse moneyDisburse) {
        return moneyDisburseDAO.save(moneyDisburse);
    }

    @Override
    public boolean update(MoneyDisburse moneyDisburse) {
        return moneyDisburseDAO.update(moneyDisburse);
    }

    @Override
    public boolean delete(MoneyDisburse moneyDisburse) {
        return moneyDisburseDAO.delete(moneyDisburse);
    }

    @Override
    public List<MoneyDisburse> findAllMoneyDisburse() {
        return moneyDisburseDAO.findAllMoneyDisburse();
    }

    @Override
    public List<MoneyDisburse> findAllMoneyDisburses(String findFor) {
        return moneyDisburseDAO.findAllMoneyDisburses(findFor);
    }

    @Override
    public List findAllMoneyDisbursesSummary(String findFor) {
        List<Customer> customers = findAllCustomer();
        List<Map<String, Comparable>> mapList = new ArrayList<Map<String, Comparable>>();
        Map<String, Comparable> map;
        for (Customer customer : customers) {
            map = new HashMap<String, Comparable>();
            map.put("cid", customer.getCID());
            map.put("name", customer.getName());
            map.put("paidTotal", getTotalDeposit(customer));
            map.put("disburseTotal", getTotalDisburse(customer));
            map.put("rest", getTotalDeposit(customer) - getTotalDisburse(customer));
            mapList.add(map);
        }
        Collections.sort(mapList, new Comparator<Map<String, Comparable>>() {
            @Override
            public int compare(Map<String, Comparable> o1, Map<String, Comparable> o2) {
                return o1.get("cid").compareTo(o2.get("cid"));
            }
        });
        return mapList;
    }

    public Double getTotalDisburse(Customer customer) {
        MoneyDisburse moneyDisburse = customer.getMoneyDisburse();
        return moneyDisburse.getCarPark() + moneyDisburse.getDownPayment() + moneyDisburse.getInstallment() +
                moneyDisburse.getOtherPayment() + moneyDisburse.getSpecialPayment();
    }

    @Override
    public MoneyDisburse getMoneyDisburse(Long id) {
        return moneyDisburseDAO.getMoneyDisburse(id);
    }

    //Follow Up History
    @Override
    public boolean setInactiveHistory(Customer customer) {
        return this.historyDAO.setInactiveHistory(customer);
    }
    @Override
    public boolean save(FollowUpHistory followUpHistory) {
        return historyDAO.save(followUpHistory);
    }

    @Override
    public boolean update(FollowUpHistory followUpHistory) {
        return historyDAO.update(followUpHistory);
    }

    @Override
    public boolean delete(FollowUpHistory followUpHistory) {
        return historyDAO.delete(followUpHistory);
    }

    @Override
    public List<FollowUpHistory> findAllFollowUpHistory() {
        return historyDAO.findAllFollowUpHistory();
    }

    @Override
    public List<FollowUpHistory> findAllFollowUpHistory(Customer customer) {
        List<FollowUpHistory> historyList = historyDAO.findAllFollowUpHistory(customer);
        Comparator<FollowUpHistory> comparator = new Comparator<FollowUpHistory>() {
            public int compare(FollowUpHistory c1, FollowUpHistory c2) {
                return Integer.parseInt(c2.getId().toString()) - Integer.parseInt(c1.getId().toString());
            }
        };

        Collections.sort(historyList, comparator);
        return historyList;
    }

    @Override
    public List<FollowUpHistory> findAllFollowUpHistory(Date date, String of) {
        List<FollowUpHistory> list = historyDAO.findAllFollowUpHistory(date, of);
        Collections.sort(list, new Comparator<FollowUpHistory>() {
            public int compare(FollowUpHistory o1, FollowUpHistory o2) {
                Date date1 = o1.getAttentionOn();
                Date date2 = o2.getAttentionOn();
                if (date1 == date2)
                    return 0;
                return date1.compareTo(date2);
            }
        });
        return list;
    }

    @Override
    public FollowUpHistory getFollowUpHistory(Long id) {
        return historyDAO.getFollowUpHistory(id);
    }


    //IncentiveDist
    @Override
    public boolean save(IncentiveDist dist) {
        return incentiveDistDAO.save(dist);
    }

    @Override
    public boolean update(IncentiveDist dist) {
        return incentiveDistDAO.update(dist);
    }

    @Override
    public boolean delete(IncentiveDist dist) {
        return incentiveDistDAO.delete(dist);
    }

    @Override
    public List<IncentiveDist> findAllIncentiveDist() {
        return incentiveDistDAO.findAllIncentiveDist();
    }

    @Override
    public IncentiveDist getIncentiveDist(Long id) {
        return incentiveDistDAO.getIncentiveDist(id);
    }


    @Override
    public List<IncentiveDist> findAllIncentiveDist(Customer customer) {
        return customer.getDistList();
    }

    @Override
    public List<IncentiveDist> findAllIncentiveDist(Integer status) {
        return incentiveDistDAO.findAllIncentiveDist(status);
    }

    @Override
    public String findAllIncentiveDistString(Customer customer) {
        String rtString = "";
        List<IncentiveDist> distList = customer.getDistList();
        if (distList.size() == 0) rtString = "No Data Found !";
        for (IncentiveDist dl : distList) {
            rtString += dl.getSalesMan().getName() + " : " + dl.getSalesManAmount() + " (Self) & " + dl.getPersonnelAmount() + " (Staff)";
        }
        return rtString;
    }

    @Override
    public Double getDistributedIncentive(MID mid) {
        return 0.0;
    }

    @Override
    public Double getDistributedIncentive(Customer customer) {
        Double rtAmount = 0.0;
        List<IncentiveDist> distList = customer.getDistList();
        for (IncentiveDist dl : distList) {
            rtAmount += dl.getSalesManAmount();
            rtAmount += dl.getPersonnelAmount();
        }
        return rtAmount;
    }


    static List<InboundMessage> msgList = new ArrayList<InboundMessage>();

    public static void startService() {
        try {
            SerialModemGateway gateway = new SerialModemGateway("id", MyProperty.PORT, MyProperty.BAUD_RATE, MyProperty.MANUFACTURER, MyProperty.MODEL);
            gateway.setInbound(true);
            gateway.setOutbound(true);
            gateway.setProtocol(AGateway.Protocols.PDU);
            gateway.getATHandler().setStorageLocations("SM");
            org.smslib.Service.getInstance().addGateway(gateway);
            org.smslib.Service.getInstance().startService();

            while (true) {
                org.smslib.Service.getInstance().readMessages(msgList, InboundMessage.MessageClasses.ALL);
                System.out.println("msgList = " + msgList.size());
                //myDriver();
                int msgCounter = 0;
                for (InboundMessage msg : msgList) {

                    msgCounter++;
                    String sender = msg.getOriginator();
                    System.out.println("sender = " + sender);
                    String msgText = msg.getText();
                    System.out.println(msgCounter + " text_msg = " + msgText);
                    msgText = msgText.trim().replaceAll(" +", " ");
                    //logger.info("\nFrom: " + sender);
                    //logger.info("Text: " + text_msg);
                    String[] wordsList = msgText.split(" ");

                    String key = wordsList[0];
                    OutboundMessage msgOut = new OutboundMessage(sender.substring(2,13), "This is ans of ur SMS,\n Fron DORPAN System \""+msgText+"\"\n -"+MyProperty.COMPANY_NAME);
                    org.smslib.Service.getInstance().sendMessage(msgOut);
                    org.smslib.Service.getInstance().deleteMessage(msg);
                }
                msgList.clear();
                Thread.sleep(1500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
