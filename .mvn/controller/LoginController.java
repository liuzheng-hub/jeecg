package edu.ncst.mvcweb.controller;

import edu.ncst.mvcweb.entity.User;
import edu.ncst.mvcweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(String userName, String password, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password))
            result.put("status", "failure");
        else {
            User user = userService.findByUsernameAndPassword(userName, password);
            if (user != null) {
                result.put("status", "success");
                session.setAttribute("loginUser", user);
            }
            else
                result.put("status", "failrue");
        }
        return result;
    }


}
