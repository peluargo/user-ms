package app.peluargo.user;

import app.peluargo.user.api.UserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private UserController userController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(this.userController);
    }
}
