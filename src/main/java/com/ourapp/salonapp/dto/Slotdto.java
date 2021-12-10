package com.ourapp.salonapp.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Slotdto {
	
	
	public Slotdto(String name, String address, Long number) {
		super();
		this.name = name;
		this.address = address;
		this.number = number;
	}
	private String name;
	private String address;
	private Long number;
	private LocalDateTime slotStartDateTime;
	private LocalDateTime slotEndDateTime;
	
}
