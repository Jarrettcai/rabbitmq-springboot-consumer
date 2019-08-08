package com.enn.springboot.consumer;

import com.rabbitmq.client.Channel;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
}
