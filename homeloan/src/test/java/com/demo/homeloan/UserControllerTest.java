package com.demo.homeloan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.demo.homeloan.controller.UserController;
import com.demo.homeloan.entity.User;
import com.demo.homeloan.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    private UserController userController;

    @SuppressWarnings("deprecation")
	@BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userService);
    }

    @Test
    void redirectToLogin_ReturnsRedirectToLogin() {
        String viewName = userController.redirectToLogin();
        assertEquals("redirect:/login", viewName);
    }

    @Test
    void showLoginForm_ReturnsLoginView() {
        String viewName = userController.showLoginForm();
        assertEquals("login", viewName);
    }

    @Test
    void showRegistrationForm_ReturnsRegistrationViewWithUserAttribute() {
        String viewName = userController.showRegistrationForm(model);
        assertEquals("registration", viewName);

        verify(model).addAttribute(eq("user"), any(User.class));
    }

    @Test
    void register_WithValidUser_ReturnsRegistrationViewWithSuccessMessage() {
        User user = new User();
        user.setEmail("newuser@example.com");
        user.setPassword("newpassword");

        doNothing().when(userService).registerUser(user);

        String viewName = userController.register(user, model);

        assertEquals("registration", viewName);
        verify(model).addAttribute("successMessage", "Registration Successful");
        verify(userService).registerUser(user);
    }


    @Test
    void login_WithValidCredentialsAndUserIsAdmin_ReturnsAdminDashboardView() {
        String email = "admin@example.com";
        String password = "adminpassword";
        User adminUser = new User();
        adminUser.setEmail(email);
        adminUser.setPassword(password);
        adminUser.setAdmin(true);

        when(userService.authenticateUser(email, password)).thenReturn(true);
        when(userService.findByEmail(email)).thenReturn(adminUser);

        String viewName = userController.login(email, password, model);

        assertEquals("admin-dashboard", viewName);
        verify(userService).authenticateUser(email, password);
        verify(userService).findByEmail(email);
    }

    @Test
    void login_WithValidCredentialsAndUserIsNotAdmin_ReturnsUserDashboardView() {
        String email = "user@example.com";
        String password = "userpassword";
        User regularUser = new User();
        regularUser.setEmail(email);
        regularUser.setPassword(password);
        regularUser.setAdmin(false);

        when(userService.authenticateUser(email, password)).thenReturn(true);
        when(userService.findByEmail(email)).thenReturn(regularUser);

        String viewName = userController.login(email, password, model);

        assertEquals("user-dashboard", viewName);
        verify(userService).authenticateUser(email, password);
        verify(userService).findByEmail(email);
    }

    @Test
    void login_WithInvalidCredentials_ReturnsLoginViewWithError() {
        String email = "invalid@example.com";
        String password = "invalidpassword";

        when(userService.authenticateUser(email, password)).thenReturn(false);

        String viewName = userController.login(email, password, model);

        assertEquals("login", viewName);
        verify(model).addAttribute("errorMessage", "Invalid email or password");
        verify(userService).authenticateUser(email, password);
        verifyNoMoreInteractions(userService);
    }
}
