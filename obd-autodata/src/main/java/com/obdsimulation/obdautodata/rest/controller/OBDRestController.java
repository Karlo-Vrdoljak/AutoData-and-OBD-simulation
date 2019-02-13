package com.obdsimulation.obdautodata.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.obdsimulation.obdautodata.service.ObdModelService;
import com.obdsimulation.obdautodata.service.ObdUserService;
import com.obdsimulation.obdautodata.domain.User;
import com.obdsimulation.obdautodata.obd.Model;

@RestController
public class OBDRestController {

    @Autowired
    ObdUserService obdUserService;

    @Autowired
    ObdModelService obdModelService;

    @PostMapping(value ="/register_vehicle")
    public ModelAndView saveVehicle(Model vehicle) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = obdUserService.getUserByUsername(auth.getName());
        obdModelService.saveVehicle(vehicle,user.getUsername());
        modelAndView.addObject("successCar","Well done!");
        modelAndView.setViewName("home");
        return modelAndView;
        
    }

    @PostMapping(value = "/show_errors")
    public ModelAndView showErrors(@RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Model vehicle = obdModelService.getModelByID(id).get();
        modelAndView.addObject("errors",vehicle.getErrors());
        modelAndView.addObject("veh",vehicle);
        modelAndView.setViewName("showerrors");
        return modelAndView;
    }
    
    @PostMapping(value="/admin/vehicle_details")
    public ModelAndView showOneVehicle(@RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Model vehicle = obdModelService.getModelByID(id).get();
        modelAndView.addObject("veh", vehicle);
        modelAndView.setViewName("admin/onevehicle");
        return modelAndView;
    }
    
    @PostMapping(value="/delete_vehicle")
    public void deleteOneVehicle(HttpServletResponse response, @RequestParam("id") Long id) throws IOException{
        Model vehicle = obdModelService.getModelByID(id).get();
        obdModelService.deleteVehicle(vehicle);
        response.sendRedirect("/profile");
    }

/*
    @PostMapping(value = "/temp")
    public ModelAndView login_process(@RequestParam("username") String username,
        @RequestParam("password") String password) throws IOException {
        String token = this.getOauth2Token(username, password);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("temp");
        modelAndView.addObject("token", token);
        return modelAndView;

    }
*/


/*
    public String getOauth2Token(String username, String password) throws IOException {
        String token = null;
        String url = "http://localhost:8080/oauth/token";
        token = Request
                .Post(url).setHeader("Authorization", "Basic Y2xpZW50OnNlY3JldA==")
                .bodyForm(Form.form()
                .add("username", username).add("password", password)
                .add("grant_type", "password").build())
                .execute().returnContent().asString();
        token = token.substring(token.indexOf(":\"") + 2, token.indexOf("\","));
        return token;
    }
*/
}