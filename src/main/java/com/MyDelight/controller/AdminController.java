package com.MyDelight.controller;

import com.MyDelight.entity.Orders;
import com.MyDelight.service.AdminService;
import com.MyDelight.service.OrderService;
import com.MyDelight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/userOrders")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/getexpecteddelivery/{expectedDate}/{status}")
    public List<Orders> getExpectedDeliveryDateDetails(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date expectedDate, @PathVariable String status) {
        return adminService.getExpectedDeliveryDetails(expectedDate, status);
    }

    @GetMapping("/todayOrders")
    public List<Orders> getTodayOrders(){
        return null;
    }
}
