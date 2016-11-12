package com.mirror2.csd.service;

import com.mirror2.csd.bean.CustomerSearchBean;
import com.mirror2.csd.dao.CustomerDAO;
import com.mirror2.csd.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 08/02/16
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 08/02/16
 * Version : 1.0
 */

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private CustomerDAO customerDAO;
    public List<Customer> searchCustomer(CustomerSearchBean searchBean) {
        return customerDAO.searchCustomer(searchBean);
    }
}
