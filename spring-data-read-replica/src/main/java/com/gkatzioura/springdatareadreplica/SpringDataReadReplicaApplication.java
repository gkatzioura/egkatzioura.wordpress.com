package com.gkatzioura.springdatareadreplica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.gkatzioura.springdatareadreplica.config.LoadBalancedReadOnlyEntityManagerConfiguration;
import com.gkatzioura.springdatareadreplica.config.PrimaryEntityManagerConfiguration;
import com.gkatzioura.springdatareadreplica.config.ReadOnlyEntityManagerConfiguration;

@SpringBootApplication
@Import({PrimaryEntityManagerConfiguration.class,
		 LoadBalancedReadOnlyEntityManagerConfiguration.class})
public class SpringDataReadReplicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataReadReplicaApplication.class, args);
	}

}
