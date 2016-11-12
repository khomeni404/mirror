package com.mirror2.messaging;

import com.mirror2.util.MirrorConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 02/09/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 02/09/15
 * Version : 1.0
 */

@Controller
@RequestMapping("/message/")
public class MessageController {

    @RequestMapping(value = "createGroupMessage.erp", method = RequestMethod.GET)
    public ModelAndView createGroupMessage(@RequestParam String to, @RequestParam String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Insert Other Payment");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("messaging/group_message_create", map);
    }

}
