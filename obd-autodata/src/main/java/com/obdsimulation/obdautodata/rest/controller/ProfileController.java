package com.obdsimulation.obdautodata.rest.controller;

import java.util.List;

import com.obdsimulation.obdautodata.domain.User;
import com.obdsimulation.obdautodata.obd.Model;
import com.obdsimulation.obdautodata.service.ObdModelService;
import com.obdsimulation.obdautodata.service.ObdUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProfileController {

    @Autowired
    public ObdUserService obdUserService;
    
    @Autowired
    public ObdModelService obdModelService;

    @GetMapping("/profile")
    public ModelAndView showProfile(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = obdUserService.getUserByUsername(auth.getName());
        List<Model> cars = obdModelService.getVehiclesByOwner(user.getUsername());
        modelAndView.addObject("user", user);
        modelAndView.addObject("vehicles", cars);
        return modelAndView;
    }

}
