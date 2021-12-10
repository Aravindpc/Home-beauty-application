package com.ourapp.salonapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ourapp.salonapp.entity.SalonServiceDetail;

@Repository
public interface SalonServiceDetailRepository extends JpaRepository<SalonServiceDetail, Long> {

	@Query(value="SELECT * FROM salon_service_detail WHERE salon_service_category= :category and name= :serviceName", nativeQuery = true)
	SalonServiceDetail findByCategoryandName(@Param("serviceName")String name,@Param("category")String category);
	
	@Query(value="SELECT * FROM salon_service_detail WHERE salon_service_category= :category and salon_service_category_types= :serviceType", nativeQuery = true)
	List<SalonServiceDetail> findByCategoryNameandType(@Param("serviceType")String type,@Param("category")String category);
}
