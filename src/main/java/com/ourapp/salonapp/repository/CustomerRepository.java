package com.ourapp.salonapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ourapp.salonapp.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
	@Query(value="SELECT * FROM customer s WHERE email= :email", nativeQuery = true)
	Customer findByEmail(@Param("email")String email);
	
	@Query(value="SELECT * FROM customer s WHERE number= :number", nativeQuery = true)
	Customer findByNumber(@Param("number")Long number);
}
