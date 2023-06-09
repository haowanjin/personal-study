package com.ddup.grpc;
 
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
 
public class HelloWorldClient {
 
    private final HelloWorldGrpc.HelloWorldBlockingStub blockingStub;
    private static ManagedChannel managedChannel =null;
   /**
    * @param host gRPC服务的主机名
    * @param port gRPC服务的端口
    */
    public HelloWorldClient(String host, int port) {
        managedChannel = ManagedChannelBuilder.forAddress(host, port)
              // 使用非安全机制传输
                .usePlaintext()
                .build();
 
        blockingStub = HelloWorldGrpc.newBlockingStub(managedChannel);
    }
 
    public String sayHello(String name) {
        Greeting greeting = Greeting.newBuilder()
                .setName(name)
                .build();
        HelloResp resp = blockingStub.sayHello(greeting);
 
        return resp.getReply();
    }
    
	public void shutdown() {
		managedChannel.shutdown();
	}
}
