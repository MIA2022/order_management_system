package com.example.demo.order;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_sequence")
    @SequenceGenerator(name = "order_item_sequence", sequenceName = "order_item_sequence", allocationSize = 1)
    private Long id;
    private Long productId;
    @ManyToOne
    @JoinColumn(name = "orders_id", nullable = false)
    private Order order;
    private Integer quantity;
    private Double price;

    public OrderItem() {
    }

    public OrderItem(Long productId, Order order, Integer quantity, Double price) {
        this.productId = productId;
        this.order = order;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem(Long id, Long productId, Order order, Integer quantity, Double price) {
        this.id = id;
        this.productId = productId;
        this.order = order;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Order getOrder() {
        return order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }
}
