package com.vv.personal.trial.grpc.client;

import com.vv.personal.grpc.proto.HelloRequest;
import com.vv.personal.grpc.proto.HelloResponse;
import com.vv.personal.grpc.proto.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author Vivek
 * @since 15/12/20
 * Independent client which runs from std java main. Not part of spring.
 */
public class GrpcClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcClient.class);

    public static void main(String[] args) {
        LOGGER.info("Connecting to {}:{}", args[0], args[1]);
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(args[0], Integer.parseInt(args[1]))
                .usePlaintext()
                .build();
        LOGGER.info("Channel prepped!");

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);
        LOGGER.info("Channel stub prepped, sending req to grpc server now!");
        HelloRequest helloRequest = HelloRequest.newBuilder().setFirstName(args[2]).setLastName(args[3]).build();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        HelloResponse response = stub.sayHello(helloRequest);
        stopWatch.stop();
        LOGGER.info("Obtained response: {}, in {}ms", response.getMessage(), stopWatch.getTime(TimeUnit.MILLISECONDS));
        channel.shutdown();
    }
}
