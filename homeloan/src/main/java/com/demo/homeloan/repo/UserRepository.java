package com.demo.homeloan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.homeloan.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
