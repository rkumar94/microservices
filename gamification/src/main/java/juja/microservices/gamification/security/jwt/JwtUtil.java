package juja.microservices.gamification.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import juja.microservices.gamification.security.exception.JwtTokenMalformedException;
import juja.microservices.gamification.user.User;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Util class for generating and parsing of token.
 *
 * @author olga kulykova email o.kulykova@gmail.com
 */
@Component
public final class JwtUtil {

    private static final String SECRET = "jujanior";

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id
     * and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), throws exception.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token if a token is valid
     * @throws AuthenticationException if a token is invalid
     */
    public User parseToken(final String token) throws AuthenticationException {
        try {
            final Claims body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            return new User((String) body.get("userId"), body.getSubject(), (Set<String>) body.get("role"));
        } catch (JwtException | ClassCastException e) {
            throw (JwtTokenMalformedException) new JwtTokenMalformedException("JWT token is not valid").initCause(e);
        }
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims.
     * These properties are taken from the specified User object. Tokens validity is infinite.
     *
     * @param user the user for which the token will be generated
     * @return the JWT token
     */
    public String generateToken(final User user) {
        final Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("userId", user.getId());
        claims.put("role", user.getAuthorities().toArray());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
}
