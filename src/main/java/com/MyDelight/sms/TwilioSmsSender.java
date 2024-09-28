package com.MyDelight.sms;

import com.MyDelight.config.TwilioConfig;
import com.MyDelight.entity.SmsEntity;
import com.MyDelight.exception.DelightException;
import com.MyDelight.repository.SmsRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service("twilio")
public class TwilioSmsSender implements SmsSender {

    @Autowired
    private TwilioConfig twilioConfig;

    @Autowired
    private SmsRepository smsRepository;


    @Override
    public String sendSMS(SmsRequest smsRequest) throws ParseException {
        SmsEntity smsEntity = new SmsEntity();
        if (isPhoneNumberisValid(smsRequest.getPhoneNumber())) {
            PhoneNumber to = new PhoneNumber("+91"+smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrailNumber());
            String otp = generateOTP();
            smsEntity.setOneTimePassword(otp);
            smsEntity.setExpireDateTime(getExpireDateTime());
            smsEntity.setGaneratedDateTime(getCurrentDateTime());
            smsEntity.setStatus("Delivered");
            smsEntity.setPhoneNumber(smsRequest.getPhoneNumber());
            String otpMessage =  "Please use OTP " + otp + " to login to your Milk account and shop from our wide array of milk products. OTP valid for 5 minutes! ";
            MessageCreator creator = Message.creator(to, from, otpMessage);
            creator.create();
            smsRepository.save(smsEntity);
            return "OTP sent Successfully";
        } else {
            throw new DelightException("Phone Number [ " + smsRequest.getPhoneNumber() + " ] is not a valid phone number");
        }

    }

    private Boolean isPhoneNumberisValid(String phoneNumber){

            return true;

    }

    private String generateOTP()  {
        return String.valueOf((int)(Math.random()*(9999-1000+1)+1000));
    }

    public String validateOTP(String otp) throws ParseException {
        SmsEntity smsEntity = smsRepository.getByoneTimePassword(otp);
        if(smsEntity!=null){
           Date expireTime =  smsEntity.getExpireDateTime();
           Date currentDateTime =  getCurrentDateTime();
           if(currentDateTime.before(expireTime) && smsEntity.getStatus()!="Validated"){
               smsEntity.setStatus("Validated");
               smsRepository.save(smsEntity);
               return "OTP validated Successfully";
           }else{
               return "OTP Invalid/Expired";
           }
        }else{
            return "Invalid OTP please provide valid OTP";
        }

    }

    public Date getExpireDateTime() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        Date date = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));
        String str = dateFormat.format(date);
        Date date1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(str);
        return date1;
    }

    public Date getCurrentDateTime() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        Date date = new Date();
        String str = dateFormat.format(date);
        Date date1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(str);
        return date1;
    }

}

