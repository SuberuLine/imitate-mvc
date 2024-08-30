package com.zoi.web;

import com.zoi.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends BaseHttpServlet {


    public DispatcherServlet(WebApplicationContext webApplicationContext) {
        super(webApplicationContext);
    }

    // 组件初始化
    @Override
    protected void onRefresh() {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("请求分发");
    }
}
