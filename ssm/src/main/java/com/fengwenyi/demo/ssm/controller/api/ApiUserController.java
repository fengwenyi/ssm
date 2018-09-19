package com.fengwenyi.demo.ssm.controller.api;

import com.fengwenyi.demo.ssm.domain.User;
import com.fengwenyi.demo.ssm.service.IUserService;
import com.fengwenyi.demo.ssm.config.ReturnCode;
import com.fengwenyi.javalib.result.Result;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "API接口Demo")
@RestController
@RequestMapping(value = "/api/user", produces = {"application/json; charset=utf-8"})
public class ApiUserController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "获取所有用户", notes = "获取所有用户及详细信息")
    @GetMapping("/all")
    public String getUsers () {
        Result result = new Result();
        List<User> users = userService.getUsers();
        result.setData(ReturnCode.SUCCESS, users);
        return new Gson().toJson(result);
    }

    @ApiOperation(value = "获取用户", notes = "通过Id获取用户详细信息")
    @GetMapping("/{id}")
    public String getUserById (@ApiParam(name = "id", value = "Id", required = true) @PathVariable("id") Integer id) {
        Result result = new Result();
        if (id != null && id > 0) {
            User user = userService.getUserById(id);
            result.setData(ReturnCode.SUCCESS, user);
        } else {
            result.setResult(ReturnCode.ERROR);
        }
        return new Gson().toJson(result);
    }

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @PostMapping("/add")
    public String add (@ApiParam(name = "name", value = "姓名", required = true) @RequestParam("name") String name,
                       @ApiParam(name = "age", value = "年龄", required = true) @RequestParam("age") Integer age) {
        Result result = new Result();
        if (name != null
                && !"".equals(name)
                && age != null
                && age > 0) {
            User user = new User();
            user.setName(name);
            user.setAge(age);
            Integer rsNum = userService.insert(user);
            log.info("add=>" + rsNum);
            if (rsNum > 0) {
                result.setData(ReturnCode.SUCCESS, user);
            } else {
                log.error("add fail, data : " + user.toString());
                result.setResult(ReturnCode.ERROR);
            }
        } else {
            result.setResult(ReturnCode.ERROR);
        }
        return new Gson().toJson(result);
    }

    @ApiOperation(value = "修改用户信息", notes = "通过Id获取用户详细信息")
    @PutMapping("/update/{id}")
    public String update (@ApiParam(name = "id", value = "Id", required = true)
                                @PathVariable("id") Integer id,
                          @ApiParam(name = "name", value = "姓名", required = false)
                                @RequestParam(value = "name", required = false) String name,
                          @ApiParam(name = "age", value = "年龄", required = false)
                                @RequestParam(value = "age", required = false) Integer age) {
        Result result = new Result();
        if (id != null
                && id > 0) {
            User user = new User();
            user.setId(id);
            if (name != null && !"".equals(name)) user.setName(name);
            if (age != null && age > 0) user.setAge(age);
            Integer rsNum = userService.update(user);
            log.info("update=>" + rsNum);
            if (rsNum > 0) {
                result.setData(ReturnCode.SUCCESS, user);
            } else {
                log.error("update fail, data : " + user.toString());
                result.setData(ReturnCode.ERROR, user);
            }
        } else {
            result.setResult(ReturnCode.ERROR);
        }
        return new Gson().toJson(result);
    }



    @ApiOperation(value = "删除用户", notes = "通过Id删除用户")
    @DeleteMapping("/delete/{id}")
    public String delete (@ApiParam(name = "id", value = "Id", required = true) @PathVariable("id") Integer id) {
        Result result = new Result();
        if (id != null && id > 0) {
            Integer rsNum = userService.delete(id);
            log.info("delete=>" + rsNum);
            if (rsNum > 0) {
                result.setResult(ReturnCode.SUCCESS);
            } else {
                log.error("delete fail, id : " + id);
                result.setResult(ReturnCode.ERROR);
            }
        } else {
            result.setResult(ReturnCode.ERROR);
        }
        return new Gson().toJson(result);
    }

    @ApiIgnore
    @RequestMapping("/test")
    public String test () {
        return "test";
    }

}
