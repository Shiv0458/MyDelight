package com.MyDelight.service;



import com.MyDelight.entity.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.MyDelight.repository.CustomersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService  {//implements UserDetailsService
    /*@Autowired
    private CustomersRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customers> user = repository.findByPhoneNumber(username);
       return new org.springframework.security.core.userdetails.User(user.get(0).getPhone_number(), user.get(0).getPassword(), new ArrayList<>());

    }*/
}
