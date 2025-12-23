package com.coffeeshop.coffee_api.model;

import java.time.Instant;

public class Order {

    private Long orderId;
    private Long coffeeId;
    private String coffeeName;

    private String size;
    private int quantity;

    private String type; // HOT / ICED

    private double unitPrice;
    private double totalPrice;   // subtotal
    private double tax;          // tax amount
    private double grandTotal;   // subtotal + tax

    private OrderStatus status;

    private Instant createdAt;
    private Instant updatedAt;

    public Order() {}

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public Long getCoffeeId() { return coffeeId; }
    public void setCoffeeId(Long coffeeId) { this.coffeeId = coffeeId; }

    public String getCoffeeName() { return coffeeName; }
    public void setCoffeeName(String coffeeName) { this.coffeeName = coffeeName; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public double getTax() { return tax; }
    public void setTax(double tax) { this.tax = tax; }

    public double getGrandTotal() { return grandTotal; }
    public void setGrandTotal(double grandTotal) { this.grandTotal = grandTotal; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
