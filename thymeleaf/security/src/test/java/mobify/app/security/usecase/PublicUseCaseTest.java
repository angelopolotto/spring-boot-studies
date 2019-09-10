package mobify.app.security.usecase;

import mobify.app.security.SecurityApplication;
import mobify.app.security.domain.entity.User;
import mobify.app.security.domain.interfaces.PublicUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

//@DataJpaTest //1
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityApplication.class)
public class PublicUseCaseTest {
    @Autowired
    private PublicUseCase userhandler;

    @Test
    public void createNewUser() {
        User user = new User();

        user.setName("test");
        user.setLastName("test");
        user.setPassword("12345");
        user.setEmail("teste@email.com");

        userhandler.saveUser(user);

        List<User> users = userhandler.findAll();

        // Verify the results
        assertEquals(2, users.size());
    }
}
