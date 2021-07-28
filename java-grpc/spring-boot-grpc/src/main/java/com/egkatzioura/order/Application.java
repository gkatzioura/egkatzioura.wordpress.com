package com.egkatzioura.order;

import java.io.IOException;

import com.egkatzioura.order.impl.OrderServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class Application {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new OrderServiceImpl()).build();

        server.start();
        server.awaitTermination();
    }

}