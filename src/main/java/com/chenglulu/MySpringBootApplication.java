package com.chenglulu;

import org.apache.log4j.PropertyConfigurator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(basePackages = "com.chenglulu")
@MapperScan(basePackages = {"com.chenglulu.mybatis.dao"})
@ConfigurationProperties("log4j.properties")
public class MySpringBootApplication {
    public static void main(String[] args){
        SpringApplication.run(MySpringBootApplication.class, args);
    }
}
