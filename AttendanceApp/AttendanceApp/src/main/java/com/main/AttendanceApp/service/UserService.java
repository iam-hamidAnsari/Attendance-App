package com.main.AttendanceApp.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.AttendanceApp.model.Attendance;
import com.main.AttendanceApp.model.User;
import com.main.AttendanceApp.repository.AttendanceRepository;
import com.main.AttendanceApp.repository.UserRepository;

@Service
public class UserService {
	  @Autowired
	    private UserRepository userRepository;
	  @Autowired
	    private AttendanceRepository attendanceRepository;

	  public String registerUser(User user) {
	        if (emailExists(user.getEmail())) {
	            return "Email already in use";
	        } else {
	            userRepository.save(user);
	            return "User registered successfully";
	        }
	    }

	    public User loginUser(String username, String password) {
	        return userRepository.findByEmailAndPassword(username, password);
	    }

	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }
	    
	    public boolean emailExists(String email) {
	        // Implementation to check if the email exists in the database
	        return userRepository.findByEmail(email) != null;
	    }

	    public User getUserByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }
	    
	    public boolean canSignIn(String email) {
	        User user = userRepository.findByEmail(email);
	        if (user == null) {
	            return false; // User not found
	        }
	        
	        LocalDate today = LocalDate.now();
	        
	        // Find the latest sign-in record for today
	        Optional<Attendance> latestSignInRecord = attendanceRepository.findTopByUserAndDateOrderBySignInTimesDesc(user, today);
	        // Find the latest sign-out record for today
	        Optional<Attendance> latestSignOutRecord = attendanceRepository.findTopByUserAndDateOrderBySignOutTimesDesc(user, today);
	        
	        // If no sign-in record is present, the user can sign in
	        if (latestSignInRecord.isEmpty()) {
	            return true;
	        }
	        
	        // Get the latest sign-in time
	        LocalTime latestSignInTime = latestSignInRecord.get().getSignInTimes().stream().max(LocalTime::compareTo).orElse(null);
	        // Get the latest sign-out time
	        LocalTime latestSignOutTime = latestSignOutRecord.isPresent() 
	            ? latestSignOutRecord.get().getSignOutTimes().stream().max(LocalTime::compareTo).orElse(null)
	            : null;
	        
	        // If the latest sign-in record has no corresponding sign-out, the user cannot sign in again
	        if (latestSignOutTime == null || latestSignInTime.isAfter(latestSignOutTime)) {
	            return false;
	        }
	        
	        // If both the latest sign-in and sign-out times are present and in correct order, the user can sign in again
	        return true;
	    }







	    	
	    	
	    }
	    
