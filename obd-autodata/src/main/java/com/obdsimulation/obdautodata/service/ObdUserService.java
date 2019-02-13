package com.obdsimulation.obdautodata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.obdsimulation.obdautodata.domain.User;
import com.obdsimulation.obdautodata.domain.UserRole;
import com.obdsimulation.obdautodata.repository.RoleRepository;
import com.obdsimulation.obdautodata.repository.UserRepository;

@Service
@Transactional
public class ObdUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    // CREATE
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
        user.setActive(0);
        return userRepository.save(user);
    }

    // READ
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(long user_id) {
        return userRepository.findById(user_id);
    }

    public UserRole getAdminRole(){
        return roleRepository.findByRole("ADMIN");
    }
    
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // UPDATE
    public User updateUserName(User user, String name) {
        user.setName(name);
        return userRepository.save(user);
    }

    public User updateUserLastName(User user, String lastName) {
        user.setLastName(lastName);
        return userRepository.save(user);
    }
    
    public User updateUserEmail(User user, String email) {
        user.setEmail(email);
        return userRepository.save(user);
    }
    
    public User updateUserPassword(User user, String rawPassword) {
        user.setPassword(passwordEncoder.encode(rawPassword));
        return userRepository.save(user);
    }
    
    public User updateUserGiveAdmin(User user) {
        List<UserRole> roles = user.getRoles();
        roles.add(roleRepository.findByRole("ADMIN"));
        user.setRoles(roles);
        return userRepository.save(user);
    }
    public User updateUserRemoveAdmin(User user) {
        List<UserRole> roles = user.getRoles();
        UserRole adminRole = roleRepository.findByRole("ADMIN");
        roles.remove(adminRole);
        user.setRoles(roles);
        return userRepository.save(user);
    }
    
    public User updateActive(String username, int active) {
        User user = this.getUserByUsername(username);
        user.setActive(active);
        return userRepository.save(user);
    }

    // DELETE
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}