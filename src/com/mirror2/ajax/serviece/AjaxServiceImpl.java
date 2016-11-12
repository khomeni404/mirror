package com.mirror2.ajax.serviece;

import com.google.gson.Gson;
import com.mirror2.csd.model.Customer;
import com.mirror2.csd.service.CsdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 21/02/16
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 21/02/16
 * Version : 1.0
 */

@Service
public class AjaxServiceImpl implements AjaxService {
    @Autowired
    private CsdService csdService;

    @Override
    public String getClientJsonData(String cid) {
        Customer customer = csdService.getCustomer(cid);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("CID", customer.getCID());
        map.put("NAME", customer.getName());
        map.put("CELL", customer.getCellPhone());
        map.put("ADDRESS", customer.getMailingAddress());
        return new Gson().toJson(map);
    }
}
