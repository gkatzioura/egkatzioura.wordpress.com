package com.gkatzioura.bootdatabaseinitialization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by gkatzioura on 29/4/2016.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication();
        ApplicationContext applicationContext = springApplication.run(Application.class,args);
    }

}