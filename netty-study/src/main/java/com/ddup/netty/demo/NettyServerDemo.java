package com.ddup.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServerDemo {

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap b = new ServerBootstrap();

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        b.group(bossGroup, workGroup);

        b.channel(NioServerSocketChannel.class);

        b.localAddress(9091);

        b.option(ChannelOption.SO_KEEPALIVE, true);
        b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

        b.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new NettyDiscardHandler());
            }
        });


        ChannelFuture channelFuture = b.bind().sync();
        System.out.println("服务端启动,监听端口:" + channelFuture.channel().localAddress());

        ChannelFuture closeFuture = channelFuture.channel().closeFuture();
        closeFuture.sync();
    }
}
