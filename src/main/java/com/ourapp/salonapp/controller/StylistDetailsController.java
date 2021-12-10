package com.ourapp.salonapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ourapp.salonapp.dto.Billdto;
import com.ourapp.salonapp.dto.Slotdto;
import com.ourapp.salonapp.dto.Stylistsubdto;
import com.ourapp.salonapp.service.StylistService;

@Controller
@RequestMapping("/stylist")
public class StylistDetailsController {

	@Autowired
	private StylistService stylistService;

	@GetMapping("/getStylist")
	public ResponseEntity<Stylistsubdto> getStylist() {
		Stylistsubdto output = stylistService.getStylist();
		return ResponseEntity.ok(output);
	}
	@GetMapping("/getSlots")
	public ResponseEntity<List<Slotdto>> getSlots() {
		List<Slotdto> output = stylistService.getSlots();
		return ResponseEntity.ok(output);
	}
	
	@GetMapping("/changeStatus")
	public ResponseEntity<String> changeStatus(@RequestParam(value = "customer") Long number) {
		String output = stylistService.changeSlotStatus(number);
		return ResponseEntity.ok(output);
	}
	
	@GetMapping("/getBill")
	public ResponseEntity<List<Billdto>> getCustomerBill(@RequestParam(value = "customer") Long number) {
		
		return ResponseEntity.ok(stylistService.getCustomerBill(number));
	}
}
