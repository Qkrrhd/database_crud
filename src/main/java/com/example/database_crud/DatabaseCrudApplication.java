package com.example.database_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
//컴포넌트 스캔은  해당 패키지의 위치의 자바코드를 스캔하여 빈객체를 자동으로 등록해주는 어노테이션입니다
//@ComponentScan(basePackages={"com.example.database_crud.controller","com.example.database_crud.service","com.example.database_crud.dao"})
@ComponentScan(basePackages={"com.example.database_crud.*"})
@SpringBootApplication
public class DatabaseCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseCrudApplication.class, args);
    }
    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
