package com.ddup.netty.reactor.single;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EchoClient {
    private static Scanner in = new Scanner(System.in);

    public void startClient() throws IOException {
        // 获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9090));
        // 切换成非阻塞模式
        socketChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (socketChannel.finishConnect()) {
            System.out.println("客户端连接成功");
            while (in.hasNext()) {
                String msg = in.nextLine();
                buffer.put(msg.getBytes(StandardCharsets.UTF_8));
                buffer.flip();
                // 发送到服务器
                socketChannel.write(buffer);

                buffer.clear();

                int read = socketChannel.read(buffer);

                System.out.println("server back:" + new String(buffer.array(), 0, read, "UTF-8"));
                buffer.clear();
            }

        }
    }

    public static void main(String[] args) throws IOException {
        new EchoClient().startClient();
    }
}
