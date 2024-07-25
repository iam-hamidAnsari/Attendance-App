package com.main.AttendanceApp.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Attendance {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate date;
    @ElementCollection
    private List<LocalTime> signInTimes = new ArrayList<>();
    @ElementCollection
    private List<LocalTime> signOutTimes = new ArrayList<>();
    private boolean absent;


    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<LocalTime> getSignInTimes() {
		return signInTimes;
	}

	public void setSignInTimes(List<LocalTime> signInTimes) {
		this.signInTimes = signInTimes;
	}

	public List<LocalTime> getSignOutTimes() {
		return signOutTimes;
	}

	public void setSignOutTimes(List<LocalTime> signOutTimes) {
		this.signOutTimes = signOutTimes;
	}

	public boolean isAbsent() {
		return absent;
	}

	public void setAbsent(boolean absent) {
		this.absent = absent;
	}
	
	public Attendance() {}

	public Attendance(User user, LocalDate date) {
        this.user = user;
        this.date = date;
        this.absent = false;
    }

}
