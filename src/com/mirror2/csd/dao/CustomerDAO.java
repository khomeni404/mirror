package com.mirror2.csd.dao;

import com.mirror2.csd.bean.CustomerSearchBean;
import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.MID;
import com.mirror2.csd.model.Offer;

import java.util.Date;
import java.util.List;

public interface CustomerDAO {
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

    public List<Customer> getAllCustomerByName(String name);

    public Integer getTotalCustomerByOffer(Offer offer);

    public Integer getTotalCustomerByMID(MID mid);

    public List getCollectionReport(String from, String to);

    public List<Customer> searchCustomer(CustomerSearchBean searchBean);

    public List<Object[]> getSalesDataSalesPersonWise(String type, Date from, Date to);

    public List<Object[]> getSalesDataDetailsSalesPersonWise(String type, Date from, Date to);
}
