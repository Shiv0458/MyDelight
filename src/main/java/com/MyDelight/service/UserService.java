package com.MyDelight.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.MyDelight.entity.Customers;
import com.MyDelight.sms.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MyDelight.exception.DelightException;
import com.MyDelight.repository.CustomersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class UserService {
	
	@Autowired
	private CustomersRepository customRepository;
	
	@Autowired
	private EmailService serviceSender;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private SmsService smsService;

	public String userRegistration(Customers customer) {
		String phoneNumber = customer.getPhone_number();
		List<Customers> custom = customRepository.findByPhoneNumber(phoneNumber);
		String gmail = customer.getMail_id();
		boolean isGmail = gmail.contains("@gmail.com");
		if(!isGmail)
			throw new DelightException("Please provide valid gmail...");
		
		List<Customers> customGmail = customRepository.getCustomerInfoByGmail(gmail);
		if(customGmail.size()!=0)
			throw new DelightException("There is already a user registered with the entered gmail....");
		
		if(custom.isEmpty()) {
			serviceSender.sendSimpleEmail(gmail, customer.getFirst_name());
			customRepository.save(customer);
			return  "You're Register Successfully with our Delight App!";
		}else {
			throw new DelightException("There is already a user registered with the entered phone Number....");
		}
		
	}
	
	public String updateUserAddress(Customers customer, String phone_number) {
		Customers cus = customRepository.getCustomerInfo(phone_number);
		if(cus!=null) {
			cus.setAddress_line1(customer.getAddress_line1());
			cus.setAddress_line2(customer.getAddress_line2());
			cus.setLandmark(customer.getLandmark());
			cus.setPincode(customer.getPincode());
		}
			Customers custom = customRepository.save(cus);
			if (custom != null) {
				return "User Address Updated successfully...";
			} else {
				return "Failed to update User Address...";
			}

	}

	public String resetPassword(Customers customer, String phoneNumber){
		Customers cus = customRepository.getCustomerInfo(phoneNumber);
		String pass = customer.getPassword();
		if(pass == null || cus==null)
			throw new DelightException("User does not exist...");
		if (pass.length() >= 8 && pass.length() <= 16) {
			String encodePass = this.bCryptPasswordEncoder.encode(pass);
			cus.setPassword(encodePass);
			Customers custom = customRepository.save(cus);
			if (custom != null)
				return "Your Password Changed Successfully...";
			else
				return "Failed to update User Password...";

		} else {
			throw new DelightException("Please give Password Min length of 8 and Max length of 16 ...");
		}

	}

	public String forgotPassword(String phoneNumber) throws ParseException {
		Customers cus = customRepository.getByPhoneNumber(phoneNumber);
		if(cus==null){
			throw new DelightException("Please provide registered mobile number...");
		}
		SmsRequest smsRequest = new SmsRequest();
		smsRequest.setPhoneNumber("+91"+phoneNumber);
		return smsService.sendSMS(smsRequest);
	}
	
}
