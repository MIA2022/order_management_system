package com.example.demo.order;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order gerOrderById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(()->new IllegalStateException("Order not found with ID: " + id));
    }

    public void addNewOrder(Order order){
        orderRepository.save(order);
    }

    public void deleteOrder(Long id){
        boolean exits=orderRepository.existsById(id);
        if(!exits){
            throw new IllegalStateException("Order with ID " + id + " does not exist.");
        }
        orderRepository.deleteById(id);
    }

    @Transactional
    public void updateOrder(Long id, String status ){
        Order order=orderRepository.findById(id)
                .orElseThrow(()->new IllegalStateException("Order not found with ID: " + id));
        if (status != null&&!status.isEmpty()&&!order.getStatus().equals(status)) {
            order.setStatus(status);
        }
        orderRepository.save(order);
    }
}
