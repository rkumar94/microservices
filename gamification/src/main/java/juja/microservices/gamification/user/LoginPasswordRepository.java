package juja.microservices.gamification.user;

/**
 * LoginPassword repository interface.
 *
 * @author olga kulykova email o.kulykova@gmail.com
 */
public interface LoginPasswordRepository {

    /**
     * Get LoginPassword by login and password.
     * @param loginPassword LoginPassword
     * @return LoginPassword
     */
    LoginPassword getLoginPassword(LoginPassword loginPassword);

    /**
     * Create LoginPassword.
     * @param loginPassword LoginPassword
     * @return LoginPassword
     */
    LoginPassword createLoginPassword(LoginPassword loginPassword);
}
