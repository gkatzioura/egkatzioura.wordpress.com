package com.gkatzioura.employee.controller;

import java.util.List;

import com.gkatzioura.employee.model.Employee;
import com.gkatzioura.employee.repository.EmployeeRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeContoller {

	private final EmployeeRepository employeeRepository;

	public EmployeeContoller(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@RequestMapping("/employee")
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

}
