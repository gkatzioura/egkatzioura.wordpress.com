package com.egkatzioura.notification.publisher;

import java.util.UUID;

import com.google.pubsub.v1.PublishRequest;
import com.google.pubsub.v1.PublishResponse;
import com.google.pubsub.v1.PublisherGrpc;

import io.grpc.stub.StreamObserver;

public class MockPublisherGrpc extends PublisherGrpc.PublisherImplBase {

    private final String prefix;

    public MockPublisherGrpc(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void publish(PublishRequest request, StreamObserver<PublishResponse> responseObserver) {
        responseObserver.onNext(PublishResponse.newBuilder().addMessageIds(prefix+":"+UUID.randomUUID().toString()).build());
        responseObserver.onCompleted();
    }

}
