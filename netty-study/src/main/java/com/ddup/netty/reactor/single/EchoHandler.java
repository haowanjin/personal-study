package com.ddup.netty.reactor.single;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class EchoHandler implements Runnable {
    //private final static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
    private final SocketChannel channel;
    private final SelectionKey sk;
    private final ByteBuffer buffer = ByteBuffer.allocate(1024);
    private static final int RECEIVING = 0, SENDING = 1;
    private int state = RECEIVING;

    public EchoHandler(Selector selector, SocketChannel channel) throws IOException {
        this.channel = channel;
        channel.configureBlocking(false);
        // 取得选择键，再设置甘心去的IO事件
        sk = channel.register(selector, 0);
        // 将 handler 自身作为选择键的附件
        sk.attach(this);
        // 注册 read 就绪事件
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }


    @Override
    public void run() {
        try {
            if (state == SENDING) {
                // 写入通道
                channel.write(buffer);
                // 写完后，准备开始从通道读，buffer 切换成写入模式
                buffer.clear();
                // 写完后，注册 read 就绪事件
                sk.interestOps(SelectionKey.OP_READ);
                // 写完后，进入接收的状态
                state = RECEIVING;
            } else if (state == RECEIVING) {
                // 从通道读
                int len = 0;
                while ((len = channel.read(buffer)) > 0) {
                    System.out.println(new String(buffer.array(), 0, len));
                }
                // 读完后，准备开始写入通道，buffer 切换成读取模式
                buffer.flip();
                // 读完后，注册 write 就绪事件
                sk.interestOps(SelectionKey.OP_WRITE);
                // 读完后，进入发送的状态
                state = SENDING;
            }

            // 处理结束了，这里不能关闭select key, 需要重复使用
//            sk.cancel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
