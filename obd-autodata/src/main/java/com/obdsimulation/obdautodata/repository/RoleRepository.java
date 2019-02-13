package com.obdsimulation.obdautodata.repository;

import com.obdsimulation.obdautodata.domain.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<UserRole, Long>{
    UserRole findByRole(String role);
    
}