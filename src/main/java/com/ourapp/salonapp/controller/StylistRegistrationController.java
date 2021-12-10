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
import com.ourapp.salonapp.dto.Stylistdto;
import com.ourapp.salonapp.service.StylistLoginService;

@Controller
@RequestMapping("/stylistRegistration")
public class StylistRegistrationController {

	@Autowired
	private StylistLoginService stylistLoginService;
		
	@GetMapping
	public String showRegistrationForm() {
		return "stylistRegistration";
	}
	
	@PostMapping(value = "/stylistRegister")
	public ResponseEntity<String> register(@RequestBody Stylistdto stylistDto) {
		String output;
		try {
			output =stylistLoginService.createStylist(stylistDto);
			
		} catch (UserRegisteredException e) {
			
			output="Already Registered";
		}
		return new ResponseEntity<>(output,HttpStatus.OK);
	}
}