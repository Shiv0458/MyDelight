package com.MyDelight.controller;

import com.MyDelight.entity.Orders;
import com.MyDelight.service.OrderService;
import com.MyDelight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;



}
