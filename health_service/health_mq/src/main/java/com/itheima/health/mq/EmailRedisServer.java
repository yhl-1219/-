package com.itheima.health.mq;

import com.itheima.health.utils.redis.RedisUtil;
import com.itheima.health.utils.resources.RedisMessageConstant;
import com.itheima.health.utils.tencentcloud.SendSms;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
@RabbitListener(queues = "email-queue")
public class EmailRedisServer {

    @RabbitHandler
    public void receive(String rabbitMessage, Channel channel, Message message) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        Integer o = SendSms.sendVerifyMsg(rabbitMessage);
        RedisUtil.set(RedisMessageConstant.SENDTYPE_ORDER + rabbitMessage, o, 5, TimeUnit.MINUTES);
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}