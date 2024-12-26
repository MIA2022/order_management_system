package com.example.demo.order;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataConfig {

    @Bean(name = "customerConfig")
    CommandLineRunner customerConfig(CustomerRepository customerRepository) {
        return args -> {
            Customer customer1 = new Customer("John Doe", "john.doe@example.com", "123 Main St", "1234567890");
            Customer customer2 = new Customer("Jane Smith", "jane.smith@example.com", "456 Elm St", "0987654321");
            customerRepository.saveAll(List.of(customer1, customer2));
            System.out.println("Saved customers: " + customerRepository.findAll());
        };
    }

    @Bean(name = "orderConfig")
    @DependsOn("customerConfig")
    CommandLineRunner orderConfig(OrderRepository orderRepository, CustomerRepository customerRepository) {
        return args -> {
            Customer customer1 = customerRepository.findAll().get(0);
            Customer customer2 = customerRepository.findAll().get(1);

            Order order1 = new Order(LocalDate.now(), "PENDING", customer1);
            Order order2 = new Order(LocalDate.now().minusDays(1), "SHIPPED", customer2);
            orderRepository.saveAll(List.of(order1, order2));
        };
    }

    @Bean(name = "orderItemConfig")
    @DependsOn({"customerConfig", "orderConfig"})
    CommandLineRunner orderItemConfig(OrderItemRepository orderItemRepository, OrderRepository orderRepository) {
        return args -> {
            Order order1 = orderRepository.findAll().get(0);
            Order order2 = orderRepository.findAll().get(1);

            OrderItem item1 = new OrderItem(101L, order1, 2, 19.99);
            OrderItem item2 = new OrderItem(102L, order1, 1, 49.99);
            OrderItem item3 = new OrderItem(103L, order2, 3, 9.99);

            orderItemRepository.saveAll(List.of(item1, item2, item3));
        };
    }



}
