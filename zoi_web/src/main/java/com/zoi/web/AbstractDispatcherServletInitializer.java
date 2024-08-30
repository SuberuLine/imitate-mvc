package com.zoi.web;

import com.zoi.web.context.WebApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ObjectUtils;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public abstract class AbstractDispatcherServletInitializer implements WebApplicationInitializer{

    public static final String DEFAULT_SERVLET_NAME = "dispatcher";

    public static final String DEFAULT_FILTER_NAME = "filters";

    public static final int M = 1024*1024;
    @Override
    public void onStartup(ServletContext servletContext) {
        // 创建父容器
        final AnnotationConfigApplicationContext rootApplicationContext = createRootApplicationContext();
        // 父容器放入servletContext
        servletContext.setAttribute(WebApplicationContext.ROOT_NAME, rootApplicationContext);
        // 刷新父容器 - 在源码中通过servlet事件进行刷新
        rootApplicationContext.refresh();
        final WebApplicationContext webApplicationContext = createWebApplicationContext();
        // 创建DispatcherServlet
        final DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet(DEFAULT_SERVLET_NAME, dispatcherServlet);
        // 配置文件信息
        dynamic.setLoadOnStartup(1);
        MultipartConfigElement configElement = new MultipartConfigElement(
                null, 5*M, 5*M, 5);
        dynamic.setMultipartConfig(configElement);
        dynamic.addMapping(getMappings());
        final Filter[] filters = getFilters();
        if (!ObjectUtils.isEmpty(filters)){
            for (Filter filter : filters) {
                servletContext.addFilter(DEFAULT_FILTER_NAME, filter);
            }
        }
    }

    // 过滤器
    protected Filter[] getFilters() {
        return null;
    }

    // 映射器
    protected String[] getMappings(){
        return new String[]{"/"};
    }

    // 创建父容器
    protected abstract AnnotationConfigApplicationContext createRootApplicationContext();
    // 创建子容器
    protected abstract WebApplicationContext createWebApplicationContext();
    // 获取包扫描配置类
    protected abstract Class<?>[] getRootConfigClasses();
    protected abstract Class<?>[] getWebConfigClasses();
}
