package com.coffeeshop.coffee_api.kafka;

import com.coffeeshop.coffee_api.model.OrderStatus;

import java.time.Instant;

public class OrderEvent {

    private Long orderId;
    private String action;      // ORDER_PLACED, STATUS_UPDATED
    private Long coffeeId;
    private String coffeeName;
    private Integer quantity;
    private String size;
    private String type;
    private Double unitPrice;
    private Double totalPrice;
    private Double tax;
    private Double grandTotal;
    private OrderStatus status;
    private Instant createdAt;
    private Instant updatedAt;

    public OrderEvent() {}

    public Long getOrderId() { return orderId; }

    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public String getAction() { return action; }

    public void setAction(String action) { this.action = action; }

    public Long getCoffeeId() { return coffeeId; }

    public void setCoffeeId(Long coffeeId) { this.coffeeId = coffeeId; }

    public String getCoffeeName() { return coffeeName; }

    public void setCoffeeName(String coffeeName) { this.coffeeName = coffeeName; }

    public Integer getQuantity() { return quantity; }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getSize() { return size; }

    public void setSize(String size) { this.size = size; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public Double getUnitPrice() { return unitPrice; }

    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }

    public Double getTotalPrice() { return totalPrice; }

    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public Double getTax() { return tax; }

    public void setTax(Double tax) { this.tax = tax; }

    public Double getGrandTotal() { return grandTotal; }

    public void setGrandTotal(Double grandTotal) { this.grandTotal = grandTotal; }

    public OrderStatus getStatus() { return status; }

    public void setStatus(OrderStatus status) { this.status = status; }

    public Instant getCreatedAt() { return createdAt; }

    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
