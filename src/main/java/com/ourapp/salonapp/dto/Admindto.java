package com.ourapp.salonapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admindto {

	private String name;
	private Long number;
	private Long completed;
	private Long notcompleted;
	
}
