package juja.microservices.gamification.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class AdminAuthenticationIntegrationTest {

    @Inject
    private MockMvc mvc;

    @Test
    public void authenticateAdminByLoginPassword() throws Exception {
        mvc.perform(post("/admin/login")
            .content("{\"login\":\"juja\", \"pwd\":\"ajuj\"}")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
