//package com.demo.homeloan;
//
//import com.demo.homeloan.entity.User;
//import com.demo.homeloan.repo.UserRepository;
//import com.demo.homeloan.service.UserServiceImpl;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class UserServiceImplTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    @SuppressWarnings("deprecation")
//	@BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testFindByEmail() {
//        // Create a test user
//        User testUser = new User();
//        testUser.setEmail("test@example.com");
//
//        // Mock the repository to return the test user
//        when(userRepository.findByEmail("test@example.com")).thenReturn(testUser);
//
//        // Test finding a user by email
//        User foundUser = userService.findByEmail("test@example.com");
//        assertEquals("test@example.com", foundUser.getEmail());
//    }
//
//    @Test
//    public void testAuthenticateUserValid() {
//        // Create a test user
//        User testUser = new User();
//        testUser.setEmail("test@example.com");
//        testUser.setPassword("password");
//
//        // Mock the repository to return the test user
//        when(userRepository.findByEmail("test@example.com")).thenReturn(testUser);
//
//        // Test authenticating a valid user
//        boolean isAuthenticated = userService.authenticateUser("test@example.com", "password");
//        assertTrue(isAuthenticated);
//    }
//
//    @Test
//    public void testAuthenticateUserInvalid() {
//        // Mock the repository to return null (user not found)
//        when(userRepository.findByEmail("test@example.com")).thenReturn(null);
//
//        // Test authenticating an invalid user
//        boolean isAuthenticated = userService.authenticateUser("test@example.com", "password");
//        assertFalse(isAuthenticated);
//    }
//
//    @Test
//    public void testRegisterUser() {
//        // Create a test user
//        User testUser = new User();
//        testUser.setEmail("test@example.com");
//
//        // Test registering a user
//        userService.registerUser(testUser);
//
//        // Verify that userRepository.save() was called once with the test user
//        verify(userRepository, times(1)).save(testUser);
//    }
//
//    @Test
//    public void testGetAllUsers() {
//        // Create a list of test users
//        List<User> testUsers = new ArrayList<>();
//        User user1 = new User();
//        user1.setEmail("user1@example.com");
//        User user2 = new User();
//        user2.setEmail("user2@example.com");
//        testUsers.add(user1);
//        testUsers.add(user2);
//
//        // Mock the repository to return the test users
//        when(userRepository.findAll()).thenReturn(testUsers);
//
//        // Test getting all users
//        List<User> foundUsers = userService.getAllUsers();
//        assertEquals(2, foundUsers.size());
//        assertEquals("user1@example.com", foundUsers.get(0).getEmail());
//        assertEquals("user2@example.com", foundUsers.get(1).getEmail());
//    }
//}
