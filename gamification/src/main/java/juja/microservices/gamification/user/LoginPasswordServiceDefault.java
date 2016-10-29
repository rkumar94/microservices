package juja.microservices.gamification.user;

import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * LoginPassword service default implementation.
 *
 * @author olga kulykova email o.kulykova@gmail.com
 */
@Component
public class LoginPasswordServiceDefault implements LoginPasswordService {

    /**
     * LoginPassword repository.
     */
    private final LoginPasswordRepository repository;

    @Inject
    public LoginPasswordServiceDefault(final LoginPasswordRepository repository) {
        this.repository = repository;
    }

    /**
     * Get LoginPassword by login.
     * @param login Login
     * @return LoginPassword
     */
    @Override
    public final LoginPassword getLoginPassword(final String login) {
        return repository.getLoginPassword(login);
    }

    /**
     * Create LoginPassword.
     * @param loginPassword LoginPassword @todo crypt password here.
     * @return LoginPassword
     */
    @Override
    public final LoginPassword createLoginPassword(final LoginPassword loginPassword) {
        return repository.createLoginPassword(loginPassword);
    }
}
