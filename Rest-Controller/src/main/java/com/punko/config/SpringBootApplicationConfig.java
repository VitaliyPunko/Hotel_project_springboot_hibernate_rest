package com.punko.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.punko")
@EntityScan(basePackages = {"com.punko.entity"})
public class SpringBootApplicationConfig {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootApplicationConfig.class, args);
    }
}
