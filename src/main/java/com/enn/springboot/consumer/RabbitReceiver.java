package com.enn.springboot.consumer;

import com.enn.springboot.entity.Order;
import com.rabbitmq.client.Channel;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class RabbitReceiver {
    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "queue_1",durable = "true"),
    exchange = @Exchange(value = "exchange_1",type = "topic"),key = "springboot.*"))
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws IOException {
        System.err.println("--------------------------------------");
        System.err.println("消费端Payload: " + message.getPayload());
        Long tag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(tag,false);
    }
    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "queue_2",durable = "true"),
            exchange = @Exchange(value = "exchange_2",type = "topic"),key = "springboot.*"))
    @RabbitHandler
    public void onOrderMessage(@Payload Order order, Channel channel, @Headers Map<String,Object> headers) throws IOException {
        System.err.println("--------------------------------------");
        System.err.println("消费端Payload: " + order.toString());
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(tag,false);
    }

}
