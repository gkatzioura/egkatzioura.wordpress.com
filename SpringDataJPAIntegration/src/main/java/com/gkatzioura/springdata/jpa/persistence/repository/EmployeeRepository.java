package com.gkatzioura.springdata.jpa.persistence.repository;

import com.gkatzioura.springdata.jpa.persistence.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by gkatzioura on 6/2/16.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>, EmployeeRepositoryCustom {
}
