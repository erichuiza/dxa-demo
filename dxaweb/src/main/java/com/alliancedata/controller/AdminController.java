package com.alliancedata.controller;

import lombok.extern.slf4j.Slf4j;
import com.alliancedata.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Admin controller that provides access for administrator.
 */
@Slf4j
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * Refreshes the current localization and redirects to the given path.
     *
     * @return the redirect command for Spring MVC
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/admin/refresh", "/*/admin/refresh"})
    public String handleRefresh() {
        return "redirect:" + adminService.refreshLocalization();
    }
}
