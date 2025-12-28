package com.coffeeshop.coffee_api.controller;

import com.coffeeshop.coffee_api.kafka.OrderEventProducer;
import com.coffeeshop.coffee_api.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/order-events")
public class OrderEventController {

    private final OrderEventProducer producer;

    public OrderEventController(OrderEventProducer producer) {
        this.producer = producer;
    }

    // Simple endpoint to publish a full "order placed" event
    @PostMapping("/publish")
    public void publish(@RequestBody Order order) {
        if (order.getOrderId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "orderId is required");
        }
        producer.publishOrderPlaced(order);
    }
}
