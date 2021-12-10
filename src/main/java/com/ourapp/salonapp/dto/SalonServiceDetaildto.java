package com.ourapp.salonapp.dto;

import com.ourapp.salonapp.entity.SalonServiceCategory;
import com.ourapp.salonapp.entity.SalonServiceCategoryTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalonServiceDetaildto {
	
    private String name;
	
    private String description;

    private Long price;	

	private SalonServiceCategory salonServiceCategory;

	private SalonServiceCategoryTypes salonServiceCategoryTypes;
	
	private String pictureLocation;
	 
}
