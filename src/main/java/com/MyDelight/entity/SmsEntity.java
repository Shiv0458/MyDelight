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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOneTimePassword() {
        return oneTimePassword;
    }

    public void setOneTimePassword(String oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
    }

    public Date getExpireDateTime() {
        return expireDateTime;
    }

    public void setExpireDateTime(Date expireDateTime) {
        this.expireDateTime = expireDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getGaneratedDateTime() {
        return ganeratedDateTime;
    }

    public void setGaneratedDateTime(Date ganeratedDateTime) {
        this.ganeratedDateTime = ganeratedDateTime;
    }
}
