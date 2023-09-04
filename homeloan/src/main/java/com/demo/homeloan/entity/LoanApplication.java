////package com.demo.homeloan.entity;
////import jakarta.persistence.*;
////
////@Entity
////public class LoanApplication {
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
////
////    @ManyToOne
////    private User user;
////
////    private String loanType;
////    private double loanAmount;
////    private int loanTenure;
////    private double interestRate;
////    private String nominee;
////    
////    public void setUser(User user) {
////        this.user = user;
////    }
////    public void setLoanType(String loanType) {
////        this.loanType = loanType;
////    }
////    
////    public void setLoanAmount(double loanAmount) {
////        this.loanAmount = loanAmount;
////    }
////    
////    public void setLoanTenure(int loanTenure) {
////        this.loanTenure = loanTenure;
////    }
////    
////    public void setInterestRate(double interestRate) {
////        this.interestRate = interestRate;
////    }
////    
////    public void setNominee(String nominee) {
////        this.nominee = nominee;
////    }
////}
//
//package com.demo.homeloan.entity;
//
//import java.util.Date;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "loan_applications")
//public class LoanApplication {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
//
//    @Column(name = "loan_type")
//    private String loanType;
//
//    @Column(name = "loan_amount")
//    private double loanAmount;
//
//    @Column(name = "loan_tenure")
//    private int loanTenure;
//
//    @Column(name = "interest_rate")
//    private double interestRate;
//
//    private String nominee;
//
//    @Column(name = "application_date")
//    private Date applicationDate;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public String getLoanType() {
//        return loanType;
//    }
//
//    public void setLoanType(String loanType) {
//        this.loanType = loanType;
//    }
//
//    public double getLoanAmount() {
//        return loanAmount;
//    }
//
//    public void setLoanAmount(double loanAmount) {
//        this.loanAmount = loanAmount;
//    }
//
//    public int getLoanTenure() {
//        return loanTenure;
//    }
//
//    public void setLoanTenure(int loanTenure) {
//        this.loanTenure = loanTenure;
//    }
//
//    public double getInterestRate() {
//        return interestRate;
//    }
//
//    public void setInterestRate(double interestRate) {
//        this.interestRate = interestRate;
//    }
//
//    public String getNominee() {
//        return nominee;
//    }
//
//    public void setNominee(String nominee) {
//        this.nominee = nominee;
//    }
//
//    public Date getApplicationDate() {
//        return applicationDate;
//    }
//
//    public void setApplicationDate(Date applicationDate) {
//        this.applicationDate = applicationDate;
//    }
//}

package com.demo.homeloan.entity;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "loan_applications")
public class LoanApplication implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "loan_amount")
    private double loanAmount;

    @Column(name = "loan_tenure")
    private int loanTenure;

    @Column(name = "interest_rate")
    private double interestRate;

    private String nominee;

    // Constructors, getters, and setters

    public LoanApplication() {
        // Default constructor
    }

    public LoanApplication(User user, String loanType, double loanAmount, int loanTenure, double interestRate, String nominee) {
        this.user = user;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.loanTenure = loanTenure;
        this.interestRate = interestRate;
        this.nominee = nominee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getLoanTenure() {
        return loanTenure;
    }

    public void setLoanTenure(int loanTenure) {
        this.loanTenure = loanTenure;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getNominee() {
        return nominee;
    }

    public void setNominee(String nominee) {
        this.nominee = nominee;
    }
}
