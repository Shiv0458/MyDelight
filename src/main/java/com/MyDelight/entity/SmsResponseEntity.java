package com.MyDelight.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsResponseEntity {

    private String message;
    private OtpStatus status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OtpStatus getStatus() {
        return status;
    }

    public void setStatus(OtpStatus status) {
        this.status = status;
    }
}
