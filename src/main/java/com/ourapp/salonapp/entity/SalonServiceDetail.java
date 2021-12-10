package com.ourapp.salonapp.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "salonServiceDetail")
public class SalonServiceDetail  implements Serializable{

	public SalonServiceDetail(String name, String description, Long price, SalonServiceCategory salonServiceCategory,
			SalonServiceCategoryTypes salonServiceCategoryTypes, String pictureLocation) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.salonServiceCategory = salonServiceCategory;
		this.salonServiceCategoryTypes = salonServiceCategoryTypes;
		this.pictureLocation = pictureLocation;
	}
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "name", nullable = false)
    private String name;
	
	@Column(name = "description", nullable = false)
    private String description;
	
	@Column(name = "price", nullable = false)
    private Long price;
	
	@Column(name = "salon_service_category")
	@Enumerated(EnumType.STRING)
	private SalonServiceCategory salonServiceCategory;

	@Column(name = "salon_service_category_types")
	@Enumerated(EnumType.STRING)
	private SalonServiceCategoryTypes salonServiceCategoryTypes;
	
	@ManyToMany(mappedBy = "salonServiceDetail",fetch = FetchType.LAZY ,cascade = {CascadeType.ALL})
	@JsonIgnore
	private Set<Customer> customers;
	
	@Column(name = "pictureLocation", nullable = false)
    private String pictureLocation;


	public void addCustomer(Customer findByEmail) {
		this.customers.add(findByEmail);
	}
}
