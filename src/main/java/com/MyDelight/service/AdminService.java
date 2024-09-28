package com.MyDelight.service;

import com.MyDelight.entity.Orders;
import com.MyDelight.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private OrdersRepository orderRepo;

    public List<Orders> getExpectedDeliveryDetails(Date expectedDate, String status) {
        return orderRepo.getOrdersByExpectedDeliveryDate(expectedDate, status);
    }
}
