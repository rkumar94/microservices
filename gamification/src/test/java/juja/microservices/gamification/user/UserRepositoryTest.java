package juja.microservices.gamification.user;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Inject
    private UserRepository repository;

    @Test
    public final void createUser() throws Exception {
        final String id = "_id";
        final String name = "name";
        final Set<String> authorities = new HashSet<>();
        authorities.add(AuthorityName.ROLE_ADMIN.toString());
        final User user = new User(id, name, authorities);
        final User result = this.repository.createUser(user);
        MatcherAssert.assertThat(user, is(result));
    }
}
