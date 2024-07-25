package com.main.AttendanceApp.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.AttendanceApp.model.Attendance;
import com.main.AttendanceApp.model.User;
import com.main.AttendanceApp.repository.AttendanceRepository;
import com.main.AttendanceApp.repository.UserRepository;

@CrossOrigin
@RestController
public class AttendanceController {
	@Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/signin")
    public Attendance signIn(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        LocalDate today = LocalDate.now();
        Optional<Attendance> optionalAttendance = attendanceRepository.findByUserAndDate(user, today);
        Attendance attendance;
        if (optionalAttendance.isPresent()) {
            attendance = optionalAttendance.get();
            attendance.getSignInTimes().add(LocalTime.now());
        } else {
            attendance = new Attendance();
            attendance.setUser(user);
            attendance.setDate(today);
            attendance.getSignInTimes().add(LocalTime.now());
        }
        return attendanceRepository.save(attendance);
    }


    @PostMapping("/signout")
    public Attendance signOut(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        LocalDate today = LocalDate.now();
        Optional<Attendance> optionalAttendance = attendanceRepository.findByUserAndDate(user, today);
        if (optionalAttendance.isPresent()) {
            Attendance attendance = optionalAttendance.get();
            attendance.getSignOutTimes().add(LocalTime.now());
            return attendanceRepository.save(attendance);
        } else {
            throw new RuntimeException("Sign-in record not found for today");
        }
    }


    @GetMapping("/report")
    public List<Attendance> getUserReport(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return attendanceRepository.findByUser(user);
    }

}
