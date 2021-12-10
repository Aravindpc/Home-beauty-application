package com.ourapp.salonapp.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer implements Serializable{


	private static final long serialVersionUID = 1L;
	 
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @Column(name = "password", nullable = false)
	 private String password;
	 
	 @Column(name = "name", nullable = false)
	 private String name;
	 
	 @Column(name = "number", nullable = false)
	 private Long number;
	 
	 @Column(name = "address", nullable = false)
	 private String address;
	 
	 @Column(name = "email", nullable = false)
	 private String email;
	 
	 @ManyToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	 @JoinTable(
	   name = "customer_service", 
	   joinColumns = @JoinColumn(name = "customer_id"), 
	   inverseJoinColumns = @JoinColumn(name = "service_id"))
	 private List<SalonServiceDetail> salonServiceDetail;

	 @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	 @JoinTable(
		name = "customer_role",
		joinColumns = @JoinColumn(name = "customer_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	 private Set<Role> roles;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "stylist_id")
	 private Stylist stylist;
	 

	public void addSalonservice(SalonServiceDetail ss) {
		this.salonServiceDetail.add(ss);
	}	
}
