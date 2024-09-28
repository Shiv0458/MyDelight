package com.MyDelight.sms;

import java.text.ParseException;

public interface SmsSender {

    String sendSMS(SmsRequest smsRequest) throws ParseException;

    String validateOTP(String otp) throws ParseException;
}
