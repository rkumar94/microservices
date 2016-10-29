package juja.microservices.gamification.user;

import juja.microservices.gamification.Gamification;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;

/**
 * User service test.
 *
 * @author olga kulykova email o.kulykova@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Gamification.class})
@TestPropertySource(locations = "classpath:test.properties")
public class UserServiceTest {

    /**
     * User service.
     */
    @Inject
    private UserService service;

    /**
     * Create user and get user by username.
     * Test if these users are matched.
     * @throws Exception if there is an issue.
     */
    @Test
    public final void createAndGetUserByUsername() throws Exception {
        final String id = "";
        final String name = "name";
        final Set<String> authorities = new HashSet<>();
        authorities.add(AuthorityName.ROLE_ADMIN.toString());
        final User user = new User(id, name, authorities);
        User createdUser = this.service.createUser(user);

        MatcherAssert.assertThat(user, is(createdUser));

        createdUser = this.service.getUserByUsername("name");

        MatcherAssert.assertThat(user, is(createdUser));
    }

    /**
     * Get user by id verify.
     * @throws Exception if there is an issue.
     */
    @Test
    public final void getUserById() throws Exception {
        final List<User> users = service.getUsers();
        if (!users.isEmpty()) {
            final User user = users.get(0);
            final String id = user.getId();
            final User resultUser = service.getUser(id);
            MatcherAssert.assertThat(user, is(resultUser));
        }
    }
}
