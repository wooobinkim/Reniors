package com.common.jmark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JmarkApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmarkApplication.class, args);
	}

}
