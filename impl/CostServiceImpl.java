package edu.ncst.mvcweb.service.impl;

import edu.ncst.mvcweb.dao.CostDao;
import edu.ncst.mvcweb.entity.Cost;
import edu.ncst.mvcweb.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CostServiceImpl implements CostService {

    @Autowired
    private CostDao costDao;

    @Override
    public Cost findByUsernameAndPassword(String username, String password) {
        return costDao.findByUsernameAndPassword(username,password);
    }
    @Override
    public List<Cost> findAll() {
        return costDao.findAll();
    }
    @Override
    public void delete(Integer id) { costDao.deleteById(id);}

    @Override
    public void delete(List<Integer> idList) {
        for (Integer id : idList)
            costDao.deleteById(id);
    }

    @Override
    public Cost queryById(Integer id) { return costDao.findById(id).orElse(null); }

    @Override
    public Cost save(Cost cost) { return costDao.save(cost); }
}
