package com.ddup.netty.reactor.single;



import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EchoServerReactor implements Runnable {
    //private final static Logger logger = LoggerFactory.getLogger(EchoServerReactor.class);

    Selector selector;
    ServerSocketChannel serverSocketChannel;

    public EchoServerReactor() throws IOException {
        // 1.获取选择器
        selector = Selector.open();
        // 2.获取通道
        serverSocketChannel = ServerSocketChannel.open();
        // 3.设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 4.绑定连接
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 8989));
        System.out.println("服务器启动成功");
        // 5.将通道注册的 "接收新连接" IO 事件，注册到选择器上
        SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 6.将新连接处理器作为附件，绑定到sk选择键
        sk.attach(new AcceptorHandler());
    }


    @Override
    public void run() {
        // 选择器轮询
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()) {
                    // 反应器负责 dispatch 收到的事件
                    SelectionKey sk = it.next();
                    dispatch(sk);
                }
                selected.clear();
            }
        } catch (Exception e) {
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

    // 新连接处理器
    class AcceptorHandler implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    new EchoHandler(selector, socketChannel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
