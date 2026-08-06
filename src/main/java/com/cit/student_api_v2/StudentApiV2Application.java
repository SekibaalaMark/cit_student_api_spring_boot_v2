package com.cit.student_api_v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class StudentApiV2Application {

	public static void main(String[] args) {
		SpringApplication.run(StudentApiV2Application.class, args);
	}

}
