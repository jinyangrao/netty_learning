package com.mylearn.proto.grpcserver;

import com.mylearn.proto.*;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {


    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {


        System.out.println("接收到客户端信息～！" + request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());

        responseObserver.onCompleted();

    }


    @Override
    public void getStudentByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {

        System.out.println("接收到客户端消息～！" + request.getAge());


        responseObserver.onNext(
                StudentResponse.newBuilder().setName("张三")
                .setAge(20).setCity("北京").build());

        responseObserver.onNext(
                StudentResponse.newBuilder().setName("李四")
                        .setAge(20).setCity("上海").build());
        responseObserver.onNext(
                StudentResponse.newBuilder().setName("王五")
                        .setAge(20).setCity("广州").build());

        responseObserver.onNext(
                StudentResponse.newBuilder().setName("赵六")
                        .setAge(20).setCity("深圳").build());

        responseObserver.onCompleted();


    }

    @Override
    public StreamObserver<StudentRequest> getStudentWrapperByAges(StreamObserver<StudentResponseList> responseObserver) {

        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest value) {
                System.out.println("onNext" + value.getAge());
            }

            @Override
            public void onError(Throwable t) {

                System.out.println("onError" + t.getMessage());
            }

            @Override
            public void onCompleted() {

                StudentResponse sr1 = StudentResponse.newBuilder().setName("赵六")
                        .setAge(20).setCity("北京").build();
                StudentResponse sr2 = StudentResponse.newBuilder().setName("李四")
                        .setAge(20).setCity("深圳").build();
                StudentResponse sr3 = StudentResponse.newBuilder().setName("王五")
                        .setAge(20).setCity("上海").build();

                StudentResponseList sl = StudentResponseList
                        .newBuilder().addStudentResponse(sr1)
                        .addStudentResponse(sr2)
                        .addStudentResponse(sr3).build();

                responseObserver.onNext(sl);
                responseObserver.onCompleted();

            }
        };

    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest value) {

                System.out.println(value.getRequestInfo());
                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());


            }

            @Override
            public void onError(Throwable t) {

                System.out.println(t.getMessage());

            }

            @Override
            public void onCompleted() {

                responseObserver.onCompleted();
            }
        };
    }
}
