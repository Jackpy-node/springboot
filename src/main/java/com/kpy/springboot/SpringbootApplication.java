package com.kpy.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringbootApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    // SpringBootServletInitializer 实现configure 方便打war 外部服务器部署。
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootApplication.class);
    }
}
