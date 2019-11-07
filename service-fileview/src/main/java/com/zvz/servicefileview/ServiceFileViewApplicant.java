package com.zvz.servicefileview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;

@SpringBootApplication
@EnableScheduling
public class ServiceFileViewApplicant {
    public static void main(String[] args) {
        SpringApplication.run(ServiceFileViewApplicant.class, args);
    }
}
