package com.gkatzioura.spring.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gkatzioura on 9/2/16.
 */
@RestController
public class GreetController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetController.class);

    @RequestMapping(path = "/public",method = RequestMethod.GET)
    public String sayFreeHi() {
        return "Greeting";
    }

    @RequestMapping(path = "/secured",method = RequestMethod.GET)
    public String saySecureHi() {
        return "Secured";
    }

}
