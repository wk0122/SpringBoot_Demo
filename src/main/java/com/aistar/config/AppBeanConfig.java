package com.aistar.config;

import com.aistar.util.*;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * Created by js on 2019/6/27.
 */
@Configuration
public class AppBeanConfig {

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }

    @Bean
    public TokenUtil tokenUtil(){
        return new TokenUtil();
    }


    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize("10240KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }

}
