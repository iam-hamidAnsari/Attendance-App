package com.main.AttendanceApp.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.main.AttendanceApp.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmailAndPassword(String email, String password);

	User findByEmail(String email);

	User findByUsername(String username);
	
	  @Query(value = "SELECT id,username, email,phone_number  FROM app_user WHERE is_admin = false", nativeQuery = true)
	    List<Map<String, Object>> findUsernameAndEmail();

}
