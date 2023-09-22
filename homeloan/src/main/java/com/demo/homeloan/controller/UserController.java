package com.demo.homeloan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.homeloan.entity.LoanApplication;
import com.demo.homeloan.entity.User;
import com.demo.homeloan.service.LoanApplicationService;
import com.demo.homeloan.service.UserService;

@Controller
public class UserController {
    private final UserService userService;
	private final LoanApplicationService loanApplicationService;

    @Autowired
    public UserController(UserService userService, LoanApplicationService loanApplicationService) {
        this.userService = userService;
        this.loanApplicationService = loanApplicationService;
    }
    
    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/user-dashboard")
    public String showUserDashboard() {
        return "user-dashboard";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        boolean isAuthenticated = userService.authenticateUser(email, password);

        if (isAuthenticated) {
            User user = userService.findByEmail(email);

            if (user.isAdmin()) {
//                List<User> users = userService.getAllUsers(); // Retrieve all users using the service
//                model.addAttribute("users", users);
//                return "admin-dashboard";	
                List<LoanApplication> userLoans = loanApplicationService.getAllLoans();
                model.addAttribute("userLoans", userLoans);
                return "admin-dashboard";              
            } else {
                model.addAttribute("user", user);
                return "user-dashboard";
            }
        } else {
            model.addAttribute("errorMessage", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute User user, Model model) {
        userService.registerUser(user);
        model.addAttribute("successMessage", "Registration Successful");
        return "registration";
    }
}
