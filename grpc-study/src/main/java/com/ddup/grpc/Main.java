package com.ddup.grpc;

import java.io.IOException;

/**
 * @author ${USER}
 * @date ${DATE} ${TIME}
 * <p>
 *
 * </p>
 */
public class Main {
    public static void main(String[] args) throws IOException {
        int port = 8000;
        GrpcServer server = new GrpcServer(port);
        server.start();
        HelloWorldClient client = new HelloWorldClient("localhost", port);
        String reply = client.sayHello("hello word");
        System.out.println(reply);
        // 关闭服务与通道，否则会报错
        server.shutdown();
        client.shutdown();
    }
}