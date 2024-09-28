package com.MyDelight.service;

import com.MyDelight.sms.SmsRequest;
import com.MyDelight.sms.SmsSender;
import com.MyDelight.sms.TwilioSmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.ParseException;


@Service
public class SmsService {

        private final SmsSender smsSender;

        @Autowired
        public SmsService(@Qualifier("twilio") TwilioSmsSender smsSender){
                this.smsSender=smsSender;
        }

        public String sendSMS(SmsRequest smsRequest) throws ParseException {
                return smsSender.sendSMS(smsRequest);
        }

        public String validateOTP(String otp) throws ParseException {
                return smsSender.validateOTP(otp);
        }

}
