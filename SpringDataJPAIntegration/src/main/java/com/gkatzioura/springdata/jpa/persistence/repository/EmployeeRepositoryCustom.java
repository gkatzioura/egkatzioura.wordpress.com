package com.gkatzioura.springdata.jpa.persistence.repository;

import com.gkatzioura.springdata.jpa.persistence.entity.Employee;

import java.util.List;

/**
 * Created by gkatzioura on 6/3/16.
 */
public interface EmployeeRepositoryCustom {

    List<Employee> getFirstNamesLike(String firstName);

}
