package com.gkatzioura.springdata.jpa.persistence.repository;

import com.gkatzioura.springdata.jpa.persistence.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gkatzioura on 6/2/16.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>, EmployeeRepositoryCustom {

    List<Employee> fetchByLastNameLength(@Param("length") Long length);
}
