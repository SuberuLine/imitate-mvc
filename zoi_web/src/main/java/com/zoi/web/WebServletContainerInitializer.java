package com.zoi.web;

import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description TODO
 * @Author Yuzoi
 * @Date 2024/8/31 1:13
 **/
@HandlesTypes(WebApplicationInitializer.class)
public class WebServletContainerInitializer implements ServletContainerInitializer {

    /**
     * 此方法在Servlet容器启动时被调用，用于执行初始化操作。
     * @param webAppInitializerClasses 一个包含webAppInitializer类类型的集合，通过@HandlesTypes注解指定的支持类型
     * @param servletContext 当前Servlet容器的上下文对象，用于获取和设置Servlet容器的状态
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> webAppInitializerClasses, ServletContext servletContext) throws ServletException {
        if(!ObjectUtils.isEmpty(webAppInitializerClasses)){
            final List<WebApplicationInitializer> initializers = new ArrayList<>(webAppInitializerClasses.size());
            // 遍历集合，排除接口和抽象类
            for (Class<?> webAppInitializerClass : webAppInitializerClasses) {
                if (!webAppInitializerClass.isInterface() && !Modifier.isAbstract(webAppInitializerClass.getModifiers()) &&
                        // 判断是否是WebApplicationInitializer的子类或实现了WebApplicationInitializer接口
                        WebApplicationInitializer.class.isAssignableFrom(webAppInitializerClass)){
                    try {
                        // 对于符合条件的类，使用反射来实例化这些类
                        initializers.add((WebApplicationInitializer)
                                ReflectionUtils.accessibleConstructor(webAppInitializerClass).newInstance());
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
            for (WebApplicationInitializer initializer : initializers) {
                initializer.onStartup(servletContext);
            }
        }
    }
}