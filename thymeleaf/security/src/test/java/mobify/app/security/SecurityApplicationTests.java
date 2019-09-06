package mobify.app.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void hashPassword() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123";

        for (int i = 0; i < 5; i++) {
            // "123456" - plain text - user input from user interface
            String passwd = encoder.encode(rawPassword);

            // passwd - password from database
            System.out.println(passwd); // print hash

            // true for all 5 iteration
            System.out.println(encoder.matches(rawPassword, passwd));
        }
    }
}
