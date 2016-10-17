package juja.microservices.gamification.security.jwt;

import juja.microservices.gamification.security.model.AuthenticatedUser;
import juja.microservices.gamification.security.model.JwtAuthenticationToken;
import juja.microservices.gamification.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Used for checking the token from the request and supply the UserDetails if the token is valid.
 *
 * @author olga kulykova email o.kulykova@gmail.com
 */
@Component
public final class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

   @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean supports(final Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @SuppressWarnings("PMD.UncommentedEmptyMethodBody")
    @Override
    protected void additionalAuthenticationChecks(final UserDetails userDetails,
                                                  final UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        final JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        final String token = jwtAuthenticationToken.getToken();

        final User parsedUser = jwtUtil.parseToken(token);

        final String commaSeparatedAuthorities = getCommaSeparatedAuthorities(parsedUser.getAuthorities());
        final List<GrantedAuthority> authorityList =
                AuthorityUtils.commaSeparatedStringToAuthorityList(commaSeparatedAuthorities);

        return new AuthenticatedUser(parsedUser.getId(), parsedUser.getUsername(), token, authorityList);
    }

    private String getCommaSeparatedAuthorities(final Set<String> authorities) {
        return authorities.toString().replace("[", "").replace("]", "").trim();
    }
}
