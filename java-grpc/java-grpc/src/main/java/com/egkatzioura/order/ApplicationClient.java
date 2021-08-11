package com.egkatzioura.order;

import com.egkatzioura.order.v1.Order;
import com.egkatzioura.order.v1.OrderServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ApplicationClient {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8080)
                                                      .usePlaintext()
                                                      .build();

        OrderServiceGrpc.OrderServiceBlockingStub orderServiceBlockingStub
                = OrderServiceGrpc.newBlockingStub(managedChannel);

        Order.OrderRequest orderRequest = Order.OrderRequest.newBuilder()
                                             .setEmail("hello@word.com")
                                             .setProduct("no-name")
                                             .setAmount(3)
                                             .build();

        Order.OrderResponse orderResponse = orderServiceBlockingStub.executeOrder(orderRequest);

        System.out.println("Received response: "+orderResponse.getInfo());

        managedChannel.shutdown();
    }
}
