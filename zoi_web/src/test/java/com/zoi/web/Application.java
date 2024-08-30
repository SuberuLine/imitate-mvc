package com.zoi.web;

import com.zoi.web.context.AnnotationConfigWebApplicationContext;

public class Application {
    public static void main(String[] args) {
        final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        System.out.println(context.getBean(Service.class));
    }
}
