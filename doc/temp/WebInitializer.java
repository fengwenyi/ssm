package com.fengwenyi;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import com.fengwenyi.configurer.MvcConfig;
import com.fengwenyi.configurer.WebFluxConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import ch.qos.logback.ext.spring.web.LogbackConfigListener;


/**
 * web配置类。程序的入口
 * WebApplicationInitializer类是Spring提供用来配置Servlet3.0+配置的接口，从而实现了代替web.xml的位置。
 * 实现此接口将会自动被SpringServletContainerInitializer获取到，SpringServletContainerInitializer用来启动Servlet3.0容器。
 * 容器启动时会自动扫描当前服务中ServletContainerInitializer的实现类，并调用其onStartup方法，
 * 其参数Set<Class<?>> c，可通过在实现类上声明注解javax.servlet.annotation.HandlesTypes(xxx.class)注解自动注入，
 * @HandlesTypes会自动扫描项目中所有的xxx.class的实现类，并将其全部注入Set。
 * @author Wenyi Feng
 * @since 2018-09-18
 */
public class WebInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 新建一个WebApplicationContext的注册配置类，并和当前的servletContext关联
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(MvcConfig.class);
        context.register(WebFluxConfig.class);
        context.setServletContext(servletContext);

        // 注册Spring mvc的DispatchServlet
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);

        // 日志配置(如果 classpath 里有  logback.xml文件， logback 会试图用它进行自我配置，所以下面两句代码可以不写)
//        servletContext.setInitParameter("logbackConfigLocation", "classpath:logback-spring.xml");
//        servletContext.addListener(LogbackConfigListener.class);
    }

}

