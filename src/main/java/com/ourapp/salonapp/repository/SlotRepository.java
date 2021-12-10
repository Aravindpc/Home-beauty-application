package com.ourapp.salonapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ourapp.salonapp.entity.Slot;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

    @Query(value="SELECT * FROM slot WHERE slot.customer_id=:customerid", nativeQuery = true)
	Slot getbyCustomerId(@Param("customerid") Long customerid);
}
