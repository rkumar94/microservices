package juja.microservices.gamification.user;

import juja.microservices.gamification.Gamification;
import juja.microservices.gamification.security.config.WebSecurityConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.HashSet;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test of authentication.
 *
 * @author olga kulykova email o.kulykova@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Gamification.class, WebSecurityConfig.class})
@TestPropertySource(locations = "classpath:test.properties")
public final class SecureIntegrationTest {

    /**
     * Web application context.
     */
    @Inject
    private WebApplicationContext context;

    /**
     * User service.
     */
    @Inject
    private UserServiceImpl service;

    /**
     * LoginPassword service.
     */
    @Inject
    private LoginPasswordService loginPasswordService;

    /**
     * Mock MVC.
     */
    private MockMvc mockMvc;

    /**
     * Create mock MVC with setting up of application context and Spring Security.
     * Create User and LoginPassword.
     */
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        final HashSet<String> authorities = new HashSet<>();
        authorities.add(AuthorityName.ROLE_ADMIN.toString());
        final User user = new User("_id", "juja", authorities);
        service.createUser(user);
        final LoginPassword loginPassword = new LoginPassword("juja", "ajuj");
        loginPasswordService.createLoginPassword(loginPassword);
    }

    /**
     * Clear context of Security context holder.
     */
    @After
    public void close() {
        SecurityContextHolder.clearContext();
    }

    /**
     * Test authentication of admin if login and password exist.
     * @throws Exception if there is an issue.
     */
    @Test
    public void authenticateAdminByLoginPasswordSuccessful() throws Exception {
        mockMvc.perform(post("/admin/login")
                        .content("{\"login\":\"juja\", \"pwd\":\"ajuj\"}")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    /**
     * Test authentication of admin if login and password don't exist or are empty.
     * @throws Exception if there is an issue.
     */
    @Test
    public void authenticateAdminIfLoginPasswordWrong() throws Exception {
        mockMvc.perform(post("/admin/login")
                        .content("{\"login\":\"\", \"pwd\":\"\"}")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isUnauthorized());
    }

    /**
     * Test authentication of admin if login and password exist but user doesn't.
     * @throws Exception
     */
    @Test
    public void authenticateAdminIfUserDoesntExists() throws Exception {
        final LoginPassword loginPassword = new LoginPassword("cracker", "cookie");
        loginPasswordService.createLoginPassword(loginPassword);
        mockMvc.perform(post("/admin/login")
                        .content("{\"login\":\"cracker\", \"pwd\":\"cookie\"}")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isUnauthorized());
    }
}
