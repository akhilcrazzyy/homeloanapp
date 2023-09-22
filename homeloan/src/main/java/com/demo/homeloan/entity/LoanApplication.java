package com.demo.homeloan.entity;
import jakarta.persistence.*;
import java.util.Date;

import java.io.Serializable;

@Entity
@Table(name = "loan_applications")
public class LoanApplication implements Serializable {

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
    
    @Column(name = "loan_creation_date")
    @Temporal(TemporalType.DATE)
    private Date loanCreationDate;
    
    @Column(name = "nominee")
    private String nominee;
    
    @Column(name = "loan_status")
    private String loanStatus;

    @Column(name = "outstanding_principal")
    private double outstandingPrincipal;

    @Column(name = "remaining_emi")
    private int remainingEMI;


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
    
    public double getOutstandingPrincipal() {
        return outstandingPrincipal;
    }

    public void setOutstandingPrincipal(double outstandingPrincipal) {
        this.outstandingPrincipal = outstandingPrincipal;
    }

    public int getRemainingEMI() {
        return remainingEMI;
    }

    public void setRemainingEMI(int remainingEMI) {
        this.remainingEMI = remainingEMI;
    }
    
    @PrePersist
    public void onCreate() {
        loanCreationDate = new Date(); // Automatically set the Loan Creation Date
        outstandingPrincipal = calculateOutstandingPrincipal();
        remainingEMI = calculateRemainingEMI();
    }
    
    public Date getLoanCreationDate() {
        return loanCreationDate;
    }

    public void setLoanCreationDate(Date loanCreationDate) {
        this.loanCreationDate = loanCreationDate;
    }
    
    public String getLoanStatus() {
        Date currentDate = new Date();
        long daysDifference = (currentDate.getTime() - loanCreationDate.getTime()) / (24 * 60 * 60 * 1000);

        if (daysDifference >= 14) {
            return "Approved";
        } else {
            return "Pending for approval";
        }
    }
    
    private double calculateOutstandingPrincipal() {
        // Calculate and return the outstanding principal
        return loanAmount - (calculateEMIPerMonth() * (loanTenure - remainingEMI));
    }

    private int calculateRemainingEMI() {
        // Calculate and return the remaining EMI months
        return loanTenure - (int)((loanAmount / calculateEMIPerMonth()) * (loanTenure - remainingEMI));
    }

    private double calculateEMIPerMonth() {
        // Calculate and return the EMI per month based on the loan amount, interest rate, and tenure
        double monthlyInterestRate = (interestRate / 12) / 100;
        return (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -loanTenure));
    }
}
