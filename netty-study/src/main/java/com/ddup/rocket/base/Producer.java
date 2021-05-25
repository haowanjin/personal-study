package com.ddup.rocket.base;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

public class Producer {
    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.2.9:9876");
        producer.start();
        for (int i = 0; i < 10; i++) {
            Message msg = new Message("topic1", "tag1", ("测试消息哈哈 " + i).getBytes(StandardCharsets.UTF_8));
            SendResult result = producer.send(msg);
            System.out.println(result);
        }
    }
}
