package com.coffeeshop.coffee_api.Service;

import com.coffeeshop.coffee_api.Producer.CoffeeProducer;
import com.coffeeshop.coffee_api.model.Coffee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CoffeeKafkaService {

    private static final Logger log = LoggerFactory.getLogger(CoffeeKafkaService.class);

    private final CoffeeProducer coffeeProducer;

    @Value("${app.kafka.topic.orders}")
    private String ordersTopic;

    public CoffeeKafkaService(CoffeeProducer coffeeProducer) {
        this.coffeeProducer = coffeeProducer;
    }

    public void publish(Coffee coffee) {
        // Simple JSON string representation
        String payload = String.format(
                "{\"id\":%s,\"name\":\"%s\",\"price\":%s}",
                coffee.getId() == null ? "null" : coffee.getId(),
                coffee.getName(),
                coffee.getPrice()
        );

        String key = coffee.getId() == null ? coffee.getName() : coffee.getId().toString();
        System.out.println("DEBUG: reached publishCoffeeEvent method");
        log.info("Publishing coffee event -> topic={}, key={}, payload={}", ordersTopic, key, payload);
        coffeeProducer.sendCoffee(ordersTopic, key, payload);
    }
}
