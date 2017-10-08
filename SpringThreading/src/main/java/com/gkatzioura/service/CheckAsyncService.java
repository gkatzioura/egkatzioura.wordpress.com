package com.gkatzioura.service;

import com.gkatzioura.persistence.Employee;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by gkatzioura on 4/26/17.
 */
@Service
public class CheckAsyncService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void checkAQuery() {

        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e").getResultList();

        employees.stream().forEach(e->System.out.println(e.getEmail()));
    }

}
