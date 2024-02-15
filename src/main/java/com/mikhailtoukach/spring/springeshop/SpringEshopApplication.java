package com.mikhailtoukach.spring.springeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
//@ComponentScan(value="com.mikhailtoukach.spring.springeshop")
public class SpringEshopApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringEshopApplication.class, args);
        ConfigurableApplicationContext context =  SpringApplication.run(SpringEshopApplication.class, args);
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
        System.out.println(encoder.encode("pass"));

    }

}
