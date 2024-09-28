package com.MyDelight.controller;

import com.MyDelight.service.SmsService;
import com.MyDelight.sms.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/sms")
public class SMSController {

    @Autowired
    private SmsService service;

    @PostMapping("/send")
    public ResponseEntity<String> sendSMS(@Valid @RequestBody SmsRequest smsRequest) throws ParseException {
        return new ResponseEntity<>(service.sendSMS(smsRequest), HttpStatus.OK);
    }

    @PostMapping("/validate/{otp}")
    public String validateOTP(@PathVariable String otp) throws ParseException {
        return service.validateOTP(otp);
    }
}
