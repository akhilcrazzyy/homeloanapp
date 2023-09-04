package com.demo.homeloan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.homeloan.entity.LoanApplication;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {

	
}
