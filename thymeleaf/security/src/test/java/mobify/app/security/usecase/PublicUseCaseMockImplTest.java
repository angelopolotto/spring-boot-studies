package mobify.app.security.usecase;

import mobify.app.security.domain.contracts.PublicContract;
import mobify.app.security.domain.entity.User;
import mobify.app.security.domain.repository.RoleRepository;
import mobify.app.security.domain.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class PublicUseCaseMockImplTest {
    @Mock
    private BindingResult bindingResult;
    @Mock
    private PublicContract.PublicController publicController;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private PublicContract.PublicUseCase publicUseCase;
    private User user;

    @Before
    public void setUp() {
        initMocks(this);
        publicUseCase = new PublicUseCaseImpl(userRepository,
                roleRepository,
                mockBCryptPasswordEncoder);

        publicUseCase.setController(publicController);

        user = new User() {{
            setId(1);
            setName("Jude");
            setLastName("Do");
            setEmail("jude@do.com");
        }};

        Mockito.when(userRepository.save(any()))
                .thenReturn(user);
        Mockito.when(userRepository.findByEmail(anyString()))
                .thenReturn(user);

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
    }

    @Test
    public void createNewUserTest() {
        // Setup
        User user = new User();

        user.setName("test");
        user.setLastName("test");
        user.setPassword("12345");
        user.setEmail("teste@email.com");

        // Run the test
        final ModelAndView result = publicUseCase.createNewUser(user, bindingResult);

        List<User> users = userRepository.findAll();

        // Verify the results
        assertEquals(0, users.size());
    }
}