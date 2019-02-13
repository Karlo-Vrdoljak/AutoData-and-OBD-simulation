package com.obdsimulation.obdautodata.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;



import com.obdsimulation.obdautodata.service.ObdUserService;
import com.obdsimulation.obdautodata.domain.User;

@RestController
public class BaseController {

    @Autowired
    ObdUserService obdUserService;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(value = "/registerUser")
    public ModelAndView saveRegisteredUser(User user) {
        ModelAndView modelAndView = new ModelAndView();
        obdUserService.saveUser(user);
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @PostMapping(value = "/check", produces = MediaType.TEXT_PLAIN_VALUE)
    public String usernameCheck(@RequestParam("username") String username) {
        if (obdUserService.getUserByUsername(username) != null) {
            return "true";
        }
        return "false";
    }

    @PostMapping(value = "/checkpw", produces = MediaType.TEXT_PLAIN_VALUE)
    public String passwordCheck(@RequestParam("password") String password) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = obdUserService.getUserByUsername(auth.getName());
        if (passwordEncoder.matches(password, user.getPassword()) == true){
            return "true";
        }
        return "false";
    }

}