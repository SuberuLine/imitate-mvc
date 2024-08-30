package com.zoi.web;

import com.zoi.web.context.AbstractRefreshableWebApplicationContext;
import com.zoi.web.context.WebApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractRefreshableConfigApplicationContext;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class BaseHttpServlet extends HttpServlet {

    protected WebApplicationContext webApplicationContext;

    public BaseHttpServlet(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    // 初始化ioc容器并配置
    @Override
    public void init() throws ServletException {
        // todo 父子容器
        final ServletContext servletContext = getServletContext();
        ApplicationContext rootContext = (ApplicationContext) servletContext.getAttribute(WebApplicationContext.ROOT_NAME);

        if (!ObjectUtils.isEmpty(webApplicationContext)) {
            AbstractRefreshableWebApplicationContext wac =
                    (AbstractRefreshableWebApplicationContext) this.webApplicationContext;
            // 设置父子容器
            if (wac.getParent() == null) {
                wac.setParent(rootContext);
            }
            // 配置上下文
            wac.setServletContext(servletContext);
            wac.setServletConfig(getServletConfig());
            // 容器刷新
            wac.refresh();
        }
    }

    protected abstract void onRefresh();
}
