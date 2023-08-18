package com.demo.homeloan;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.demo.homeloan.entity.User;
import com.demo.homeloan.repo.UserRepository;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setAdmin(false);
        userRepository.save(user);

        User foundUser = userRepository.findByEmail("test@example.com");
        assertNotNull(foundUser);
        assertEquals("test@example.com", foundUser.getEmail());
    }
    
    @Test
    public void testFindAdminByEmail() {
        User user = new User();
        user.setEmail("admin@example.com");
        user.setPassword("password");
        user.setAdmin(true);
        userRepository.save(user);

        User foundUser = userRepository.findByEmail("admin@example.com");
        assertNotNull(foundUser);
        assertEquals("admin@example.com", foundUser.getEmail());
    }

    @Test
    public void testFindByEmailNonExistent() {
        User foundUser = userRepository.findByEmail("nonexistent@example.com");
        assertNull(foundUser);
    }
}
