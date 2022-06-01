package edu.ncst.mvcweb.service.impl;


import edu.ncst.mvcweb.dao.AdministratorDao;
import edu.ncst.mvcweb.entity.Administrator;
import edu.ncst.mvcweb.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorDao administratorDao;
    @Override
    public Administrator findByUsernameAndPassword(String username, String password) {
        return administratorDao.findByUsernameAndPassword(username,password);
    }
}
