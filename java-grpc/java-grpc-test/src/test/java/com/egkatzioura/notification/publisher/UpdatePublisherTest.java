package com.egkatzioura.notification.publisher;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.rpc.FixedTransportChannelProvider;
import com.google.api.gax.rpc.TransportChannel;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.pubsub.v1.Publisher;

import io.grpc.ManagedChannel;
import io.grpc.Server;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class UpdatePublisherTest {

    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    private static final String MESSAGE_ID_PREFIX = "message";

    private UpdatePublisher updatePublisher;

    @Before
    public void setUp() throws Exception {
        String serverName = InProcessServerBuilder.generateName();

        Server server = InProcessServerBuilder
                .forName(serverName).directExecutor().addService(new MockPublisherGrpc(MESSAGE_ID_PREFIX)).build().start();

        grpcCleanup.register(server);

        ExecutorProvider executorProvider = testExecutorProvider();
        ManagedChannel managedChannel = InProcessChannelBuilder.forName(serverName).directExecutor().build();

        TransportChannel transportChannel = GrpcTransportChannel.create(managedChannel);
        TransportChannelProvider transportChannelProvider = FixedTransportChannelProvider.create(transportChannel);

        String topicName = "projects/test-project/topic/my-topic";
        Publisher publisher = Publisher.newBuilder(topicName)
                                       .setExecutorProvider(executorProvider)
                                       .setChannelProvider(transportChannelProvider)
                                       .build();

        updatePublisher = new UpdatePublisher(publisher, Executors.newSingleThreadExecutor());
    }

    private ExecutorProvider testExecutorProvider() {
        return InstantiatingExecutorProvider.newBuilder()
                                     .setExecutorThreadCount(1)
                                     .build();
    }

    @Test
    public void testPublishOrder() throws ExecutionException, InterruptedException {
        String messageId = updatePublisher.update("Some notification").get();
        assertThat(messageId, containsString(MESSAGE_ID_PREFIX));
    }

}