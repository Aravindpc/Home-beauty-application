package com.ourapp.salonapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ourapp.salonapp.dto.Admindto;
import com.ourapp.salonapp.service.StylistService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private StylistService stylistService;

	@GetMapping("/getStylists")
	public ResponseEntity<List<Admindto>> getStylists() {
		List<Admindto> output = stylistService.getStylistDetails();
		return ResponseEntity.ok(output);
	}
	
}
