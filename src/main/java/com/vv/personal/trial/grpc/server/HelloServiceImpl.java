package com.vv.personal.trial.grpc.server;

import com.vv.personal.grpc.proto.HelloRequest;
import com.vv.personal.grpc.proto.HelloResponse;
import com.vv.personal.grpc.proto.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vivek
 * @since 15/12/20
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        LOGGER.info("Within hello from server for req: {}", request);
        String greeting = String.format("Hello %s %s", request.getFirstName(), request.getLastName());

        HelloResponse response = HelloResponse.newBuilder()
                .setMessage(greeting)
                .build();

        LOGGER.info("Prepped response to send back => {}", response);
        responseObserver.onNext(response); //sends to the client
        responseObserver.onCompleted(); //closes the connection, notifying client that no further info will come in
    }
}
