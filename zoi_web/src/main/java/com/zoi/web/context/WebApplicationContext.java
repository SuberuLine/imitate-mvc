package com.zoi.web.context;

import org.springframework.context.ApplicationContext;

/**
 * 继承ApplicationContext，添加web相关方法
 */
public interface WebApplicationContext extends ApplicationContext {
    public static final String ROOT_NAME = WebApplicationContext.class.getName() + "ROOT";
}
