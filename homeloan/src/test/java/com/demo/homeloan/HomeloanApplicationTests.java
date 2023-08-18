package com.demo.homeloan;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HomeloanApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
    void applicationStarts() {
        HomeloanApplication.main(new String[]{}); // Start the application
        // No assertion needed, we're just checking for exceptions during startup
    }

}
