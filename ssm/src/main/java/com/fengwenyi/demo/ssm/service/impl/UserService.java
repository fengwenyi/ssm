package com.fengwenyi.demo.ssm.service.impl;

import com.fengwenyi.demo.ssm.dao.IUserDao;
import com.fengwenyi.demo.ssm.domain.User;
import com.fengwenyi.demo.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    public List<User> getUsers () {
        List<User> users = userDao.getUsers();
        if (users != null)
            return users;
        return null;
    }

    @Override
    public User getUserById(Integer id) {
        if (id != null && id > 0) return userDao.getUserById(id);
        return null;
    }

    @Override
    public Integer insert(User user) {
        if (user != null
                && user.getName() != null
                && !"".equals(user.getName())
                && user.getAge() != null
                && user.getAge() > 0) return userDao.insert(user);
        return -1;
    }

    @Override
    public Integer update(User user) {
        if (user != null
                && user.getId() != null
                && user.getId() > 0) return userDao.update(user);
        return -1;
    }

    @Override
    public Integer delete(Integer id) {
        if (id != null && id > 0) return userDao.delete(id);
        return -1;
    }


}
