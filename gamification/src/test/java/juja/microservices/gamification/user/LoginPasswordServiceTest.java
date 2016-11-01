package juja.microservices.gamification.user;

import juja.microservices.gamification.Gamification;
import org.apache.commons.codec.digest.DigestUtils;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;

/**
 * LoginPassword service test.
 *
 * @author olga kulykova email o.kulykova@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Gamification.class})
@TestPropertySource(locations = "classpath:test.properties")
public class LoginPasswordServiceTest {

    /**
     * LoginPassword service.
     */
    @Inject
    private LoginPasswordService service;

    /**
     * Create and get LoginPassword.
     * These two objects should be matched.
     * @throws Exception
     */
    @Test
    public final void createAndGetLoginPassword()throws Exception {
        final LoginPassword loginPassword = new LoginPassword("juja", "ajuj");
        LoginPassword result = service.createLoginPassword(loginPassword);
        final LoginPassword encryptedLoginPassword = new LoginPassword("juja", DigestUtils.md5Hex("ajuj"));
        MatcherAssert.assertThat(encryptedLoginPassword, is(result));

        result = service.getLoginPassword(encryptedLoginPassword);
        MatcherAssert.assertThat(encryptedLoginPassword, is(result));
    }
}
