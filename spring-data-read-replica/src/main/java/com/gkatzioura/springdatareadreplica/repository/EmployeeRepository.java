package com.gkatzioura.springdatareadreplica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gkatzioura.springdatareadreplica.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
