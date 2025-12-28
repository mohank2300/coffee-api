package com.coffeeshop.coffee_api.kafka;

import com.coffeeshop.coffee_api.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String topic;

    public OrderEventProducer(
            KafkaTemplate<String, Object> kafkaTemplate,
            @Value("${app.kafka.topic.orders}") String topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void publishOrderPlaced(Order order) {
        kafkaTemplate.send(topic, String.valueOf(order.getOrderId()), toEvent(order, "ORDER_PLACED"));
    }

    public void publishStatusUpdated(Order order) {
        kafkaTemplate.send(topic, String.valueOf(order.getOrderId()), toEvent(order, "STATUS_UPDATED"));
    }

    private OrderEvent toEvent(Order order, String action) {
        OrderEvent e = new OrderEvent();
        e.setAction(action);
        e.setOrderId(order.getOrderId());
        e.setCoffeeId(order.getCoffeeId());
        e.setCoffeeName(order.getCoffeeName());
        e.setQuantity(order.getQuantity());
        e.setSize(order.getSize());
        e.setType(order.getType());
        e.setUnitPrice(order.getUnitPrice());
        e.setTotalPrice(order.getTotalPrice());
        e.setTax(order.getTax());
        e.setGrandTotal(order.getGrandTotal());
        e.setStatus(order.getStatus());
        e.setCreatedAt(order.getCreatedAt());
        e.setUpdatedAt(order.getUpdatedAt());
        return e;
    }
}
