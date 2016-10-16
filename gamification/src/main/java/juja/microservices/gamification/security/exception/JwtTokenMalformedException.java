package juja.microservices.gamification.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Thrown when token cannot be parsed.
 * @author olga kulykova email o.kulykova@gmail.com
 */
public class JwtTokenMalformedException extends AuthenticationException {

    public JwtTokenMalformedException(String msg) {
        super(msg);
    }
}
