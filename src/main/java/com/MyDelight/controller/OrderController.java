package com.MyDelight.controller;

import com.MyDelight.entity.Customers;
import com.MyDelight.entity.Orders;
import com.MyDelight.entity.Products;
import com.MyDelight.exception.DelightException;
import com.MyDelight.repository.CustomersRepository;
import com.MyDelight.repository.OrdersRepository;
import com.MyDelight.repository.ProductRepository;
import com.MyDelight.service.OrderService;
import com.MyDelight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private ProductRepository productRepository;


    @PostMapping("/placeOrder")
    public String getPlaceOrder(@RequestBody Orders orders) throws ParseException {

        Customers customer = customersRepository.findById(orders.getCustomer().getId()).get();
        orders.setCustomer(customer);
        try {
            Products product = productRepository.findById(orders.getProduct().getProduct_id()).get();
            orders.setProduct(product);
        } catch (Exception e) {
            throw new DelightException("Inavlid product Id...");
        }

        if (orders.getOrder_end_date() == null) {
            orders.setOrder_end_date(orders.getOrder_start_date());
        }
        return orderService.addOrder(orders);
    }

    @PutMapping("/updateOrder/{order_id}")
    public Orders getUpdateOrder(@RequestBody Orders orders, @PathVariable String order_id) throws ParseException {
        String str = ordersRepository.getOrdersId(order_id);
        if (str != null) {
            return orderService.updateOrderDeails(orders, order_id);
        } else {
            throw new DelightException("Invalid Order Id please provide valid Order Id...");
        }

    }

    @PutMapping("/cancelOrder/{order_id}")
    public String getCancelOrder(@PathVariable String order_id) throws ParseException {
        String str = ordersRepository.getOrdersId(order_id);
        if (str != null) {
            return orderService.cancelOrder(order_id);
        } else {
            throw new DelightException("Invalid Order Id please provide valid Order Id...");
        }
    }

    @GetMapping("/myOrders/{customer}")
    public List<Orders> getOrdersDetails(@PathVariable Customers customer) throws SQLException {

        return ordersRepository.getOrdersByCustomer(customer);
    }

    @GetMapping("/myOldOrders/{customer}")
    public List<Orders> getOldOrders(@PathVariable Customers customer) {
        return orderService.getOldOrders(customer);
    }

    @GetMapping("/myUpcomingOrders/{customer}")
    public List<Orders> getUpcomingOrders(@PathVariable Customers customer) {
        return orderService.getUpcomingOrders(customer);
    }

    @GetMapping("/myCancelledOrders/{customer}")
    public List<Orders> getCancelledOrders(@PathVariable Customers customer) {
        return orderService.getCancelledOrders(customer);
    }

    @GetMapping("/byOrderId/{orderId}")
    public Orders getOrderDetails(@PathVariable String orderId) {
        return orderService.getOrderDetails(orderId);
    }
}
