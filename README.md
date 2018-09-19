# SSM

手把手教你整合最优雅SSM框架：SpringMVC + Spring + MyBatis，并开发RESTful风格的API接口

## HEY

本节主要内容为：基于Spring从0到1搭建一个web工程，适合初学者，Java初级开发者。欢迎与我交流。

## MODULE

新建一个Maven工程。

![Maven Web App](https://upload-images.jianshu.io/upload_images/5805596-852edbeeb33b69ae.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

不论你是什么工具，选这个就可以了，然后next，直至finish。

## POM.XML

引jar是一个难点，都是一股脑的引入，这是我们开始的第一步，很关键，我们分开说。

先看Spring，我们可能需要的jar：

```xml
<!-- spring核心依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- spring ioc依赖 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- spring aop依赖 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- spring 扩展依赖 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- spring web相关依赖 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- spring test相关依赖 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!-- spring依赖  end-->
```

大抵就是这样，当然，还有其他的作为辅助开发，这个可以看源码，然后我们看一下版本。

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>

    <junit.version>5.3.1</junit.version>
    <lombok.version>1.18.2</lombok.version>
    <jetty.version>9.4.12.v20180830</jetty.version>
    <spring.version>5.0.9.RELEASE</spring.version>
    <JavaLib.version>1.0.4.RELEASE</JavaLib.version>
    <slf4j.version>1.8.0-beta2</slf4j.version>
    <log4j.version>1.2.17</log4j.version>
</properties>
```

## WEB.XML

先配请求拦截，意思是说，这个请求交给Spring管理。

```xml
<!-- 配置DispatcherServlet -->
<servlet>
    <servlet-name>mvc-dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring/spring-*.xml</param-value>
    </init-param>
</servlet>
<servlet-mapping>
    <servlet-name>mvc-dispatcher</servlet-name>
    <!-- 默认匹配所有的请求 -->
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

总有人会遇到乱码问题，那我们也解决一下。

```xml
<!-- 编码  -->
<filter>
    <filter-name>characterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
        <param-name>forceEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>characterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

最后，我们加一个首页响应页面路径

```xml
<welcome-file-list>
    <welcome-file>/</welcome-file>
</welcome-file-list>
```

## SPRING.XML

配一个扫描

```
<!-- 开启SpringMVC注解模式 -->
<mvc:annotation-driven />

<!--向容器自动注入配置-->
<context:annotation-config />

<!--将静态资源交由默认的servlet处理-->
<mvc:default-servlet-handler />

<!-- 扫描web相关的bean -->
<context:component-scan base-package="com.fengwenyi.springweb.controller" />
```

用springmvc的人，大抵都会用到页面，那我们也配一下。

```xml
<!-- configure the InternalResourceViewResolver -->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
      id="internalResourceViewResolver">
    <!-- 前缀 -->
    <property name="prefix" value="/WEB-INF/pages/" />
    <!-- 后缀 -->
    <property name="suffix" value=".html" />
</bean>
```

## CODE

```
package com.fengwenyi.springweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Wenyi Feng
 * @since 2018-09-18
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
```

## RUN

运行，会自动跳转这个页面

```
http://localhost:8080/
```

![Hello World](https://upload-images.jianshu.io/upload_images/5805596-377b25b58675852b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

哦，对了，我已经写好了html页面。

## PROJECT

我们看一下工程目录结构吧

![project](https://upload-images.jianshu.io/upload_images/5805596-11c01dd4deca59f3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)