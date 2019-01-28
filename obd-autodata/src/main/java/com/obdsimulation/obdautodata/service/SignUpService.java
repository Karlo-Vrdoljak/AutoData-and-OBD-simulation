package com.obdsimulation.obdautodata.service;

import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.obdsimulation.obdautodata.domain.User;
import com.obdsimulation.obdautodata.domain.UserRole;
import com.obdsimulation.obdautodata.repository.RoleRepository;
import com.obdsimulation.obdautodata.repository.UserRepository;

@Service
@Transactional
public class SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    private void setupDefaultUser(){
        // Arrays.asList(new UserRole("USER"), new UserRole("ADMIN"))
        if(userRepository.count() == 0){
            /*
             * UserRole userRole = roleRepository.findByRole("USER"); UserRole adminRole =
             * roleRepository.findByRole("ADMIN");
             */
            userRepository.save(new User()
            .setEmail("asdf@gmail.com")
            .setActive(1)
            .setLastname("Vrdoljak")
            .setName("Karlo")
            .setUsername("ramiz")
            .setPassword(passwordEncoder.encode("asdfasdf"))
            .setRoles(Arrays.asList(new UserRole("USER"), new UserRole("ADMIN")))
            );
        }
    }
}
