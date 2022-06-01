package edu.ncst.mvcweb.service.impl;

import edu.ncst.mvcweb.dao.CourseDao;
import edu.ncst.mvcweb.entity.Course;
import edu.ncst.mvcweb.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }
    @Override
    public void delete(Integer id) { courseDao.deleteById(id);}

    @Override
    public void delete(List<Integer> idList) {
        for (Integer id : idList)
            courseDao.deleteById(id);
    }

    @Override
    public Course queryById(Integer id) { return courseDao.findById(id).orElse(null); }

    @Override
    public Course save(Course course) { return courseDao.save(course); }
}
