package juja.microservices.gamification.user;

/**
 * LoginPassword service interface.
 *
 * @author olga kulykova email o.kulykova@gmail.com
 */
public interface LoginPasswordService {

    /**
     * Get LoginPassword by login.
     * @param login Login
     * @return LoginPassword
     */
    LoginPassword getLoginPassword(String login);

    /**
     * Create LoginPassword.
     * @param loginPassword LoginPassword
     * @return LoginPassword
     */
    LoginPassword createLoginPassword(LoginPassword loginPassword);
}
