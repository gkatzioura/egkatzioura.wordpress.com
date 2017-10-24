package com.gkatzioura.controller;

import com.gkatzioura.persistence.Employee;
import com.gkatzioura.persistence.EmployeeRepository;
import com.gkatzioura.service.AsynchronousService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;

/**
 * Created by gkatzioura on 4/26/17.
 */
@RestController
public class HelloController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private AsynchronousService anAsynchronousService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/")
    public String checkIt() {

        //checkMe();

        return "whatever";
    }

    @RequestMapping("/executeFunction")
    public String executeAsync() {

        anAsynchronousService.executeAsynchronously();

        return "OK";
    }

    /*
    @Async
    private void checkMe() {

        LOGGER.info("Check it");

        Employee employee = new Employee();
        employee.setEmail("gka@gks.com");
        employee.setFirstName("lala");
        employee.setLastname("Noke");

        employeeRepository.save(employee);

            employeeRepository.findAll().forEach(o->LOGGER.info(o.getEmail()));
    }
    */

}
