package com.coffeeshop.coffee_api.controller;

import com.coffeeshop.coffee_api.model.Order;
import com.coffeeshop.coffee_api.model.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final List<Order> orders = new ArrayList<>();
    private final AtomicLong orderIdSeq = new AtomicLong(1);

    // ✅ 10% example tax
    private static final double TAX_RATE = 0.10;

    // ✅ scheduler for auto status flow
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private final Map<Long, String> coffeeNames = Map.of(
            1L, "Espresso",
            2L, "Latte",
            3L, "Cappuccino",
            4L, "Mocha"


    );

    private final Map<Long, Double> coffeeBasePrices = Map.of(
            1L, 3.50,
            2L, 4.25,
            3L, 4.00,
            4L, 4.75
    );

    private double sizeMultiplier(String size) {
        if (size == null) return 1.0;
        return switch (size.trim().toLowerCase()) {
            case "tall" -> 1.0;
            case "grande" -> 1.2;
            case "venti" -> 1.4;
            default -> 1.0;
        };
    }

    private double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @PostMapping
    public Order placeOrder(@RequestBody Order order) {

        if (order.getQuantity() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "quantity must be >= 1");
        }

        Double basePrice = coffeeBasePrices.get(order.getCoffeeId());
        if (basePrice == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid coffeeId: " + order.getCoffeeId());
        }

        // orderId
        if (order.getOrderId() == null || order.getOrderId() == 0) {
            order.setOrderId(orderIdSeq.getAndIncrement());
        }

        // coffee name
        order.setCoffeeName(coffeeNames.get(order.getCoffeeId()));

        // type normalize
        String t = order.getType();
        if (t == null || t.isBlank()) t = "HOT";
        order.setType(t.trim().toUpperCase());

        // prices
        double unitPrice = basePrice * sizeMultiplier(order.getSize());
        double subTotal = unitPrice * order.getQuantity();
        double tax = subTotal * TAX_RATE;
        double grandTotal = subTotal + tax;

        order.setUnitPrice(round2(unitPrice));
        order.setTotalPrice(round2(subTotal));
        order.setTax(round2(tax));
        order.setGrandTotal(round2(grandTotal));

        // status + timestamps
        order.setStatus(OrderStatus.PLACED);
        Instant now = Instant.now();
        order.setCreatedAt(now);
        order.setUpdatedAt(now);

        orders.add(order);

        // ✅ AUTO FLOW SCHEDULED IN BACKEND
        long id = order.getOrderId();

        // PLACED -> PREPARING in 10s
        scheduler.schedule(() -> autoUpdateStatus(id, OrderStatus.PLACED, OrderStatus.PREPARING), 10, TimeUnit.SECONDS);

        // PREPARING -> READY in 20s (10s after preparing)
        scheduler.schedule(() -> autoUpdateStatus(id, OrderStatus.PREPARING, OrderStatus.READY), 20, TimeUnit.SECONDS);

        return order;
    }

    private void autoUpdateStatus(long orderId, OrderStatus expected, OrderStatus next) {
        try {
            synchronized (orders) {
                for (Order o : orders) {
                    if (o.getOrderId() != null && o.getOrderId() == orderId) {
                        if (o.getStatus() == expected) {
                            o.setStatus(next);
                            o.setUpdatedAt(Instant.now());
                        }
                        break;
                    }
                }
            }
        } catch (Exception ignored) {}
    }

    @GetMapping
    public List<Order> getOrders() {
        return orders;
    }

    @PatchMapping("/{orderId}/status")
    public Order updateStatus(@PathVariable long orderId, @RequestBody Map<String, String> body) {

        String statusStr = body.get("status");
        if (statusStr == null || statusStr.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "status is required");
        }

        OrderStatus newStatus;
        try {
            newStatus = OrderStatus.valueOf(statusStr.trim().toUpperCase());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status: " + statusStr);
        }

        Order order = orders.stream()
                .filter(o -> o.getOrderId() != null && o.getOrderId() == orderId)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found: " + orderId));

        order.setStatus(newStatus);
        order.setUpdatedAt(Instant.now());
        return order;
    }
}
