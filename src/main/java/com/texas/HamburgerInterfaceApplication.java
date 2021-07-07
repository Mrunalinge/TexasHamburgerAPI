package com.texas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.texas.*"})
@EnableJpaRepositories(basePackages = "com.texas.repository")
public class HamburgerInterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HamburgerInterfaceApplication.class, args);
	}
	

}
