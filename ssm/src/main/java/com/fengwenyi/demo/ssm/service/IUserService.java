package com.fengwenyi.demo.ssm.service;

import com.fengwenyi.demo.ssm.domain.User;

import java.util.List;

public interface IUserService {

    List<User> getUsers();

    User getUserById(Integer id);

    Integer insert (User user);

    Integer update (User user);

    Integer delete (Integer id);

}
