package com.gkatzioura.service;

import com.gkatzioura.MyThread;
import com.gkatzioura.persistence.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by gkatzioura on 4/26/17.
 */
@Service
public class AsynchronousService {

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private ApplicationContext applicationContext;

    @PersistenceContext
    private EntityManager entityManager;

    @Async
    @Transactional
    public void printEmployees() {

        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e").getResultList();
        employees.stream().forEach(e->System.out.println(e.getEmail()));
    }

    @Async("specificTaskExecutor")
    @Transactional
    public CompletableFuture<List<Employee>> fetchEmployess() {
        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e").getResultList();
        return CompletableFuture.completedFuture(employees);
    }


    public void executeAsynchronously() {

        MyThread myThread = applicationContext.getBean(MyThread.class);
        taskExecutor.execute(myThread);
    }

}
