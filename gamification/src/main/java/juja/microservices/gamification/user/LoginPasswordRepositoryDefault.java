package juja.microservices.gamification.user;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * LoginPassword repository default implementation.
 *
 * @author olga kulykova email o.kulykova@gmail.com
 */
public class LoginPasswordRepositoryDefault implements LoginPasswordRepository {

    /**
     * MongoTemplate.
     */
    private final MongoTemplate template;

    public LoginPasswordRepositoryDefault(final MongoTemplate template) {
        this.template = template;
    }

    /**
     * Get LoginPassword by login and password.
     * @param loginPassword LoginPassword
     * @return LoginPassword
     */
    @Override
    public final LoginPassword getLoginPassword(final LoginPassword loginPassword) {
        final Query query = Query.query(Criteria.where("login").is(loginPassword.getLogin())
                .and("password").is(loginPassword.getPassword()));
        return this.template.findOne(query, LoginPassword.class);
    }

    /**
     * Create LoginPassword.
     * @param loginPassword LoginPassword
     * @return LoginPassword
     */
    @Override
    public final LoginPassword createLoginPassword(final LoginPassword loginPassword) {
        this.template.save(loginPassword);
        return loginPassword;
    }
}
