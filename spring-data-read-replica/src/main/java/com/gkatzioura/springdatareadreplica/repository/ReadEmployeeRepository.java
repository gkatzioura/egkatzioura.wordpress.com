package com.gkatzioura.springdatareadreplica.repository;


import java.util.List;

import org.springframework.data.repository.Repository;

import com.gkatzioura.springdatareadreplica.config.ReadOnlyRepository;
import com.gkatzioura.springdatareadreplica.entity.Employee;

/**
 * This is a read only repository
 */
@ReadOnlyRepository
public interface ReadEmployeeRepository extends Repository<Employee,Long> {

    List<Employee> findAll();

}
