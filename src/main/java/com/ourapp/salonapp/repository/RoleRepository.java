package com.ourapp.salonapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ourapp.salonapp.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

}
