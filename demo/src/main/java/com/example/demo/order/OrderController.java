package com.example.demo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping(path="{id}")
    public Order getOrderById(@PathVariable("id") Long id){
        return orderService.gerOrderById(id);
    }

    @PostMapping
    public void addNewOrder(@RequestBody Order order){
        orderService.addNewOrder(order);
    }

    @DeleteMapping(path="{id}")
    public void deleteOrder(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
    }

    @PutMapping(path="{id}")
    public void updateOrder(
            @PathVariable("id") Long id,
            @RequestParam(name="status", required = false) String status
    ){
        orderService.updateOrder(id, status);
    }
}
