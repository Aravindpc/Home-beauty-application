package com.ourapp.salonapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ourapp.salonapp.entity.Customer;
import com.ourapp.salonapp.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	    
    public Customer findByEmail(String userName) {
		return customerRepository.findByEmail(userName);
	}
    
    public Customer findByNumber(Long number) {
		return customerRepository.findByNumber(number);
	}
}
