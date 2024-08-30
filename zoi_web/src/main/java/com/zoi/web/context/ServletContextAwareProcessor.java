package com.zoi.web.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public class ServletContextAwareProcessor implements BeanPostProcessor {
    private ServletContext servletContext;

    private ServletConfig servletConfig;

    public ServletContextAwareProcessor(ServletContext servletContext,ServletConfig servletConfig){
        this.servletConfig = servletConfig;
        this.servletContext = servletContext;
    }

    /**
     * 要拿到ServletContext/ServletConfig需要用户自行实现xxAware接口获取属性
     * @param bean the new bean instance
     * @param beanName the name of the bean
     * @return the bean instance to use, either the original or a replacement
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 如果传入的bean实现了ServletContextAware接口，则为其设置ServletContext属性。
        if (bean != null && bean instanceof ServletContextAware){
            ((ServletContextAware)bean).setServletContext(this.servletContext);
        }
        // 如果传入的bean实现了ServletConfigAware接口，则为其设置ServletConfig属性。
        if (bean != null && bean instanceof ServletConfigAware){
            ((ServletConfigAware)bean).setServletConfig(this.servletConfig);
        }

        return bean;
    }
}
