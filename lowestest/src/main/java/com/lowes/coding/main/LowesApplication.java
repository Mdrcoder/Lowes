package com.lowes.coding.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author manan
 * 
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.lowes.coding.controller", "com.lowes.coding.dto", "com.lowes.coding.service",
		"com.lowes.coding.service.impl" })
public class LowesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LowesApplication.class, args);
	}
}
