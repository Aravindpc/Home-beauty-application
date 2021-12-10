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
import javax.persistence.OneToMany;
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
@Table(name = "stylist")
public class Stylist implements Serializable{

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
	 
	 @Column(name = "email", nullable = false)
	 private String email;
	 
	 @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.ALL})
	 @JoinTable(
	   name = "stylist_slot", 
	   joinColumns = @JoinColumn(name = "stylist_id"), 
	   inverseJoinColumns = @JoinColumn(name = "slot_id"))
	 private List<Slot> slot;

	 @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	 @JoinTable(
		name = "stylist_role",
		joinColumns = @JoinColumn(name = "stylist_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	 private Set<Role> roles;
	 
	 @OneToMany(mappedBy = "stylist", fetch = FetchType.EAGER,
	            cascade = CascadeType.ALL)
	 private Set<Customer> customers;

	
	public void addSlot(Slot slot)
	{
		this.slot.add(slot);
	}

	public void addCustomer(Customer customer) {
		this.customers.add(customer);		
	}
}
