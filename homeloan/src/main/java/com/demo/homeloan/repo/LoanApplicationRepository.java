package com.demo.homeloan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.homeloan.entity.LoanApplication;
import com.demo.homeloan.entity.User;

import java.util.List;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    List<LoanApplication> findByUser(User user);
}

