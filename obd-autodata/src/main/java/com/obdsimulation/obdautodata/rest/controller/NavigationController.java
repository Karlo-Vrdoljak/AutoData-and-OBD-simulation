package com.obdsimulation.obdautodata.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


import com.obdsimulation.obdautodata.obd.Model;
import com.obdsimulation.obdautodata.obd.ObdReaderError;
import com.obdsimulation.obdautodata.obd.Statistics;
import com.obdsimulation.obdautodata.service.ObdModelService;
import com.obdsimulation.obdautodata.service.ObdUserService;
import com.obdsimulation.obdautodata.utilities.CarDatalist;
import com.obdsimulation.obdautodata.domain.User;
import com.obdsimulation.obdautodata.domain.UserRole;

@RestController
public class NavigationController {

    @Autowired
    ObdUserService obdUserService;

    @Autowired
    ObdModelService obdModelService;

    @GetMapping(value = { "/", "/home" })
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @GetMapping(value = "/about")
    public ModelAndView showAbout() {
        return new ModelAndView("about");
    }

    @GetMapping(value = "/contact")
    public ModelAndView showContact() {
        return new ModelAndView("contact");
    }

    @GetMapping(value = "admin/dashboard")
    public ModelAndView showDashboard() {
        ModelAndView modelAndView = new ModelAndView();
        List<Model> allVehicles = obdModelService.getAllVehiclesSortedByOwner();
        Iterable<User> iterable_users = obdUserService.getUsers();
        List<User> users = new ArrayList<>();
        iterable_users.forEach(users::add);

        Statistics stats = generateStats();
        modelAndView.addObject("listOfUsers", users);
        modelAndView.addObject("stats", stats);
        modelAndView.addObject("vehicles", allVehicles);
        modelAndView.setViewName("/admin/dashboard");
        return modelAndView;
    }

    @PostMapping(value = "/admin/edituser")
    public ModelAndView editUser(@RequestParam("username") String username) {
        ModelAndView modelAndView = new ModelAndView();
        List<Model> cars = obdModelService.getVehiclesByOwner(username);
        User user = obdUserService.getUserByUsername(username);
        modelAndView.addObject("user", user);
        List<String> roles = new ArrayList<>();
        for (UserRole role : user.getRoles()) {
            roles.add(role.getRole());
        }
        modelAndView.addObject("roles", roles);
        modelAndView.addObject("vehicles", cars);
        modelAndView.setViewName("admin/edituser");
        return modelAndView;
    }

    @GetMapping(value = "/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping(value = "/register_vehicle")
    public ModelAndView registerVehicle() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registervehicle");
        Model car = new Model();
        modelAndView.addObject("car", car);
        List<CarDatalist> datalist = obdModelService.getCardatalistwrapper().getCardatalist();
        modelAndView.addObject("vehicles", datalist);
        return modelAndView;
    }


/*******************************************
 *  STATISTICS
 *******************************************/
    
    public String calculateRepairTime(List<Model> cars){
        double time = 0;
        double days = time / 86400;
        double hours = time % 86400 / 3600;
        double minutes = (time % 3600) / 60;
        String repair = "";
        if (Math.floor(days) >= 1) {
            if (Math.floor(days) == 1) {
                repair += (int) Math.floor(days) + " day ";
            } else {
                repair += (int) Math.floor(days) + " days ";
            }
        }
        if (Math.floor(days) >= 1 && Math.floor(hours) >= 1) {
            repair += "& ";
        }
        if (Math.floor(hours) >= 1) {
                if (Math.floor(hours) == 1) {
                    repair += (int)Math.floor(hours) + " hour ";
                }
                else {
                    repair += (int)Math.floor(hours) + " hours ";
                }
            }
            if (Math.floor(hours) >= 1 && Math.floor(minutes) >= 1) {
                repair += "& ";
            }
            if (Math.floor(minutes) >= 1) {
                if (Math.floor(minutes) == 1) {
                    repair += (int)Math.floor(minutes) + " minute";
                } else {
                    repair += (int)Math.floor(minutes) + " minutes";
                }
            }

            return repair;
    }

    public String calculateCosts(List<Model> cars){
        double time = 0;
        for (Model car : cars) {
            List<ObdReaderError> errors = car.getErrors();
            for (ObdReaderError err : errors) {
                time += err.getRepairTime();
                
            }

        }
        double cost = (time / 3600) * 150;
        NumberFormat formatter = new DecimalFormat("#0.00");
        String totalCost = formatter.format(cost) + " HRK";
        return totalCost;

    }

    public Statistics generateStats() {
        Iterable<User> iterable_users = obdUserService.getUsers();
        List<User> users = new ArrayList<>();
        iterable_users.forEach(users::add);
        List<Model> cars = obdModelService.getAllVehiclesSortedByOwner();
        Statistics stats = new Statistics();
        int num_admins = 0;
        int num_errors = 0;
        for (User user : users) {
            if(user.getRoles().contains(obdUserService.getAdminRole()) == true){
                num_admins+=1;
            }
        }
        for (Model car : cars){
            num_errors += car.getErrors().size();
        }
        stats.setUsers(users.size())
             .setAdmins(num_admins)
             .setErrors(num_errors)
             .setVehicles(cars.size())
             .setRepairs(calculateRepairTime(cars))
             .setCosts(calculateCosts(cars));
            ;
        return stats;
    }
}
