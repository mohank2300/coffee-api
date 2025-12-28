package com.coffeeshop.coffee_api.Producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CoffeeProducer {

    private static final Logger log = LoggerFactory.getLogger(CoffeeProducer.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CoffeeProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCoffee(String topic, String key, Object message) {
        log.info("Sending coffee message -> topic={}, key={}, payload={}", topic, key, message);
        kafkaTemplate.send(topic, key, message);
    }
}
