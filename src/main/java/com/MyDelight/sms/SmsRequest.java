package com.MyDelight.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class SmsRequest {

    @NotBlank
    private  String phoneNumber;

    private String trailNumber;

    public @NotBlank String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTrailNumber() {
        return trailNumber;
    }

    public void setTrailNumber(String trailNumber) {
        this.trailNumber = trailNumber;
    }

    @Override
    public String toString() {
        return "SmsRequest{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
