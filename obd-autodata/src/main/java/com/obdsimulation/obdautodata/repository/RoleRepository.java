package com.obdsimulation.obdautodata.repository;

import com.obdsimulation.obdautodata.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface RoleRepository extends CrudRepository<UserRole, Long>{
    UserRole findByRole(String role);
    
}