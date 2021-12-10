package com.ourapp.salonapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
public class Customerdto {
	

	public Customerdto(String name, String email, String address, String password) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.password = password;
	}

	private String name;
	private String email;
	private Long number;
	private String address;
	private String password;
	
}