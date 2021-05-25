package com.ddup.netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@ChannelHandler.Sharable
public class NettyDiscardHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接到客户端");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("msg type:" + (in.hasArray() ? "heap memory" : "direct memory"));
        int len = in.readableBytes();
        byte[] arr = new byte[len];
        in.getBytes(0, arr);
        System.out.println("server receive: " + new String(arr, "UTF-8"));

        System.out.println("write before,msg.refCnt:" + in.refCnt());

        ChannelFuture f = ctx.writeAndFlush(msg);
        f.addListener((ChannelFutureListener) -> {
            System.out.println("write back, msg.refCnt:" + ((ByteBuf) msg).refCnt());
        });
    }
}
