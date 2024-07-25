package com.main.AttendanceApp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.AttendanceApp.model.Attendance;
import com.main.AttendanceApp.model.User;
import com.main.AttendanceApp.repository.AttendanceRepository;
import com.main.AttendanceApp.repository.UserRepository;
import com.main.AttendanceApp.service.UserService;

@CrossOrigin(origins = "*" , methods ={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH} )
@RestController
public class UserController {
	 @Autowired
	    private UserService userService;
	 @Autowired
	 private AttendanceRepository attendanceRepository;
	 @Autowired
	 private UserRepository userRepository;
	 
	 
	 


	    @PostMapping("/register")
	    public String registerUser(@RequestBody User user) {
	    	AttendanceController attend = new AttendanceController();
	    	attend.signIn(null);
	        if (user != null) {
	            String message = userService.registerUser(user);
	            return message;
	        }
	        return "Something went wrong";
	    }

	    

	    @PostMapping("/login")
	    public String loginUser(@RequestParam String email, @RequestParam String password) {
	        User loggedInUser = userService.loginUser(email,password);
	        if (loggedInUser != null) {
	            if (loggedInUser.isAdmin()) {
	                return "admin";
	            } else if (userService.canSignIn(email)== false) {
	            	return "sign out";
					
				}else{
	                return "user";
	            }
	        }
	        return "try again later";
	    }

	    @GetMapping("/users")
	    public List<Map<String, Object>> getReport() {
	        return userRepository.findUsernameAndEmail();
	    }

	    @GetMapping("/userDetails")
	    public List<Attendance> getUserReport(@RequestParam String email) {
	        User user = userRepository.findByEmail(email);
	        if (user == null) {
	            throw new RuntimeException("User not found");
	        }
	        return attendanceRepository.findByUser(user);
	    }

}
