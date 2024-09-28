package com.MyDelight.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("twilio")
@Data
public class TwilioConfig {

    private String accountSid;
    private String authToken;
    private String trailNumber;

}
