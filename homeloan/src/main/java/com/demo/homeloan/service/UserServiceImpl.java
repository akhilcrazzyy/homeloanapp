package com.demo.homeloan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.demo.homeloan.config.UserPrincipal;
import com.demo.homeloan.entity.User;
import com.demo.homeloan.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        User user = findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public void registerUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//	@Override
//	public User getCurrentUser() {
	    // Get the currently authenticated user's details from the security context
//	    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//	    // Assuming your UserPrincipal class wraps your User entity
//	    if (userDetails instanceof UserPrincipal) {
//	        UserPrincipal userPrincipal = (UserPrincipal) userDetails;
//	        return userPrincipal.getUser();
//	    }

//	    return null; // Return null if user not found or not authenticated
//	}
}
