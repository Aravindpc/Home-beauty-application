package com.ourapp.salonapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ourapp.salonapp.dto.Stylistsubdto;
import com.ourapp.salonapp.entity.Slot;
import com.ourapp.salonapp.service.StylistService;

@Controller
@RequestMapping("/user")
public class StylistController {

	@Autowired
	private StylistService stylistService;

	@GetMapping("/getAllStylists")
	public ResponseEntity<List<Stylistsubdto>> isSlotAvailable() {
		List<Stylistsubdto> output = stylistService.getAllStylists();
		return ResponseEntity.ok(output);
	}
	@PostMapping("/isAvailable")
	public ResponseEntity<String> isSlotAvailable(@RequestParam(value = "stylist") String stylist,@RequestBody Slot slotdto) {
		String output = stylistService.isSlotAvailable(slotdto.getSlotStartDateTime(), slotdto.getSlotEndDateTime(),stylist);
		return ResponseEntity.ok(output);
	}
	
	@PostMapping("/bookSlot")
	private ResponseEntity<String> saveSlot(@RequestParam(value = "stylist") String stylist,@RequestBody Slot slot) {
		String output = stylistService.bookSlot(slot,stylist);
		return ResponseEntity.ok(output);
	}
}
