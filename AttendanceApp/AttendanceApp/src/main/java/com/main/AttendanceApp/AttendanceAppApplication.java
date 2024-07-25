package com.main.AttendanceApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.main.AttendanceApp.controller.UserController;

@SpringBootApplication
public class AttendanceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceAppApplication.class, args);
		UserController user = new UserController();
		user.userRe
	}

}
