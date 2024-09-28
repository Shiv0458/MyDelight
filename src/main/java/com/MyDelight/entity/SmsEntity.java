package com.MyDelight.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "SMS_ENTITY")
public class SmsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String phoneNumber;
    private String oneTimePassword;
    private Date expireDateTime;
    private String status;
    private Date ganeratedDateTime;
}
