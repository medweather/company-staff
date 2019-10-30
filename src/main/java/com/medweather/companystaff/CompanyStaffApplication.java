package com.medweather.companystaff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CompanyStaffApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyStaffApplication.class, args);
	}

}
