package com.mirror2.csd.dao;

import com.mirror2.csd.model.Building;
import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.MoneyDisburse;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MoneyDisburseDAO {
    public boolean save(MoneyDisburse moneyDisburse);

    public boolean update(MoneyDisburse moneyDisburse);

    public boolean delete(MoneyDisburse moneyDisburse);

    public List<MoneyDisburse> findAllMoneyDisburse();

    public List<MoneyDisburse> findAllMoneyDisburses(String findFor);

    public List<MoneyDisburse> findAllMoneyDisbursesSummary(String findFor);

    public MoneyDisburse getMoneyDisburse(Long id);
}
