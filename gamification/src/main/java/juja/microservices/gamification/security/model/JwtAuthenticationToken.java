package juja.microservices.gamification.security.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Holder for JWT token from the request.
 * Other fields aren't used but necessary to comply to the contracts of AbstractUserDetailsAuthenticationProvider
 *
 * @author olga kulykova email o.kulykova@gmail.com
 */
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    /**
     * Token field.
     */
    private String token;

    /**
     * Token constructor.
     * @param token Token
     */
    public JwtAuthenticationToken(String token) {
        super(null, null);
        this.token = token;
    }

    /**
     * Get token.
     * @return Token
     */
    public String getToken() {
        return token;
    }

    /**
     * Get credentials.
     * @return null
     */
    @Override
    public Object getCredentials() {
        return null;
    }

    /**
     * Get Principal.
     * @return null
     */
    @Override
    public Object getPrincipal() {
        return null;
    }
}
