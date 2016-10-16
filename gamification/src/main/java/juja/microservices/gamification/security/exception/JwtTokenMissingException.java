package juja.microservices.gamification.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Thrown when token cannot be found in the request header.
 * @author olga kulykova email o.kulykova@gmail.com
 */
public class JwtTokenMissingException extends AuthenticationException {

    public JwtTokenMissingException(final String msg) {
        super(msg);
    }
}
