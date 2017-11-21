package com.baciu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EntityScan
public class StudentsLedgerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentsLedgerApplication.class, args);
	}
}