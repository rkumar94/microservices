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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test of authentication.
 *
 * @author olga kulykova email o.kulykova@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Gamification.class, WebSecurityConfig.class, TestConfiguration.class})
public final class SecureIntegrationTest {

    /**
     * Web application context.
     */
    @Inject
    private WebApplicationContext context;

    /**
     * Mock MVC.
     */
    private MockMvc mockMvc;

    /**
     * Create mock MVC with setting up of application context and Spring Security.
     */
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    /**
     * Clear context of Security context holder.
     */
    @After
    public void close() {
        SecurityContextHolder.clearContext();
    }

    /**
     * Test authentication of admin by login and password.
     * @throws Exception if there is an issue.
     */
    @Test
    public void authenticateAdminByLoginPassword() throws Exception {
        mockMvc.perform(post("/admin/login")
                        .content("{\"login\":\"juja\", \"pwd\":\"ajuj\"}")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
