package com.ourapp.salonapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ourapp.salonapp.Exception.UserRegisteredException;
import com.ourapp.salonapp.dto.Customerdto;
import com.ourapp.salonapp.service.CustomerLoginService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private CustomerLoginService customerLoginService;
		
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<String> register(@RequestBody Customerdto registrationDto) {
		String output;
		try {
			output = customerLoginService.createCustomer(registrationDto);
			
		} catch (UserRegisteredException e) {
			
			output="Already Registered";
		}
		return new ResponseEntity<>(output,HttpStatus.OK);
	}
}