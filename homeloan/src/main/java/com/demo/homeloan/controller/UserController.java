package com.demo.homeloan.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//import com.demo.homeloan.config.UserPrincipal;
//import com.demo.homeloan.entity.LoanApplication;
import com.demo.homeloan.entity.User;
//import com.demo.homeloan.repo.UserRepository;
//import com.demo.homeloan.service.LoanApplicationService;
import com.demo.homeloan.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
//    @GetMapping("/dashboard")
//    public String showUserDashboard(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
//        User currentUser = userPrincipal.getUser();
//        model.addAttribute("user", currentUser);
//        return "user-dashboard";
//    }

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String email, @RequestParam String password, Model model) {
//        boolean isAuthenticated = userService.authenticateUser(email, password);
//
//        if (isAuthenticated) {
//            User user = userService.findByEmail(email);
//
//            if (user.isAdmin()) {
//                List<User> users = userService.getAllUsers(); // Retrieve all users using the service
//                model.addAttribute("users", users);
//                return "admin-dashboard";
//            } else {
//                model.addAttribute("user", user);
//                return "user-dashboard";
//            }
//        } else {
//            model.addAttribute("errorMessage", "Invalid email or password");
//            return "login";
//        }
//    }
    
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        boolean isAuthenticated = userService.authenticateUser(email, password);

        if (isAuthenticated) {
            User user = userService.findByEmail(email);

            if (user.isAdmin()) {
                List<User> users = userService.getAllUsers(); // Retrieve all users using the service
                model.addAttribute("users", users);
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
