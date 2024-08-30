package com.zoi.web;

import javax.servlet.ServletContext;


public interface WebApplicationInitializer{
    void onStartup(ServletContext servletContext);
}
