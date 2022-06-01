package edu.ncst.mvcweb.service.impl;


import edu.ncst.mvcweb.dao.UserDao;
import edu.ncst.mvcweb.entity.User;
import edu.ncst.mvcweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional   //事务
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /*@Override
    public User findByUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }*/

    @Override
        public User findByUsernameAndPassword(String username, String password) {
            return userDao.findByUsernameAndPassword(username,password);
        }
        @Override
        public List<User> findAll() {
            return userDao.findAll();
        }
    @Override
    public void delete(Integer id) { userDao.deleteById(id);}

    @Override
    public void delete(List<Integer> idList) {
        for (Integer id : idList)
            userDao.deleteById(id);
    }

    @Override
    public User queryById(Integer id) { return userDao.findById(id).orElse(null); }

    @Override
    public User save(User user) { return userDao.save(user); }
}