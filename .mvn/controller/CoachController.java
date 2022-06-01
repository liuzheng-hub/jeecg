package edu.ncst.mvcweb.controller;


import edu.ncst.mvcweb.entity.Coach;
import edu.ncst.mvcweb.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/coach")
public class CoachController {
    @Autowired
    private CoachService coachService;

    @GetMapping(value = "/list")
    public List<Coach> list() {
        return coachService.findAll();
    }
    @PostMapping("deleteByList")
    public Map<String,Object> deleteByList(@RequestParam("idList[]") List<Integer> idList){
        coachService.delete(idList);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status","success");
        return map;
    }

    @GetMapping("queryUserById")
    public Coach queryUserById(Integer id){
        Coach coach = coachService.queryById(id);
        return coach;
    }

    @PostMapping("save")
    public Map<String,Object> save(Coach coach){
        coachService.save(coach);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status", "success");
        return map;
    }
}
