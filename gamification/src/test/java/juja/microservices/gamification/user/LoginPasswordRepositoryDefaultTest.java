package juja.microservices.gamification.user;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * LoginPassword repository test.
 *
 * @author olga kulykova email o.kulykova@gmail.com
 */
public class LoginPasswordRepositoryDefaultTest {
    /**
     * LoginPassword repository.
     */
    @InjectMocks
    private LoginPasswordRepositoryDefault repository;

    /**
     * Mongo templ.
     */
    @Mock
    private MongoTemplate templ;

    /**
     * Init mocks.
     */
    @Before
    public final void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Get LoginPassword test.
     * @throws Exception if there is an issue.
     */
    @Test
    public final void getLoginPassword() throws Exception {
        final String login = "juja";
        final String password = "ajuj";
        final LoginPassword loginPassword = new LoginPassword(login, password);
        final Query query = Query.query(Criteria.where("login").is(login).and("password").is(password));
        this.repository.getLoginPassword(loginPassword);
        Mockito.verify(this.templ, Mockito.times(1)).findOne(query, LoginPassword.class);
    }
}
