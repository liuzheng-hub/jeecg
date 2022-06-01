package edu.ncst.mvcweb.service.impl;

import edu.ncst.mvcweb.dao.CarDao;
import edu.ncst.mvcweb.entity.Car;
import edu.ncst.mvcweb.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Override
    public Car findByUsernameAndPassword(String username, String password) {
        return carDao.findByUsernameAndPassword(username,password);
    }
    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }
    @Override
    public void delete(Integer id) { carDao.deleteById(id);}

    @Override
    public void delete(List<Integer> idList) {
        for (Integer id : idList)
            carDao.deleteById(id);
    }

    @Override
    public Car queryById(Integer id) { return carDao.findById(id).orElse(null); }

    @Override
    public Car save(Car car) { return carDao.save(car); }
}
