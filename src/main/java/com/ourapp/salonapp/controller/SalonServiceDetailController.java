package com.ourapp.salonapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ourapp.salonapp.dto.Billdto;
import com.ourapp.salonapp.dto.SalonServiceDetaildto;
import com.ourapp.salonapp.dto.SalonServiceNamedto;
import com.ourapp.salonapp.service.SalonServiceDetailService;

@Controller
@RequestMapping("/user/salonServiceDetail")
public class SalonServiceDetailController {

	@Autowired
	private SalonServiceDetailService salonServiceDetailService;

	@GetMapping(value = "/salonServiceDetailByName/{Category}")
	public ResponseEntity<List<SalonServiceDetaildto>> getSalonServiceDetailByType(
			@RequestParam(value = "Type") String type, @PathVariable(value = "Category") String category) {
		return ResponseEntity.ok(salonServiceDetailService.findByNameType(type, category));
	}

	@PostMapping("/addServices")
	private ResponseEntity<String> addSalonServiceDetail(@RequestBody SalonServiceNamedto salonServiceNamedto) {
		return ResponseEntity.ok(salonServiceDetailService.addSalonServiceDetail(salonServiceNamedto));
	}

	@GetMapping("/getbill")
	public ResponseEntity<List<Billdto>> getBill() {

		return ResponseEntity.ok(salonServiceDetailService.getBill());
	}
}
