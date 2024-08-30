package com.zoi.web;

import com.zoi.web.context.AnnotationConfigWebApplicationContext;
import com.zoi.web.context.WebApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ObjectUtils;

import javax.servlet.Filter;

/**
 * @Description TODO
 * @Author Yuzoi
 * @Date 2024/8/31 1:04
 **/
public abstract class AbstractAnnotationConfigDispatcherServletInitializer extends AbstractDispatcherServletInitializer{
    @Override
    protected AnnotationConfigApplicationContext createRootApplicationContext() {
        final Class<?>[] rootConfigClasses = getRootConfigClasses();
        if (ObjectUtils.isEmpty(rootConfigClasses)) {
            final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
            context.register(rootConfigClasses);
            return context;
        }
        return null;
    }

    @Override
    protected WebApplicationContext createWebApplicationContext() {
        final Class<?>[] webConfigClasses = getWebConfigClasses();
        if (ObjectUtils.isEmpty(webConfigClasses)) {
            final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
            context.register(webConfigClasses);
            return context;
        }
        return null;
    }
}
