package com.main.AttendanceApp.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.AttendanceApp.model.Attendance;
import com.main.AttendanceApp.model.User;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>{
	Optional<Attendance> findByUserAndDate(User user, LocalDate date);

	List<Attendance> findByUser(User user);

	Optional<Attendance> findByUserAndDateAndSignInTimesNotNull(User user, LocalDate today);

	Optional<Attendance> findByUserAndDateAndSignOutTimesNotNull(User user, LocalDate today);

	Optional<Attendance> findTopByUserAndDateOrderBySignInTimesDesc(User user, LocalDate today);

	Optional<Attendance> findTopByUserAndDateOrderBySignOutTimesDesc(User user, LocalDate today);
}
