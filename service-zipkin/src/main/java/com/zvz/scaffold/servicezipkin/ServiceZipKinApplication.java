package com.zvz.scaffold.servicezipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class ServiceZipKinApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceZipKinApplication.class, args);
    }
}
