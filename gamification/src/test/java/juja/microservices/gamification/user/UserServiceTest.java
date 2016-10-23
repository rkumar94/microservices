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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Gamification.class})
@TestPropertySource(locations = "classpath:test.properties")
public class UserServiceTest {

    @Inject
    private UserService service;

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
