package edu.ncst.mvcweb.controller;

import edu.ncst.mvcweb.entity.Course;
import edu.ncst.mvcweb.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/list")
    public List<Course> list() {
        return courseService.findAll();
    }
    @PostMapping("deleteByList")
    public Map<String,Object> deleteByList(@RequestParam("idList[]") List<Integer> idList){
        courseService.delete(idList);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status","success");
        return map;
    }

    @GetMapping("queryUserById")
    public Course queryUserById(Integer id){
        Course course = courseService.queryById(id);
        return course;
    }

    @PostMapping("save")
    public Map<String,Object> save(Course course){
        courseService.save(course);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status", "success");
        return map;
    }
}
