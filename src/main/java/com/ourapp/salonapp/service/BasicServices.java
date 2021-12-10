package com.ourapp.salonapp.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class BasicServices {
	
	public String getCurrentUser() {
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username;
	if (principal instanceof UserDetails) {
	 username = ((UserDetails)principal).getUsername();
	} else {
	 username = principal.toString();
	}
	return username;
	}
}
