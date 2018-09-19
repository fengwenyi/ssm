package com.fengwenyi;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * @author Wenyi Feng
 * @since 2018-09-18
 */
public class JettyServer {

    private static Logger logger = LoggerFactory.getLogger(JettyServer.class);

    private static final int HTTP_PORT = 8080;
    private static final int HTTPS_PORT = 8443;
    private static final String CONTEXT = "/";

    public static void main(String[] args) {
        Server server = new JettyServer().createServer();
        try {
            server.start();
            logger.info("jetty服务开启！！！");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JettyServer() {}

    private Server createServer() {
        // jetty server 默认的最小连接线程是8，最大是200，连接线程最大闲置时间60秒
        Server server = new Server();
        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setSecureScheme("https");
        httpConfig.setSecurePort(HTTPS_PORT);
        httpConfig.setOutputBufferSize(32768);
        httpConfig.setRequestHeaderSize(8192);
        httpConfig.setResponseHeaderSize(8192);
        httpConfig.setSendServerVersion(true);
        httpConfig.setSendDateHeader(false);
        httpConfig.setHeaderCacheSize(512);
        ConnectionFactory connectionFactory = new HttpConnectionFactory(httpConfig);
        ServerConnector connector = new ServerConnector(server, connectionFactory);
        connector.setPort(HTTP_PORT);
        connector.setSoLingerTime(-1);
        // 连接线程最大空闲时间
        connector.setIdleTimeout(30000);
        server.addConnector(connector);

        URL url = JettyServer.class.getProtectionDomain().getCodeSource().getLocation();
        String path = "";
        try {
            path = new File(url.toURI()).getParent();
        } catch (URISyntaxException e) {
        }/*
         * 如果将项目的配置文件打包到jar包外，可以使用FileSystemXmlApplicationContext加载
         * AbstractXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(path+ "/webapp/WEB-INF/springConfig/applicationContext.xml");
         */
        // 如果在springMVC配置文件中，引入了springIOC配置文件applicationContext.xml,在这里就没有必要再创建applicationContext并将其放入到webApplicationContext中
        // 这里使用ClassPathXmlApplicationContext的原因是在打包后，打包的jar文件目录其实是classpath目录，不会出现文件找不到
        AbstractXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml");
        WebAppContext webAppContext = new WebAppContext();
        /*
         * contextPath 是URL的前缀。例如一个contextPath 是/foo，它将处理 /foo， /foo/index.html， /foo/bar/，and /foo/bar/image.png等请求，
         * 但是它不会处理像/，/other/，or /favicon.ico这样的请求
         */
        webAppContext.setContextPath(CONTEXT);
        webAppContext.setDescriptor(path+"/webapp/WEB-INF/web.xml");

        // 配置资源，这个配置是一个目录，包含各种静态资源信息，可以是图片或者HTML页面。
        webAppContext.setResourceBase(path+"/webapp");
        // 配置监听主机ip或名称，没有配置的将不会被监听到。
        //webAppContext.setVirtualHosts(new String[] {});
        webAppContext.setConfigurationDiscovered(true);
        webAppContext.setParentLoaderPriority(true);
        webAppContext.setMaxFormContentSize(10 << 20);
        webAppContext.setClassLoader(Thread.currentThread().getContextClassLoader());

        XmlWebApplicationContext xmlWebAppContext = new XmlWebApplicationContext();
        xmlWebAppContext.setParent(applicationContext);
        xmlWebAppContext.setConfigLocation("");
        xmlWebAppContext.setServletContext(webAppContext.getServletContext());
        xmlWebAppContext.refresh();
        // 配置属性，可以传递到实体类中，比如javax.servlet.context.tempdir属性用来配置临时目录。
        webAppContext.setAttribute(
                WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
                xmlWebAppContext);
        server.setHandler(webAppContext);
        return server;
    }

}