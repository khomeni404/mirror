package com.mirror2.ajax;

import com.google.gson.Gson;
import com.mirror2.ajax.serviece.AjaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 21/02/16
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 21/02/16
 * Version : 1.0
 */

@Controller
@RequestMapping("/ajax/")
public class AjaxController {

    public
    @ResponseBody
    String getCustomerData(@RequestParam Long id) {

        return new Gson().toJson("Hello");
    }

    @Autowired
    private AjaxService ajaxService;

    @RequestMapping(method = RequestMethod.GET, value = "getClientJsonData.erp")
    public
    @ResponseBody
    String getClientJsonData(@RequestParam String cid) {
        if (cid.toUpperCase().startsWith("DPL")) {
            cid = cid.substring(3, cid.length());
        }
        DecimalFormat df = new DecimalFormat("000000");
        cid = "DPL " + df.format(Integer.parseInt(cid.trim()));

        return ajaxService.getClientJsonData(cid);
    }
}
