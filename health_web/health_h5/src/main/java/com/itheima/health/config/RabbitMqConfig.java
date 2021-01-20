//package com.itheima.health.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author wangweili
// */
//@Configuration
//public class RabbitMqConfig {
//
//    /**
//     * 交换机名称
//     */
//    public static final String TEST_EXCHANGE = "health_appointment_exchange";
//
//    /**
//     * 队列名称
//     */
//    public static final String TEST_QUEUE = "email-queue";
//
//    /**
//     * 声明交换机 类型为direct
//     *
//     * @return Exchange
//     */
//    @Bean("testDirectExchange")
//    public Exchange testExchange() {
//        return new DirectExchange(TEST_EXCHANGE, true, false);
//    }
//
//    /**
//     * 声明队列
//     *
//     * @return Queue
//     */
//    @Bean("testQueue")
//    public Queue testQueue() {
//        return new Queue(TEST_QUEUE, true);
//    }
//
//    @Bean
//    public Binding testQueueExchange(@Qualifier("testQueue") Queue queue, @Qualifier("testDirectExchange") Exchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("email-key").noargs();
//    }
//}