package com.demo.homeloan.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoanApplicationTest {

    @Test
    public void testLoanApplicationConstructor() {
        // Create a User instance for testing
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setAdmin(false);

        // Create a LoanApplication instance
        LoanApplication loanApplication = new LoanApplication(user, "Home Loan", 100000.0, 12, 5.0, "John Doe");

        // Check if the constructor sets values correctly
        assertEquals(user, loanApplication.getUser());
        assertEquals("Home Loan", loanApplication.getLoanType());
        assertEquals(100000.0, loanApplication.getLoanAmount());
        assertEquals(12, loanApplication.getLoanTenure());
        assertEquals(5.0, loanApplication.getInterestRate());
        assertEquals("John Doe", loanApplication.getNominee());
    }

    @Test
    public void testLoanApplicationGetterAndSetter() {
        // Create a LoanApplication instance
        LoanApplication loanApplication = new LoanApplication();

        // Set values using setters
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setAdmin(false);
        loanApplication.setUser(user);
        loanApplication.setLoanType("Home Loan");
        loanApplication.setLoanAmount(100000.0);
        loanApplication.setLoanTenure(12);
        loanApplication.setInterestRate(5.0);
        loanApplication.setNominee("John Doe");

        // Check if getters return the expected values
        assertEquals(user, loanApplication.getUser());
        assertEquals("Home Loan", loanApplication.getLoanType());
        assertEquals(100000.0, loanApplication.getLoanAmount());
        assertEquals(12, loanApplication.getLoanTenure());
        assertEquals(5.0, loanApplication.getInterestRate());
        assertEquals("John Doe", loanApplication.getNominee());
    }

    @Test
    public void testLoanApplicationId() {
        // Create a LoanApplication instance and set the ID
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setId(1L);

        // Check if the ID is set correctly
        assertEquals(1L, loanApplication.getId());
    }
}
