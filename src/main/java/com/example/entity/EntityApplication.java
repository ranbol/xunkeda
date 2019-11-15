package com.example.entity;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.example.entity.dao")
@ServletComponentScan(basePackages = "com.example.entity")
public class EntityApplication {
    public static void main(String[] args) {
        SpringApplication.run (EntityApplication.class, args);
    }

}
