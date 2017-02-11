package com.gkatzioura.hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by gkatzioura on 2/6/17.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication();
        ApplicationContext applicationContext = springApplication.run(Application.class,args);
    }

}
