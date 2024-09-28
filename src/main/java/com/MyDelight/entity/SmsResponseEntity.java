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
}
