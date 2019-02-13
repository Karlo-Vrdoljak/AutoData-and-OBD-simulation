package com.obdsimulation.obdautodata.service;


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
        //Arrays.asList(new UserRole("USER"), new UserRole("ADMIN"));
        
        if(userRepository.count() == 0){
            roleRepository.save(new UserRole("USER"));
            roleRepository.save(new UserRole("ADMIN"));
            userRepository.save(new User()
            .setEmail("asdf@gmail.com")
            .setActive(1)
            .setLastName("Vrdoljak")
            .setName("Karlo")
            .setUsername("ramiz")
            .setPassword(passwordEncoder.encode("asdfasdf"))
            );
        }
    }
}
