package com.egkatzioura.notification.publisher;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;

public class UpdatePublisher {

    private final Publisher publisher;
    private final Executor executor;

    public UpdatePublisher(Publisher publisher, Executor executor) {
        this.publisher = publisher;
        this.executor = executor;
    }

    public CompletableFuture<String> update(String notification) {
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                                                           .setData(ByteString.copyFromUtf8(notification))
                                                                   .build();
        ApiFuture<String> apiFuture = publisher.publish(pubsubMessage);

        return toCompletableFuture(apiFuture);
    }

    private CompletableFuture<String> toCompletableFuture(ApiFuture<String> apiFuture) {
        final CompletableFuture<String> responseFuture = new CompletableFuture<>();

        ApiFutures.addCallback(apiFuture, new ApiFutureCallback<>() {
            @Override
            public void onFailure(Throwable t) {
                responseFuture.completeExceptionally(t);
            }

            @Override
            public void onSuccess(String result) {
                responseFuture.complete(result);
            }

        }, executor);
        return responseFuture;
    }

}
