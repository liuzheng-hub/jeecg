package edu.ncst.mvcweb.service.impl;

import edu.ncst.mvcweb.dao.CoachDao;
import edu.ncst.mvcweb.entity.Coach;
import edu.ncst.mvcweb.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CoachServiceImpl implements CoachService {
    @Autowired
    private CoachDao coachDao;

    @Override
    public List<Coach> findAll() {
        return coachDao.findAll();
    }
    @Override
    public void delete(Integer id) { coachDao.deleteById(id);}

    @Override
    public void delete(List<Integer> idList) {
        for (Integer id : idList)
            coachDao.deleteById(id);
    }

    @Override
    public Coach queryById(Integer id) { return coachDao.findById(id).orElse(null); }

    @Override
    public Coach save(Coach coach) { return coachDao.save(coach); }
}
