package com.ddup.netty.reactor.multy;


import com.ddup.netty.reactor.single.EchoHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadEchoServerReactor {
    private ServerSocketChannel serverSocketChannel;
    private AtomicInteger next = new AtomicInteger(0);
    // 选择器集合，引入多个选择器
    Selector[] selectors = new Selector[2];
    // 引入多个子反应器
    SubReactor[] subReactors = null;

    public MultiThreadEchoServerReactor() throws IOException {
        // 初始化多个选择器
        selectors[0] = Selector.open();
        selectors[1] = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 9090));

        // 第一个选择器，负责健康新连接事件
        SelectionKey sk = serverSocketChannel.register(selectors[0], SelectionKey.OP_ACCEPT);
        // 绑定新连接监控 handler 处理器到 SelectionKey
        sk.attach(new AcceptorHandler());
        // 第一个子反应器，一个子反应器负责一个选择器
        SubReactor subReactor1 = new SubReactor(selectors[0]);
        // 第二个子反应器，一个子反应器负责一个选择器
        SubReactor subReactor2 = new SubReactor(selectors[1]);

        subReactors = new SubReactor[]{subReactor1, subReactor2};
    }

    private void startService() {
        // 一个反应器对应一个线程
        new Thread(subReactors[0]).start();
        new Thread(subReactors[1]).start();
    }

    class SubReactor implements Runnable {
        private final Selector selector;

        public SubReactor(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    selector.select();
                    Set<SelectionKey> keySet = selector.selectedKeys();
                    Iterator<SelectionKey> it = keySet.iterator();
                    while (it.hasNext()) {
                        SelectionKey sk = it.next();
                        dispatch(sk);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 反应器分发方法
        public void dispatch(SelectionKey selectionKey) {
            Runnable handler = (Runnable) selectionKey.attachment();
            // 调用之前绑定到选择键上的 handler 处理器对象\
            if (handler != null) {
                handler.run();
            }
        }
    }

    // 新连接处理器
    class AcceptorHandler implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    new EchoHandler(selectors[next.get()], socketChannel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}