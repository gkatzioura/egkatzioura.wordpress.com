package com.gkatzioura.order;

import com.egkatzioura.order.v1.OrderRequest;
import com.egkatzioura.order.v1.OrderResponse;
import com.egkatzioura.order.v1.OrderServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ApplicationClient {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        OrderServiceGrpc.OrderServiceBlockingStub orderServiceBlockingStub
                = OrderServiceGrpc.newBlockingStub(managedChannel);

        OrderRequest orderRequest = OrderRequest.newBuilder()
                .setEmail("hello@word.com")
                .setProduct("no-name")
                .setAmount(3)
                .build();

        OrderResponse orderResponse = orderServiceBlockingStub.executeOrder(orderRequest);

        System.out.println("Received response: "+orderResponse.getInfo());

        managedChannel.shutdown();
    }
}