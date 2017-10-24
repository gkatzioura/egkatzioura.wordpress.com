package com.gkatzioura.controller;

import com.gkatzioura.persistence.Employee;
import com.gkatzioura.persistence.EmployeeRepository;
import com.gkatzioura.service.AsynchronousService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


    @RequestMapping("/runTask")
    public String executeAsync() {

        anAsynchronousService.executeAsynchronously();

        return "OK";
    }

    @RequestMapping("/employees")
    public List<Employee> employees() throws Exception {

        return anAsynchronousService.fetchEmployess().get();
    }

    @RequestMapping(value = "/employee",method = RequestMethod.POST)
    public void add(@RequestBody Employee employee) {

        employeeRepository.save(employee);
    }

}
