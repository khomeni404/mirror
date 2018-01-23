package com.mirror2.common.service;


import com.mirror2.admin.service.AdminService;
import com.mirror2.common.dao.CommonDAO;
import com.mirror2.csd.service.CsdService;
import com.mirror2.mis.service.MISService;
import com.mirror2.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Khomeni
 * Created on : 17-May-17 at 12:54 AM
 */

@Component
public class GenericController {

    @Autowired
    public CommonService commonService;

    @Autowired
    public AdminService adminService;

    @Autowired
    public MISService misService;

    @Autowired
    public CsdService csdService;

    @Autowired
    public ReportService reportService;

    @Autowired
    public CommonDAO commonDAO;




   /* public static void addError(Object modelAttribute, BindingResult binding, RedirectAttributes redirectAttributes) {
        String modelClassName = modelAttribute.getClass().getSimpleName();
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + modelClassName, binding);
        redirectAttributes.addFlashAttribute(modelClassName, modelAttribute);
    }

    public static void addError(Object modelAttribute, String modelClassName, BindingResult binding, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + modelClassName, binding);
        redirectAttributes.addFlashAttribute(modelClassName, modelAttribute);
    }*/


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    public static Map<String, Object> getModelMap(String pageTitle) {
        return getModelMap(pageTitle, null, true);
    }

    public static Map<String, Object> getModelMap(String pageTitle, String message) {
        return getModelMap(pageTitle, message, true);
    }
    
    public static Map<String, Object> getModelMap(String pageTitle, String message, boolean success) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", pageTitle == null ? "---" : pageTitle);

        map.put("message", message);
        map.put("success", success);
        return map;
    }

    static ModelAndView redirect(Object controller, String methodName) {
        return redirect(controller, methodName, new HashMap<String, Object>(0));
    }

    static ModelAndView redirect(Object controller, String methodName, Map<String, Object> map) {
        String requestMapping = controller.getClass().getAnnotation(RequestMapping.class).value()[0];
        return new ModelAndView("redirect:" + requestMapping + methodName + ".se", map == null ? null : map.size() == 0 ? null : map);
    }


}
