package org.zoi;

import com.zoi.web.AbstractAnnotationConfigDispatcherServletInitializer;
import com.zoi.web.context.WebApplicationContext;

/**
 * @Description TODO
 * @Author Yuzoi
 * @Date 2024/8/31 1:47
 **/
public class QuickStart extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getWebConfigClasses() {
        return new Class[]{AppConfig.class};
    }
}
