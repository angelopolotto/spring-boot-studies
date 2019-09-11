package mobify.app.security.usecase;

import mobify.app.security.SecurityApplication;
import mobify.app.security.domain.contracts.PublicContract;
import mobify.app.security.domain.entity.User;
import mobify.app.security.domain.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.Assert.assertEquals;

//@DataJpaTest //1
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityApplication.class)
public class PublicUseCaseImplTest {
    @MockBean
    private BindingResult bindingResult;

    @Autowired
    private PublicContract.PublicUseCase userhandler;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createNewUserTest() {
        User user = new User();

        user.setName("test");
        user.setLastName("test");
        user.setPassword("12345");
        user.setEmail("teste@email.com");

        userhandler.createNewUser(user, bindingResult);

        List<User> users = userRepository.findAll();

        // Verify the results
        assertEquals(2, users.size());
    }
}
