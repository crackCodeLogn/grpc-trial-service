package com.vv.personal.trial.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * @author Vivek
 * @since 15/12/20
 */
@Component
public class GrpcServerComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcServerComponent.class);

    @Value("${server.grpc.port:8080}")
    private int grpcServerPort;

    @Lazy
    @Bean(destroyMethod = "shutdown")
    private Server GrpcServer() {
        return ServerBuilder
                .forPort(grpcServerPort)
                .addService(new HelloServiceImpl())
                .build();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startGrpcServer() throws IOException {
        LOGGER.info("GRPC Server built!");
        GrpcServer().start();
        LOGGER.info("Server started, on port: {}", grpcServerPort);
    }

}
