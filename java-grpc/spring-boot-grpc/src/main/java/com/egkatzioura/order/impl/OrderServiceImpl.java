package com.egkatzioura.order.impl;

import com.egkatzioura.order.v1.Order;
import com.egkatzioura.order.v1.OrderServiceGrpc;

import io.grpc.stub.StreamObserver;

public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

    @Override
    public void executeOrder(Order.OrderRequest request, StreamObserver<Order.OrderResponse> responseObserver) {

        Order.OrderResponse response = Order.OrderResponse.newBuilder()
                                                          .setInfo("Hi "+request.getEmail()+", you order has been executed")
                                                          .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
