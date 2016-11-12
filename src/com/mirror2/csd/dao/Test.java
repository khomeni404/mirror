package com.mirror2.csd.dao;

import com.mirror2.csd.service.CsdService;
import com.mirror2.csd.service.CsdServiceImpl;
import com.mirror2.csd.test.PrivateObject;

import java.lang.reflect.Field;

public class Test {
    public static String getName(String cid) {
        CustomerDAO customerDAO = new CustomerDaoImpl();
        CsdService csdService = new CsdServiceImpl();
        //String name = customerDAO.getCustomer(cid).getName();
        String name = csdService.getCustomer(Long.parseLong("17")).getName();
        //......................................................
        return name;
    }

    public static void main(String[] args) {
        Integer i = 6;
        if(i>5)
            System.out.println("Boro");
        else
            System.out.println("Soto");
    }
}
