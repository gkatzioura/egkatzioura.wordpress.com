package com.gkatzioura.order.impl;

import com.egkatzioura.order.v1.OrderRequest;
import com.egkatzioura.order.v1.OrderResponse;
import com.egkatzioura.order.v1.OrderServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase{

    @Override
    public void executeOrder(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        OrderResponse response = OrderResponse.newBuilder()
                .setInfo("Hi "+request.getEmail()+", you order has been executed")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
