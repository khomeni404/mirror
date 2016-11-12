package com.mirror2.csd;

import com.mirror2.csd.bean.CustomerSearchBean;
import com.mirror2.csd.service.SearchService;
import com.mirror2.util.MirrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 08/02/16
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 08/02/16
 * Version : 1.0
 */
@Controller
@RequestMapping("/search/")
public class SearchController {
    @Autowired
    private SearchService searchService;

    //createMoneyReceipt
    @RequestMapping(method = RequestMethod.GET, value = "/searchCustomer.erp")
    public ModelAndView createMoneyReceipt(@RequestParam String referenceName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Insert MR");
        map.put("msg", "");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        CustomerSearchBean searchBean = new CustomerSearchBean();
        searchBean.setReferenceName("%"+referenceName+"%");


        map.put("customerList", searchService.searchCustomer(searchBean));
        map.put("status", "All");

        return new ModelAndView("csd/report/customer_find", map);
    }
}
