package com.gkatzioura.hibernate.repository;

import com.gkatzioura.hibernate.enitites.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by gkatzioura on 2/11/17.
 */
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
