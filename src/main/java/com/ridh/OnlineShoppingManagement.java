package com.ridh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnlineShoppingManagement {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShoppingManagement.class, args);
	}

}
