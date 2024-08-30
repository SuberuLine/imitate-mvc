package com.zoi.web.context;

import org.springframework.lang.Nullable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * 继承WebApplicationContext，增加配置上下文的能力
 */
public interface ConfigurableWebApplicationContext extends WebApplicationContext{
    void setServletContext(@Nullable ServletContext servletContext);

    void setServletConfig(@Nullable ServletConfig servletConfig);

    ServletContext getServletContext();

    ServletConfig getServletConfig();
}
