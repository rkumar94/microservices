package juja.microservices.gamification.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Holds the info for a authenticated user (Principal).
 *
 * @author olga kulykova email o.kulykova@gmail.com
 */
public final class AuthenticatedUser implements UserDetails {

    /**
     * Id field.
     */
    private final String id;

    /**
     * Username field.
     */
    private final String username;

    /**
     * Token field.
     */
    private final String token;

    /**
     * Collection of granted authorities.
     */
    private final Collection<? extends GrantedAuthority> authorities;

    /**
     * AuthenticatedUser constructor.
     * @param id Id
     * @param username Username
     * @param token Token
     * @param authorities Authorities
     */
    public AuthenticatedUser(String id, String username, String token,
                             Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.authorities = authorities;
    }

    /**
     * Get id.
     * @return Id
     */
    @JsonIgnore
    public String getId() {
        return id;
    }

    /**
     * Get token.
     * @return Token
     */
    public String getToken() {
        return token;
    }

    /**
     * Get granted authorities.
     * @return Authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Get password.
     * @return null
     */
    @Override
    public String getPassword() {
        return null;
    }

    /**
     * Get username.
     * @return Username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * In account non expired.
     * @return true
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Is account non locked.
     * @return true
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Is credentials non expired.
     * @return true
     */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Is enabled.
     * @return true
     */
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
