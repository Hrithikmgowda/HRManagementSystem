package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableCaching

public class HrManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                HrManagementSystemApplication.class,args );
    }
}

