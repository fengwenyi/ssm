package com.fengwenyi.configurer;

import com.fengwenyi.service.HelloWorldHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * @author Wenyi Feng
 * @since 2018-09-18
 */
@Configuration
public class Router {
    @Autowired
    private HelloWorldHandler helloWorldHandler;
    //@Autowired private UserHandler userHandler;

    @Bean
    public RouterFunction<?> routerFunction(){
        return RouterFunctions.route(RequestPredicates.GET("/hello"), helloWorldHandler::helloWorld);
    }
}
