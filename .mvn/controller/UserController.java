package edu.ncst.mvcweb.controller;

import edu.ncst.mvcweb.entity.User;
import edu.ncst.mvcweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")//用其他注解代替restcontroller和getmapping
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/getOne")
    public User getUser(String username,String password) {
        return userService.findByUsernameAndPassword(username,password);
    }
    @GetMapping(value = "/list")
    public List<User> list() {
        return userService.findAll();
    }
    @PostMapping("deleteByList")
    public Map<String,Object> deleteByList(@RequestParam("idList[]") List<Integer> idList){
        userService.delete(idList);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status","success");
        return map;
    }

    @GetMapping("queryUserById")
    public User queryUserById(Integer id){
        User user = userService.queryById(id);
        return user;
    }

    @PostMapping("save")
    public Map<String,Object> save(User user){
        userService.save(user);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status", "success");
        return map;
    }
}
