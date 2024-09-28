package com.MyDelight.controller;

import com.MyDelight.entity.Customers;
import com.MyDelight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MyDelight.exception.DelightException;

import java.text.ParseException;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/addAddress/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String updateUserAddress(@RequestBody Customers customer, @PathVariable String phoneNumber) {
        if (customer.getAddress_line1() == null)
            throw new DelightException("Address Line1 is Required....");
        if (customer.getLandmark() == null)
            throw new DelightException("LandMark is required...");
        return userService.updateUserAddress(customer, phoneNumber);
    }

    @PutMapping("/reset/password/{phoneNumber}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String resetUserPassword(@RequestBody Customers customer, @PathVariable String phoneNumber){
        return userService.resetPassword(customer, phoneNumber);
    }

    @PutMapping("/forgot/password/{phoneNumber}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String forgotPassword(@PathVariable String phoneNumber) throws ParseException {
        return userService.forgotPassword(phoneNumber);
    }

}
