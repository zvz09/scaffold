package com.zvz.serviceblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceBlogApplication.class, args);
    }
}
