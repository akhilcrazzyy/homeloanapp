package com.demo.homeloan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.demo.homeloan.controller.UserController;
import com.demo.homeloan.entity.User;
import com.demo.homeloan.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Model model;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testShowLandingPage() {
        String result = userController.redirectToLogin();
        assertEquals("redirect:/login", result);
    }
    
    @Test
    void testShowLoginForm() {
        String result = userController.showLoginForm();
        assertEquals("login", result);
    }

    @Test
    void testLogin_ValidCredentials_Admin() {
        String email = "admin@example.com";
        String password = "adminPassword";
        User adminUser = new User();
        adminUser.setEmail(email);
        adminUser.setPassword(password);
        adminUser.setAdmin(true);

        when(userRepository.findByEmail(email)).thenReturn(adminUser);

        String result = userController.login(email, password, model);
        assertEquals("admin-dashboard", result);
        verify(model).addAttribute("users", userRepository.findAll());
    }

    @Test
    void testLogin_ValidCredentials_User() {
        String email = "user@example.com";
        String password = "userPassword";
        User regularUser = new User();
        regularUser.setEmail(email);
        regularUser.setPassword(password);
        regularUser.setAdmin(false);

        when(userRepository.findByEmail(email)).thenReturn(regularUser);

        String result = userController.login(email, password, model);
        assertEquals("user-dashboard", result);
        verify(model).addAttribute("user", regularUser);
    }
    
    @Test
    void testLogin_InvalidPass_User() {
        String email = "john@example.com";
        String password = "userPassword";
        String wrongpassword = "wrongPassword";
        User regularUser = new User();
        regularUser.setEmail(email);
        regularUser.setPassword(password);
        regularUser.setAdmin(false);

        when(userRepository.findByEmail(email)).thenReturn(regularUser);

        String result = userController.login(email, wrongpassword, model);
        assertEquals("login", result);
        verify(model).addAttribute("errorMessage", "Invalid email or password");
    }

    @Test
    void testLogin_InvalidCredentials() {
        String email = "invalid@example.com";
        String password = "invalidPassword";

        when(userRepository.findByEmail(email)).thenReturn(null);

        String result = userController.login(email, password, model);
        assertEquals("login", result);
        verify(model).addAttribute("errorMessage", "Invalid email or password");
    }

    @Test
    void testShowRegistrationForm() {
        String result = userController.showRegistrationForm(model);
        assertEquals("registration", result);
    }

    @Test
    void testRegister() {
        User user = new User();
        String result = userController.register(user, model);
        assertEquals("registration", result);
        verify(userRepository).save(user);
        verify(model).addAttribute("successMessage", "Registration Successful");
    }
}
