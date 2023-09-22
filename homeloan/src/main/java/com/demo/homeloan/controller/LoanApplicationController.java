//package com.demo.homeloan.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.demo.homeloan.entity.LoanApplication;
//import com.demo.homeloan.entity.User;
//import com.demo.homeloan.service.LoanApplicationService;
//import com.demo.homeloan.service.UserService;
//
//@Controller
//public class LoanApplicationController {
//
//    private final LoanApplicationService loanApplicationService;
//    private final UserService userService;
//    
//    @Autowired
//    public LoanApplicationController(LoanApplicationService loanApplicationService, UserService userService) {
//        this.loanApplicationService = loanApplicationService;
//        this.userService = userService;
//    }
//
//    @GetMapping("/loan-application")
//    public String showLoanApplicationForm(Model model) {
//        return "loan-application";
//    }
//
//    @PostMapping("/loan-application")
//    public String saveLoanApplication(
//            @RequestParam User currentUser,
//            @RequestParam String loanType,
//            @RequestParam double loanAmount,
//            @RequestParam int loanTenure,
//            @RequestParam double interestRate,
//            @RequestParam String nominee,
//            Model model
//    ) {
//    	 LoanApplication loanApplication = new LoanApplication();
//       loanApplication.setUser(currentUser);
//       loanApplication.setLoanType(loanType);
//       loanApplication.setLoanAmount(loanAmount);
//       loanApplication.setLoanTenure(loanTenure);
//       loanApplication.setInterestRate(interestRate);
//       loanApplication.setNominee(nominee);
//    
//       loanApplicationService.saveLoanApplication(loanApplication);
//        
//
//      model.addAttribute("successMessage", "Loan application submitted successfully.");
//
//      return "loan-application";
//    }
//}


package com.demo.homeloan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.demo.homeloan.entity.LoanApplication;
import com.demo.homeloan.entity.User;
import com.demo.homeloan.service.LoanApplicationService;
import com.demo.homeloan.service.UserService;

import java.util.List;

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
    public String submitLoanApplication(
            @RequestParam String registeredEmail,
            @RequestParam String loanType,
            @RequestParam double loanAmount,
            @RequestParam int loanTenure,
            @RequestParam double interestRate,
            @RequestParam String nominee,
            Model model
    ) {
        // Retrieve the user based on the registered email
        User currentUser = userService.findByEmail(registeredEmail);

        if (currentUser != null) {
            LoanApplication loanApplication = new LoanApplication();
            loanApplication.setUser(currentUser);
            loanApplication.setLoanType(loanType);
            loanApplication.setLoanAmount(loanAmount);
            loanApplication.setLoanTenure(loanTenure);
            loanApplication.setInterestRate(interestRate);
            loanApplication.setNominee(nominee);

            loanApplicationService.saveLoanApplication(loanApplication);

            model.addAttribute("successMessage", "Loan application submitted successfully.");
        } 
    else {
            model.addAttribute("errorMessage", "Invalid registered email.");
        }

        return "loan-application";
    }

    @GetMapping("/view-loans")
    public String viewLoansForm() {
        return "view-loans";
    }

    @PostMapping("/view-loans")
    public String viewLoans(
            @RequestParam String registeredEmail,
            @RequestParam String password,
            Model model
    ) {
        boolean isAuthenticated = userService.authenticateUser(registeredEmail, password);

        if (isAuthenticated) {
            User currentUser = userService.findByEmail(registeredEmail);
            List<LoanApplication> userLoans = loanApplicationService.getLoansByUser(currentUser);
            model.addAttribute("userLoans", userLoans);
            return "view-loans";
        } else {
            model.addAttribute("errorMessage", "Invalid email or password");
            return "view-loans";
        }
    }
}

