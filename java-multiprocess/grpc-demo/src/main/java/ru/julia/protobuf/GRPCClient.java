package ru.julia.protobuf;

import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import ru.otus.protobuf.Empty;
import ru.otus.protobuf.RemoteDBServiceGrpc;
import ru.otus.protobuf.UserMessage;
import java.util.concurrent.CountDownLatch;

public class GRPCClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8190;

    public static void main(String[] args) throws InterruptedException {
        var channel = ManagedChannelBuilder.forAddress(SERVER_HOST, SERVER_PORT)
                .usePlaintext()
                .build();

        var stub = RemoteDBServiceGrpc.newBlockingStub(channel);
        var savedUserMsg = stub.saveUser(UserMessage.newBuilder()
                .setFirstName("Vasya")
                .setLastName("Ivanov")
                .build());

        System.out.printf("User was saved: {id: %d, name: %s %s}%n",
                savedUserMsg.getId(), savedUserMsg.getFirstName(), savedUserMsg.getLastName());

        var allUsersIterator = stub.findAllUsers(Empty.getDefaultInstance());
        System.out.println("Get all users with Vasya:");
        allUsersIterator.forEachRemaining(
                um -> System.out.printf("{id: %d, name: %s %s}%n", um.getId(), um.getFirstName(), um.getLastName()));

        System.out.println("\n\n\nthe same actions, but asynchronously:\n\n");
        var latch = new CountDownLatch(1);
        var newStub = RemoteDBServiceGrpc.newStub(channel);
        newStub.findAllUsers(Empty.getDefaultInstance(), new StreamObserver<UserMessage>() {
            @Override
            public void onNext(UserMessage um) {
                System.out.printf("{id: %d, name: %s %s}%n", um.getId(), um.getFirstName(), um.getLastName());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println(t);
            }

            @Override
            public void onCompleted() {
                System.out.println("\n\nCompleted!");
                latch.countDown();
            }
        });

        latch.await();
        channel.shutdown();
    }
}
