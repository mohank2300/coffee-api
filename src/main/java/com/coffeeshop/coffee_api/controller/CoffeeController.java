package com.coffeeshop.coffee_api.controller;

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

