package com.demo.homeloan.service;

import java.util.List;

import com.demo.homeloan.entity.LoanApplication;
import com.demo.homeloan.entity.User;

public interface LoanApplicationService {
	
//	void saveLoanApplication(User user, String loanType, double loanAmount, int loanTenure, double interestRate, String nominee);
    
    List<LoanApplication> getLoansByUser(User user);
    
    List<LoanApplication> getAllLoans();

	void saveLoanApplication(LoanApplication loanApplication);
}
