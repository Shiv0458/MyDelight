package com.MyDelight.repository;

import com.MyDelight.entity.SmsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SmsRepository extends JpaRepository<SmsEntity, Integer> {

    @Query(name = "SELECT * FROM SMS_ENTITY WHERE ONE_TIME_PASSWORD = ?1")
    public SmsEntity getByoneTimePassword(String otp);
}
