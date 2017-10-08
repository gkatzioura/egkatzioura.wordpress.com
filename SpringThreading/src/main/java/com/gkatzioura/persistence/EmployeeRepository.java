package com.gkatzioura.persistence;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by gkatzioura on 4/26/17.
 */
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
}
