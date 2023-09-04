package com.demo.homeloan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.homeloan.entity.LoanApplication;
import com.demo.homeloan.repo.LoanApplicationRepository;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;

    @Autowired
    public LoanApplicationServiceImpl(LoanApplicationRepository loanApplicationRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
    }

    @Override
    public void saveLoanApplication(LoanApplication loanApplication) {
        loanApplicationRepository.save(loanApplication);
    }
}
