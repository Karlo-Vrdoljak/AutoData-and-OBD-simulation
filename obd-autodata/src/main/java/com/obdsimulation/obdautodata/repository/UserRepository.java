package com.obdsimulation.obdautodata.repository;

import org.springframework.data.repository.CrudRepository;

import com.obdsimulation.obdautodata.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}