package com.obdsimulation.obdautodata.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.obdsimulation.obdautodata.domain.User;
import com.obdsimulation.obdautodata.utilities.Response;
import com.obdsimulation.obdautodata.service.ObdUserService;
import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping("/api/user")
@RestController
public class UsersRestController {

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
    @PostMapping(value = "/edit/name")
    public void editRecourseName(HttpServletResponse response, @RequestParam("name") String name) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = obdUserService.getUserByUsername(auth.getName());
        obdUserService.updateUserName(user, name);
        response.sendRedirect("/profile");
    }

    @PostMapping(value = "/edit/lastName")
    public void editRecourseLastName(HttpServletResponse response, @RequestParam("lastName") String lastName)
            throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = obdUserService.getUserByUsername(auth.getName());
        obdUserService.updateUserLastName(user, lastName);
        response.sendRedirect("/profile");
    }

    @PostMapping(value = "/edit/email")
    public void editRecourseEmail(HttpServletResponse response, @RequestParam("email") String email)
            throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = obdUserService.getUserByUsername(auth.getName());
        obdUserService.updateUserEmail(user, email);
        response.sendRedirect("/profile");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/edit/role")
    public void editRole(HttpServletResponse response, @RequestParam("givenRole") String givenRole, 
    @RequestParam("username") String username) throws IOException {
        if(givenRole.equals("giveADMIN") == true) {
            obdUserService.updateUserGiveAdmin(obdUserService.getUserByUsername(username));
        }
        else if(givenRole.equals("removeADMIN") == true) {
            obdUserService.updateUserRemoveAdmin(obdUserService.getUserByUsername(username));

        }
        if (SecurityContextHolder.getContext().getAuthentication().getName().equals(username) == true) {
            response.sendRedirect("/logout"); 
        } else {
            response.sendRedirect("/admin/dashboard");
        }
        
    }

    @PostMapping(value = "/edit/password")
    public void editRecoursePassword(HttpServletResponse response, @RequestParam("passOne") String passOne,
    @RequestParam("passTwo") String passTwo)
            throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = obdUserService.getUserByUsername(auth.getName());
        obdUserService.updateUserPassword(user, passOne);
        response.sendRedirect("/profile");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit/active")
    public void editRecourseActive(HttpServletResponse response, @RequestParam("givenActive") String givenActive,
            @RequestParam("username") String username) throws IOException {
        if (givenActive.equals("ActiveOff") == true) {
            obdUserService.updateActive(username, 0);
        } else if (givenActive.equals("ActiveOn") == true) {
            obdUserService.updateActive(username, 1);
        }
        if (SecurityContextHolder.getContext().getAuthentication().getName().equals(username) == true) {
            response.sendRedirect("/logout");
        } else {
            response.sendRedirect("/admin/dashboard");
        }

    }

    // DELETE
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/delete")
    public void deleteRecourse(HttpServletResponse response, @RequestParam("username") String username) throws IOException {
        User user = obdUserService.getUserByUsername(username);
        obdUserService.deleteUser(user);
        if (SecurityContextHolder.getContext().getAuthentication().getName().equals(username) == true) {
            response.sendRedirect("/logout");
        } else {
            response.sendRedirect("/admin/dashboard");
        }
    }

}