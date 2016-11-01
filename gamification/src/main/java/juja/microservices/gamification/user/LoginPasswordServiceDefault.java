package juja.microservices.gamification.user;

import org.apache.commons.codec.digest.DigestUtils;
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
     * Get encrypted LoginPassword by login and password.
     * @param loginPassword LoginPassword
     * @return LoginPassword
     */
    @Override
    public final LoginPassword getLoginPassword(final LoginPassword loginPassword) {
        return repository.getLoginPassword(loginPassword);
    }

    /**
     * Create encrypted LoginPassword.
     * @param loginPassword LoginPassword
     * @return LoginPassword
     */
    @Override
    public final LoginPassword createLoginPassword(final LoginPassword loginPassword) {
        //todo It's temporary place for password encrypting, replace to controller
        final LoginPassword encryptedLoginPassword = new LoginPassword(loginPassword.getLogin(),
                DigestUtils.md5Hex(loginPassword.getPassword()));
        return repository.createLoginPassword(encryptedLoginPassword);
    }
}
