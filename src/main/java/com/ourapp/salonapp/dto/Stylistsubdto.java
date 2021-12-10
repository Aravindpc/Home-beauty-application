package com.ourapp.salonapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stylistsubdto {

	public Stylistsubdto(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	private String name;
	private String email;
	private Long number;
	
}