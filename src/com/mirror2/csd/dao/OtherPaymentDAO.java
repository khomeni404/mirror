package com.mirror2.csd.dao;

import com.mirror2.csd.model.OtherPayment;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface OtherPaymentDAO {
    public boolean save(OtherPayment otherPayment);

    public boolean update(OtherPayment otherPayment);

    public boolean delete(OtherPayment otherPayment);

    public List<OtherPayment> findAllOtherPayment();

    public List<OtherPayment> findAllOtherPayment(String paymentType);

    public OtherPayment getOtherPayment(Long id);
}
