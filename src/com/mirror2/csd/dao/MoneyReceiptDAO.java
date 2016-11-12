package com.mirror2.csd.dao;

import com.mirror2.csd.model.Building;
import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.MoneyReceipt;
import com.mirror2.security.model.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MoneyReceiptDAO {
    public boolean save(MoneyReceipt moneyReceipt);

    public boolean update(MoneyReceipt moneyReceipt);

    public boolean delete(MoneyReceipt moneyReceipt);

    public List<MoneyReceipt> findAllMoneyReceipt();

    public List<MoneyReceipt> findAllMoneyReceipt(Integer start, Integer limit);

    public List<MoneyReceipt> findAllMoneyReceipt(Customer customer, Date from, Date to);

    public List<Object[]> findAllMaxDatedMoneyReceipt(String type, Date from);

    public List<Object[]> getCollectionDataModeWise(Date from, Date to);

    public List<MoneyReceipt> waitingMoneyReceiptForApprove();

    public MoneyReceipt getMoneyReceipt(Long id);

    public MoneyReceipt getMoneyReceipt(String mrNo);

    public List<MoneyReceipt> getDepositedAmountByMR(Customer customer, String status);


}
