package app.peluargo.user;

import app.peluargo.user.api.controllers.UserController;
import app.peluargo.user.api.repositories.UserRepository;
import app.peluargo.user.api.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserSmokeTest {
	@Autowired
	private UserController userController;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Test
	@DisplayName("UserController must not be null")
	void smokeTestUserController() {
		Assertions.assertNotNull(this.userController);
	}

	@Test
	@DisplayName("UserService must not be null")
	void smokeTestUserService() {
		Assertions.assertNotNull(this.userService);
	}

	@Test
	@DisplayName("UserRepository must not be null")
	void smokeTestUserRepository() {
		Assertions.assertNotNull(this.userRepository);
	}
}
