package com.demo.homeloan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.homeloan.entity.LoanApplication;
import com.demo.homeloan.entity.User;
import com.demo.homeloan.service.LoanApplicationService;
import com.demo.homeloan.service.UserService;

@Controller
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;
    private final UserService userService;
    
    @Autowired
    public LoanApplicationController(LoanApplicationService loanApplicationService, UserService userService) {
        this.loanApplicationService = loanApplicationService;
        this.userService = userService;
    }

    @GetMapping("/loan-application")
    public String showLoanApplicationForm(Model model) {
        return "loan-application";
    }

    @PostMapping("/loan-application")
    public String saveLoanApplication(
            @RequestParam User currentUser,
            @RequestParam String loanType,
            @RequestParam double loanAmount,
            @RequestParam int loanTenure,
            @RequestParam double interestRate,
            @RequestParam String nominee,
            Model model
    ) {
    	 LoanApplication loanApplication = new LoanApplication();
       loanApplication.setUser(currentUser);
       loanApplication.setLoanType(loanType);
       loanApplication.setLoanAmount(loanAmount);
       loanApplication.setLoanTenure(loanTenure);
       loanApplication.setInterestRate(interestRate);
       loanApplication.setNominee(nominee);
    
       loanApplicationService.saveLoanApplication(loanApplication);
        

      model.addAttribute("successMessage", "Loan application submitted successfully.");

      return "loan-application";
    }
}
