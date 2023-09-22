package com.demo.homeloan.service;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.homeloan.entity.LoanApplication;
import com.demo.homeloan.entity.User;
import com.demo.homeloan.repo.LoanApplicationRepository;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;

    @Autowired
    public LoanApplicationServiceImpl(LoanApplicationRepository loanApplicationRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
    }  
    @Override
    public List<LoanApplication> getLoansByUser(User user) {
        return loanApplicationRepository.findByUser(user);
    }

    @Override
    public void saveLoanApplication(LoanApplication loanApplication) {
        loanApplicationRepository.save(loanApplication);
    }
    
    @Override
    public List<LoanApplication> getAllLoans() {
        return loanApplicationRepository.findAll();
    }
}
