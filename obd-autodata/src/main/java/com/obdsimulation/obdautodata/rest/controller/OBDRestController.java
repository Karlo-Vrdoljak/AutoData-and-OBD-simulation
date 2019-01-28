package com.obdsimulation.obdautodata.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class OBDRestController {

    /*@Autowired
    baseclasstype baseclass;
    */

    @RequestMapping(value = "/obd/send", method =  RequestMethod.POST)
    public String index(/*@RequestBody MQTTMessage mqttMessage*/) {
        return "Publish success!";
      }
}