package com.ourapp.salonapp.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ourapp.salonapp.entity.Stylist;


@Repository
public interface StylistRepository extends JpaRepository<Stylist,Long> {
	@Query(value="SELECT * FROM stylist s WHERE email= :email", nativeQuery = true)
	Stylist findByEmail(@Param("email")String email);
	
	@Query(value="SELECT\n"
			+ "stylist.*,slot.* "
			+ "FROM\n"
			+ "stylist, slot, stylist_slot\n"
			+ "WHERE\n"
			+ "stylist.id = stylist_slot.stylist_id\n"
			+ "AND\n"
			+ "slot.id = stylist_slot.slot_id AND stylist.email=:email and slot.slot_start_date_time=:startDatetime AND slot.slot_end_date_time=:endDatetime", nativeQuery = true)
	Stylist isSlotAvailable(@Param("startDatetime") LocalDateTime startDateTime,
			 @Param("endDatetime") LocalDateTime endDateTime,@Param("email")String stylist);

	@Query(value="SELECT\n"
			+ "count(slot.status) "
			+ "FROM\n"
			+ "stylist, slot, stylist_slot\n"
			+ "WHERE\n"
			+ "stylist.id = stylist_slot.stylist_id\n"
			+ "AND\n"
			+ "slot.id = stylist_slot.slot_id AND stylist.email=:email and slot.status= 0", nativeQuery = true)
	Long getNotCompleted(@Param("email")String email);
	
	@Query(value="SELECT\n"
			+ "count(slot.status) "
			+ "FROM\n"
			+ "stylist, slot, stylist_slot\n"
			+ "WHERE\n"
			+ "stylist.id = stylist_slot.stylist_id\n"
			+ "AND\n"
			+ "slot.id = stylist_slot.slot_id AND stylist.email=:email and slot.status= 1", nativeQuery = true)
	Long getCompleted(@Param("email")String email);
}
