package com.demo.homeloan.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.demo.homeloan.entity.User;  // Import the User entity class
import com.demo.homeloan.repo.UserRepository;  // Import the UserRepository interface

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            if (user.isAdmin()) {
                model.addAttribute("users", userRepository.findAll());
                return "admin-dashboard";
            } else {
                model.addAttribute("user", user);
                return "user-dashboard";
            }
        } else {
            model.addAttribute("errorMessage", "Invalid email or password"); // Add error message to model
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
        userRepository.save(user);
        model.addAttribute("successMessage", "Registration Successful");
        return "registration";
    }
}

