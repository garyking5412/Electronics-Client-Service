package com.example.electronicsspringbootclientservice.config;

import com.example.electronicsspringbootclientservice.utils.Constants;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitMQConfig {
//
////    @Bean
//    public CachingConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setAddresses("localhost");
//        connectionFactory.setPort(5672);
//        connectionFactory.setConnectionTimeout(1000);
//        return connectionFactory;
//    }
//
////    @Bean
//    public Declarables rabbitQueueBindings() {
////        Queue fanoutQueue1 = new Queue("fanout.queue1", false);
//        Queue fanoutQueue1 = QueueBuilder.durable("fanout.queue1").withArgument("x-dead-letter-exchange", "fanout.queue1.dlx").withArgument("x-dead-letter-routing-key", "QUEUE_MESSAGES_DLQ").build();
//        Queue fanoutQueue2 = QueueBuilder.durable("fanout.queue2").withArgument("x-dead-letter-exchange", "fanout.queue2.dlx").withArgument("x-dead-letter-routing-key", "QUEUE_MESSAGES_DLQ").build();
//        FanoutExchange fanoutExchange = new FanoutExchange(Constants.rabbitTopicExchangeName);
//        DirectExchange directExchange = new DirectExchange(Constants.rabbitTopicRetryExchangeName);
//        return new Declarables(
//                fanoutQueue1, fanoutQueue2, fanoutExchange, directExchange, BindingBuilder.bind(fanoutQueue1).to(directExchange).withQueueName(), BindingBuilder.bind(fanoutQueue2).to(fanoutExchange)
//        );
//    }
//
////    @Bean
//    public Queue deadLetterQueue() {
//        return QueueBuilder.durable("QUEUE_MESSAGES_DLQ").build();
//    }
//
////    @Bean
//    FanoutExchange deadLetterExchange2() {
//        return new FanoutExchange("fanout.queue2.dlx");
//    }
//
////    @Bean
//    FanoutExchange deadLetterExchange1() {
//        return new FanoutExchange("fanout.queue1.dlx");
//    }
//
////    @Bean
//    Declarables deadLetterBinding() {
//        return new Declarables(BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange1()), BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange2()));
//    }
//
////    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        return new RabbitTemplate(connectionFactory());
//    }
}
