package edu.ncst.mvcweb.service;

import edu.ncst.mvcweb.entity.Administrator;

import java.util.List;

public interface AdministratorService {
    Administrator findByUsernameAndPassword(String username, String password);
}
