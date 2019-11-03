package com.gkatzioura.springdatareadreplica.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gkatzioura.springdatareadreplica.entity.Employee;
import com.gkatzioura.springdatareadreplica.repository.EmployeeRepository;
import com.gkatzioura.springdatareadreplica.repository.ReadEmployeeRepository;

@RestController
public class EmployeeContoller {

    private final EmployeeRepository employeeRepository;
    private final ReadEmployeeRepository readEmployeeRepository;

    public EmployeeContoller(EmployeeRepository employeeRepository,
                             ReadEmployeeRepository readEmployeeRepository) {
        this.employeeRepository = employeeRepository;
        this.readEmployeeRepository = readEmployeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employee/read")
    public List<Employee> getEmployeesRead() {
        return readEmployeeRepository.findAll();
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody Employee employee) {
        employeeRepository.save(employee);
    }


}
