package com.albanero.customermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.albanero.customermicroservice.entity")
public class CustomerManagementMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerManagementMicroserviceApplication.class, args);
	}

}
