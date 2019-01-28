package com.obdsimulation.obdautodata.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

import com.obdsimulation.obdautodata.domain.User;
import com.obdsimulation.obdautodata.domain.UserRole;
import com.obdsimulation.obdautodata.message.Response;
import com.obdsimulation.obdautodata.security.ObdUserDetails;
import com.obdsimulation.obdautodata.security.ObdUserDetailsService;
import com.obdsimulation.obdautodata.service.ObdUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("/api/user")
@RestController
public class UsersController {

    @Autowired
    private ObdUserService obdUserService;

    // CREATE
    @PostMapping(value = "/save")
    public Response saveRecourse(@RequestBody User user) {
        return new Response("Done", obdUserService.saveUser(user));
    }

    // READ
    @GetMapping(value = "/all")
    public Response getAllResources() {
        return new Response("Done", obdUserService.getUsers());
    }

    @GetMapping(value = "/{username}")
    public Response getRecourse(@PathVariable String username) {
        return new Response("Done", obdUserService.getUserByUsername(username));
    }

    // UPDATE
    @PutMapping(value = "/edit/name/{name}")
    public Response editRecourseName(@PathVariable String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = obdUserService.getUserByUsername(auth.getName());
        return new Response("Done", obdUserService.updateUserName(user, name));
    }

    @PutMapping(value = "/edit/lastName/{lastName}")
    public Response editRecourseLastName(@PathVariable String lastName) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = obdUserService.getUserByUsername(auth.getName());
        return new Response("Done", obdUserService.updateUserLastName(user, lastName));
    }

    @PutMapping(value = "/edit/email/{email}")
    public Response editRecourseEmail(@PathVariable String email) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = obdUserService.getUserByUsername(auth.getName());
        return new Response("Done", obdUserService.updateUserEmail(user, email));
    }

    @PutMapping(value = "/give/admin")
    public Response giveAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = obdUserService.getUserByUsername(auth.getName());
        return new Response("Done", obdUserService.updateUserGiveAdmin(user));
    }

    @PostMapping(value = "/edit/password")
    public Response editRecoursePassword(@RequestParam("passOne") String passOne,
    @RequestParam("passTwo") String passTwo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = obdUserService.getUserByUsername(auth.getName());
        Response response = new Response();
        if(passOne.equals(passTwo) != true) {
            response.setStatus("Passwords don't match!").setData("NULL");
        }
        else{
            obdUserService.updateUserPassword(user,passOne);
            response.setStatus("Done").setData("Password changed successfully.");
        }
        return response;
    }

    // DELETE
    @DeleteMapping(value = "/delete/{username}")
    public Response deleteRecourse(@PathVariable String username) {
        User user = obdUserService.getUserByUsername(username);
        obdUserService.deleteUser(user);
        return new Response("Done", "deleted");
    }

}