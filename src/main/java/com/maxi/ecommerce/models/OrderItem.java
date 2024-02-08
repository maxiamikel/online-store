package com.maxi.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private Double price;

    private Long productId;
    private Long OrderId;

    @Transient
    private Double subTotal;

    public Double getSubTotal() {
        if (this.quantity > 0 && this.price > 0) {
            return this.quantity * this.price;
        } else {
            return (double) 0;
        }
    }

    public OrderItem() {
        this.quantity = 0;
        this.price = (double) 0;
    }

    public OrderItem(Integer quantity, Double price, Long productId, Long orderId) {
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
        OrderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

}
