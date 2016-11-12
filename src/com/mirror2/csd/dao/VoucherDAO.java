package com.mirror2.csd.dao;

import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.Voucher;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */


public interface VoucherDAO {
    public boolean save(Voucher voucher);

    public boolean update(Voucher voucher);

    public boolean delete(Voucher voucher);

    public List<Voucher> findAllVoucher();

    public Voucher getVoucher(Long id);

    public List<Voucher> getVoucherList(String status);

    public List<Voucher> findAllVoucher(Boolean b);
}
