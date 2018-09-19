package com.fengwenyi.demo.ssm.dao;

import com.fengwenyi.demo.ssm.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IUserDao {

    List<User> getUsers();

    User getUserById(@Param("id") Integer id);

    /*List<User> getUserByPage(Integer pageCurr, Integer pageSize);*/

    Integer insert (@Param("user") User user);

    Integer update (@Param("user") User user);

    Integer delete (@Param("id") Integer id);

}
