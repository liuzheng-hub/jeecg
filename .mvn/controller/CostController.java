package edu.ncst.mvcweb.controller;

import edu.ncst.mvcweb.entity.Cost;
import edu.ncst.mvcweb.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cost")
public class CostController {

    @Autowired
    private CostService costService;

    @GetMapping(value = "/getOne")
    public Cost getUser(String username, String password) {
        return costService.findByUsernameAndPassword(username,password);
    }
    @GetMapping(value = "/list")
    public List<Cost> list() {
        return costService.findAll();
    }
    @PostMapping("deleteByList")
    public Map<String,Object> deleteByList(@RequestParam("idList[]") List<Integer> idList){
        costService.delete(idList);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status","success");
        return map;
    }

    @GetMapping("queryUserById")
    public Cost queryUserById(Integer id){
        Cost cost = costService.queryById(id);
        return cost;
    }

    @PostMapping("save")
    public Map<String,Object> save(Cost cost){
        costService.save(cost);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status", "success");
        return map;
    }
}
