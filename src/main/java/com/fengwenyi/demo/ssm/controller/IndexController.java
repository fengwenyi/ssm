package com.fengwenyi.demo.ssm.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class IndexController {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String index (HttpServletRequest request, HttpServletResponse response) {
        /*try {
            // 网页地址不变
            request.getRequestDispatcher("/swagger-ui.html").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }*/
        // 网页地址改变（实际访问）
        return "redirect:swagger-ui.html";
    }

}
