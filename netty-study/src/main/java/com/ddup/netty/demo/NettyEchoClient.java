package com.ddup.netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class NettyEchoClient {

    public static void main(String[] args) throws InterruptedException {
        Bootstrap b = new Bootstrap();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        b.group(workGroup);
        b.channel(NioSocketChannel.class);
        b.remoteAddress("127.0.0.1", 9091);
        b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new NettyEchoClientHandler());
            }
        });
        ChannelFuture f = b.connect();
        f.addListener((ChannelFuture futureListener) -> {
            if (futureListener.isSuccess()) {
                System.out.println("客户端连接成功");
            } else {
                System.out.println("客户端连接失败");
            }
        });
        f.sync();
        Channel channel = f.channel();
        Scanner in = new Scanner(System.in);
        System.out.println("请数据待发送消息:");

        try {
            while (in.hasNext()) {
                String next = in.nextLine();
                byte[] bytes = next.getBytes(StandardCharsets.UTF_8);
                ByteBuf buf = channel.alloc().buffer();
                buf.writeBytes(bytes);
                channel.writeAndFlush(buf);
                System.out.println("请数据待发送消息:");
            }
        } finally {
            workGroup.shutdownGracefully();
        }
    }

}
