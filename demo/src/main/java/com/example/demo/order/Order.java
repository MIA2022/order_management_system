package com.example.demo.order;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_sequence")
    @SequenceGenerator(name = "orders_sequence", sequenceName = "orders_sequence", allocationSize = 1)
    private Long id;
    private LocalDate date;
    private String status;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(LocalDate date, String status, Customer customer) {
        this.date = date;
        this.status = status;
        this.customer = customer;
    }

    public Order(Long id, LocalDate date, String status, Customer customer) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }


    public void setStatus(String status) {
        this.status = status;
    }

}
