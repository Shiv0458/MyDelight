package com.MyDelight.controller;


import java.text.ParseException;
import java.util.List;

import com.MyDelight.entity.Customers;
import com.MyDelight.repository.CustomersRepository;
import com.MyDelight.service.SmsService;
import com.MyDelight.service.UserService;
import com.MyDelight.sms.SmsRequest;
import com.MyDelight.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.MyDelight.exception.DelightException;

@RestController
public class WelcomeController {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private CustomersRepository customRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private SmsService smsService;

	@PostMapping("/login")
	public String login(@RequestBody Customers customer) throws Exception {
		List<Customers> custom = customRepository.findByPhoneNumber(customer.getPhone_number());
		String enteredPass = customer.getPassword();
		String dbPass = "";
		if (custom.size() == 1 && custom.size() < 2) {
			dbPass = custom.get(0).getPassword();
		} else {
			throw new DelightException("Invalid Phone Number Please try with Registered Phone Number...");
		}

		boolean isValid = this.bCryptPasswordEncoder.matches(enteredPass, dbPass);

		if (isValid == false) {
			throw new DelightException("Invalid PhoneNumber/password");
		}
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(customer.getPhone_number(), customer.getPassword()));
		} catch (Exception ex) {
			throw new DelightException("inavalid PhoneNumber/password");
		}
		return jwtUtil.generateToken(customer.getPhone_number());
	}

	@PostMapping("/register")
	public String registerUser(@RequestBody Customers customer) throws ParseException {
		String phLength = customer.getPhone_number();
		if(phLength==null)
			throw new DelightException("Please provide phone number...");
		String regex = "\\d{10}"; 
	    Boolean isValid =  phLength.matches(regex);
		if(!isValid)
			throw new DelightException("Please give valid Phone Number...");
		if (phLength.length() < 10 || phLength.length() > 10) {
			throw new DelightException("Invalid Phone Number Please provide valid Phone Number...");
		}
		String pass = customer.getPassword();
		if(pass == null)
			throw new DelightException("Password required...");
		if (pass.length() >= 8 && pass.length() <= 16) {
			String encodePass = this.bCryptPasswordEncoder.encode(pass);
			customer.setPassword(encodePass);
		} else {
			throw new DelightException("Please give Password Min length of 8 and Max length of 16 ...");
		}
		String phoneNumber = "+91"+phLength;
		SmsRequest smsRequest = new SmsRequest();
		smsRequest.setPhoneNumber(phoneNumber);
		String msg = userService.userRegistration(customer) ;
		if(msg.equals("You're Register Successfully with our Delight App!")) {
			smsService.sendSMS(smsRequest);
			return "You're Register Successfully with our Delight App!";
		}else {
			return msg;
		}
	}
	
}
