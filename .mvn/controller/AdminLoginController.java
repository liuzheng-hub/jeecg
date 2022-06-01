package edu.ncst.mvcweb.controller;

import edu.ncst.mvcweb.entity.Administrator;
import edu.ncst.mvcweb.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminLoginController {
    @Autowired
    private AdministratorService administratorService;
    @PostMapping("/admin_login")
    public Map<String, Object> login(String userName, String password, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password))
            result.put("status", "failure");
        else {
            Administrator administrator = administratorService.findByUsernameAndPassword(userName, password);
            if (administrator != null) {
                result.put("status", "success");
                session.setAttribute("loginUser", administrator);
            }
            else
                result.put("status", "failrue");
        }
        return result;
    }
}
