package com.coffeeshop.coffee_api.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

    @KafkaListener(topics = "${app.kafka.topic.orders}", groupId = "coffee-group")
    public void listen(OrderEvent event) {
        System.out.println("✅ Kafka Event: action=" + event.getAction()
                + " orderId=" + event.getOrderId()
                + " status=" + event.getStatus()
                + " coffee=" + event.getCoffeeName()
                + " qty=" + event.getQuantity());
    }
}
