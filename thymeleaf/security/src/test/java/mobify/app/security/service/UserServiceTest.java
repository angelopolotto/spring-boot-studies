//package mobify.app.security.service;
//
//import mobify.app.security.entity.User;
//import mobify.app.security.repository.RoleRepository;
//import mobify.app.security.repository.UserRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.MockitoAnnotations.initMocks;
//
//public class UserServiceTest {
//
//    @Mock
//    private UserRepository mockUserRepository;
//    @Mock
//    private RoleRepository mockRoleRepository;
//    @Mock
//    private BCryptPasswordEncoder mockBCryptPasswordEncoder;
//
//    private UserService userServiceUnderTest;
//    private User user;
//
//    @Before
//    public void setUp() {
//        initMocks(this);
//        userServiceUnderTest = new UserService(mockUserRepository,
//                mockRoleRepository,
//                mockBCryptPasswordEncoder);
//        user = new User() {{
//            setId(1);
//            setName("Gustavo");
//            setLastName("Ponce");
//            setEmail("test@test.com");
//        }};
//
//        Mockito.when(mockUserRepository.save(any()))
//                .thenReturn(user);
//        Mockito.when(mockUserRepository.findByEmail(anyString()))
//                .thenReturn(user);
//    }
//
//    @Test
//    public void testFindUserByEmail() {
//        // Setup
//        final String email = "test@test.com";
//
//        // Run the test
//        final User result = userServiceUnderTest.findUserByEmail(email);
//
//        // Verify the results
//        assertEquals(email, result.getEmail());
//    }
//
//    @Test
//    public void testSaveUser() {
//        // Setup
//        final String email = "test@test.com";
//
//        // Run the test
//        User result = userServiceUnderTest.saveUser(user);
//
//        // Verify the results
//        assertEquals(email, result.getEmail());
//    }
//}