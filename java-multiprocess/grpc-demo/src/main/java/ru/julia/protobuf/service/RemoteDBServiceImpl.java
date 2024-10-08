package ru.julia.protobuf.service;

import io.grpc.stub.StreamObserver;
import java.util.List;
import ru.julia.protobuf.model.User;
import ru.otus.protobuf.Empty;
import ru.otus.protobuf.RemoteDBServiceGrpc;
import ru.otus.protobuf.UserMessage;

public class RemoteDBServiceImpl extends RemoteDBServiceGrpc.RemoteDBServiceImplBase {

    private final RealDBService realDBService;

    public RemoteDBServiceImpl(RealDBService realDBService) {
        this.realDBService = realDBService;
    }

    @Override
    public void saveUser(UserMessage request, StreamObserver<UserMessage> responseObserver) {
        User user = realDBService.saveUser(request.getFirstName(), request.getLastName());
        responseObserver.onNext(user2UserMessage(user));
        responseObserver.onCompleted();
    }

    @Override
    public void findAllUsers(Empty request, StreamObserver<UserMessage> responseObserver) {
        List<User> allUsers = realDBService.findAllUsers();
        allUsers.forEach(u -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            responseObserver.onNext(user2UserMessage(u));
        });
        responseObserver.onCompleted();
    }

    private UserMessage user2UserMessage(User user) {
        return UserMessage.newBuilder()
                .setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .build();
    }
}
