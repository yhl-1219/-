package com.itheima.health.mq;

import com.itheima.health.service.OrderService;
import com.itheima.health.utils.redis.RedisUtil;
import com.itheima.health.utils.resources.RedisMessageConstant;
import com.itheima.health.utils.tencentcloud.SendSms;
import com.rabbitmq.client.Channel;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wangweili
 */
@Component
public class EmailRedisServer {

    @Reference
    private OrderService orderService;

    @RabbitHandler
    @RabbitListener(queues = "email-queue")
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


    @RabbitHandler
    @RabbitListener(queues = "dlx_queue")
    public void receiveDlx(Map rabbitMessage, Channel channel, Message message) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        int oid = Integer.parseInt((String) rabbitMessage.get("oid"));
        String telephone = (String) rabbitMessage.get("telephone");
        //查询订单状态，并发送短信
        Boolean orderOk = orderService.findOrderStateById(oid);
        if (Boolean.TRUE.equals(orderOk)) {
            SendSms.sendSuccessMsg(telephone);
        } else {
            orderService.updateReservationsByOrderDate(oid);
            orderService.removeById(oid);
        }
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}