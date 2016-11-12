package com.mirror2.csd.service;

import com.mirror2.csd.bean.CustomerSearchBean;
import com.mirror2.csd.model.Customer;

import java.util.List;

public interface SearchService {
    public List<Customer> searchCustomer(CustomerSearchBean searchBean);

}
