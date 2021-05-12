package com.punko.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.punko")
public class SpringBootApplicationConfig {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootApplicationConfig.class, args);
    }
}
