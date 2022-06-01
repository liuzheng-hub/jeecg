package edu.ncst.mvcweb.controller;

import edu.ncst.mvcweb.entity.Car;
import edu.ncst.mvcweb.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping(value = "/getOne")
    public Car getUser(String username,String password) {
        return carService.findByUsernameAndPassword(username,password);
    }
    @GetMapping(value = "/list")
    public List<Car> list() {
        return carService.findAll();
    }
    @PostMapping("deleteByList")
    public Map<String,Object> deleteByList(@RequestParam("idList[]") List<Integer> idList){
        carService.delete(idList);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status","success");
        return map;
    }

    @GetMapping("queryUserById")
    public Car queryUserById(Integer id){
        Car car = carService.queryById(id);
        return car;
    }

    @PostMapping("save")
    public Map<String,Object> save(Car car){
        carService.save(car);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status", "success");
        return map;
    }
}
