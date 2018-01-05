package com.mirror2.ajax;

import com.google.gson.Gson;
import com.mirror2.ajax.serviece.AjaxService;
import com.mirror2.common.model.Country;
import com.mirror2.common.model.District;
import com.mirror2.common.model.Division;
import com.mirror2.common.model.PoliceStation;
import com.mirror2.common.service.CommonService;
import com.mirror2.common.service.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class AjaxController extends GenericController{



    private static String SELECT_OPTION = "<option value=\"-1\">--Select--</option>";

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


    @RequestMapping(method = RequestMethod.GET, value = "getDivisionListSelector.se")
    public
    @ResponseBody
    String getDivisionListSelector(@RequestParam Long id) {
        Country country = commonService.get(Country.class, id);
        List<Division> divisionList = country.getDivisionList();
        String s = SELECT_OPTION;
        for (Division d : divisionList) {
            s += "<option value=\"" + d.getId() + "\">" + d.getName() + "</option>";
        }
        return s;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getDistrictListSelector.se")
    public
    @ResponseBody
    String getDistrictListSelector(@RequestParam Long id) {
        Division division = commonService.get(Division.class, id);
        List<District> districtList = division.getDistrictList();
        String s = SELECT_OPTION;
        for (District d : districtList) {
            s += "<option value=\"" + d.getId() + "\">" + d.getName() + "</option>";
        }
        return s;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPoliceStationListSelector.se")
    public
    @ResponseBody
    String getPoliceStationListSelector(@RequestParam Long id) {
        District district = commonService.get(District.class, id);
        List<PoliceStation> policeStationList = district.getPoliceStationList();
        String s = SELECT_OPTION;
        for (PoliceStation d : policeStationList) {
            s += "<option value=\"" + d.getId() + "\">" + d.getName() + "</option>";
        }
        return s;
    }

}
