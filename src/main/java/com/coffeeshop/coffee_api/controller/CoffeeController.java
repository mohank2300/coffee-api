package com.coffeeshop.coffee_api.controller;

<<<<<<< Updated upstream
import com.coffeeshop.coffee_api.model.Coffee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    @GetMapping
    public List<Coffee> getCoffees() {
        return List.of(
                new Coffee(1L, "Espresso", 3.50),
                new Coffee(2L, "Latte", 4.25),
                new Coffee(3L, "Cappuccino", 4.20),
                new Coffee(4L, "Mocha", 4.75),
                new Coffee(5L, "Macchiato", 4.50),
                new Coffee(6L, "Flat White", 5.75),
                new Coffee(7L, "Cortado", 4.75),
                new Coffee(8L, "Espresso Shot", 3.75),
                new Coffee(9L, "Americano", 3.55)


        );
    }
}

=======
import com.coffeeshop.coffee_api.Service.CoffeeKafkaService;
import com.coffeeshop.coffee_api.model.Coffee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coffees")
public class CoffeeController {

    private static final Logger log = LoggerFactory.getLogger(CoffeeController.class);

    private final CoffeeKafkaService coffeeKafkaService;

    public CoffeeController(CoffeeKafkaService coffeeKafkaService) {
        this.coffeeKafkaService = coffeeKafkaService;
    }

    // Simple endpoint to publish a coffee event to Kafka
    @PostMapping("/publish")
    public ResponseEntity<Void> publish(@RequestBody Coffee coffee) {
        log.info("REST request: publish coffee -> {}", coffee);
        coffeeKafkaService.publish(coffee);
        return ResponseEntity.accepted().build();
    }

    // Optional health check endpoint
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("coffee controller is up");
    }
}
>>>>>>> Stashed changes
