package com.gkatzioura.jobapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class IgniteKubeClusterApplication {

	public static void main(String[] args) {
		SpringApplication.run(IgniteKubeClusterApplication.class, args);
	}

}
