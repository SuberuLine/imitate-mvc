package com.zoi.web.context;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.AbstractRefreshableConfigApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * 该抽象类实现了 ConfigurableWebApplicationContext 接口，提供了刷新上下文的行为
 */
public abstract class AbstractRefreshableWebApplicationContext
        extends AbstractRefreshableConfigApplicationContext
        implements ConfigurableWebApplicationContext {

    protected ServletContext servletContext;

    protected ServletConfig servletConfig;

    @Override
    public void setServletConfig(ServletConfig servletConfig) {
        this.servletConfig = servletConfig;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    @Override
    public ServletContext getServletContext() {
        return this.servletContext;
    }

    /**
     * 添加后置处理器
     * @param beanFactory the bean factory used by the application context
     */
    @Override
    protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        beanFactory.addBeanPostProcessor(new ServletContextAwareProcessor(this.servletContext, this.servletConfig));

        // 忽略 ServletContext 和 ServletConfig 依赖接口，避免自动装配
        beanFactory.ignoreDependencyInterface(ServletContext.class);
        beanFactory.ignoreDependencyInterface(ServletConfig.class);
    }
}
