package edu.ncst.mvcweb.controller;

import edu.ncst.mvcweb.entity.Administrator;
import edu.ncst.mvcweb.service.AdministratorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @GetMapping(value = "/getOne")
    public Administrator getUser(String username, String password) {
        return administratorService.findByUsernameAndPassword(username,password);
    }
}
