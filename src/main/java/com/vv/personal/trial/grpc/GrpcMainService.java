package com.vv.personal.trial.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Vivek
 * @since 15/12/20
 */
@EnableEurekaClient
@SpringBootApplication
public class GrpcMainService {

    public static void main(String[] args) {
        SpringApplication.run(GrpcMainService.class, args);
    }
}
