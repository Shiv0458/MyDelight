package com.MyDelight.config;

import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(SmsInitializer.class);
    private final TwilioConfig twilioConfig;

    @Autowired
    public SmsInitializer(TwilioConfig twilioConfig){
        this.twilioConfig=twilioConfig;
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
        //LOGGER.info("Twilio is initialized with account sid {}", twilioConfig.getAccountSid());
    }
}
