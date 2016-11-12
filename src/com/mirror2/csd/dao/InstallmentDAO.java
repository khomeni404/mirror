package com.mirror2.csd.dao;

import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.Installment;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface InstallmentDAO {
    public boolean save(Installment installment);

    public boolean update(Installment installment);

    public boolean delete(Installment installment);

    public boolean deleteAllInstallment(Customer customer);

    public List<Installment> findAllInstallment();

    public List<Installment> findAllInstallment(Customer customer);

    public Installment getInstallment(Long id);

}
