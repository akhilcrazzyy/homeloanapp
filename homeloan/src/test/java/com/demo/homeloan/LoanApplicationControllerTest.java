//package com.demo.homeloan;
//
//import com.demo.homeloan.controller.LoanApplicationController;
//import com.demo.homeloan.entity.LoanApplication;
//import com.demo.homeloan.entity.User;
//import com.demo.homeloan.service.LoanApplicationService;
//import com.demo.homeloan.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.ui.Model;
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class LoanApplicationControllerTest {
//
//    @Mock
//    private LoanApplicationService loanApplicationService;
//
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private LoanApplicationController loanApplicationController;
//
//    @Mock
//    private Model model;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testShowLoanApplicationForm() {
//        String result = loanApplicationController.showLoanApplicationForm(model);
//        assertEquals("loan-application", result);
//        // You can also verify model attributes here
//    }
//
//    @Test
//    public void testSaveLoanApplicationValid() {
//        // Create a User and LoanApplication object
//        User currentUser = new User();
//        String loanType = "Home Loan";
//        double loanAmount = 100000.0;
//        int loanTenure = 12;
//        double interestRate = 5.0;
//        String nominee = "John Doe";
//
//        when(model.addAttribute(eq("successMessage"), anyString())).thenReturn(model);
//
//        // Simulate a valid loan application submission
//        String result = loanApplicationController.saveLoanApplication(
//            currentUser, loanType, loanAmount, loanTenure, interestRate, nominee, model);
//
//        assertEquals("loan-application", result);
//        verify(loanApplicationService, times(1)).saveLoanApplication(any(LoanApplication.class));
//        verify(model, times(1)).addAttribute(eq("successMessage"), anyString());
//    }
//
//
//    @Test
//    public void testSaveLoanApplicationInvalidLoanAmount() {
//        // Test with an invalid loan amount (negative amount)
//        User currentUser = new User();
//        String loanType = "Home Loan";
//        double loanAmount = -100000.0; // Invalid loan amount
//        int loanTenure = 12;
//        double interestRate = 5.0;
//        String nominee = "John Doe";
//
//        String result = loanApplicationController.saveLoanApplication(
//            currentUser, loanType, loanAmount, loanTenure, interestRate, nominee, model);
//
//        assertEquals("loan-application", result);
//    }
//
//    @Test
//    public void testSaveLoanApplicationInvalidLoanTenure() {
//        // Test with an invalid loan tenure (zero tenure)
//        User currentUser = new User();
//        String loanType = "Home Loan";
//        double loanAmount = 100000.0;
//        int loanTenure = 0; // Invalid loan tenure
//        double interestRate = 5.0;
//        String nominee = "John Doe";
//
//        String result = loanApplicationController.saveLoanApplication(
//            currentUser, loanType, loanAmount, loanTenure, interestRate, nominee, model);
//
//        assertEquals("loan-application", result);
//    }
//
//    @Test
//    public void testShowLoanApplicationFormViewName() {
//        String viewName = loanApplicationController.showLoanApplicationForm(model);
//        assertEquals("loan-application", viewName);
//    }
//}
//
