package com.MyDelight.service;

import com.MyDelight.entity.Customers;
import com.MyDelight.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Customers user = customersRepository.getByPhoneNumber(phoneNumber);
        if (user != null) {
            return new User(user.getPhone_number(), user.getPassword(), buildSimpleGrantedAuthorities(user.getUser_role()));
        } else {
            throw new UsernameNotFoundException("User not found with phoneNumber: " + phoneNumber);
        }
           // return new org.springframework.security.core.userdetails.User(user.getPhone_number(), user.getPassword(), new ArrayList<>());
        }

    private static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final String role) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}
