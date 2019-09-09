package mobify.app.security.repository;

import mobify.app.security.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void addUser() {
        User user = new User();
        user.setName("test");
        user.setLastName("test");
        user.setPassword("12345");
        user.setEmail("teste@email.com");

        entityManager.persist(user);
        entityManager.flush();

        List<User> users = userRepository.findAll();

        // Verify the results
        assertEquals(2, users.size());
    }
}
