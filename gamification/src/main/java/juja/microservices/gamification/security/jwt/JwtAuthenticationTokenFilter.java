package juja.microservices.gamification.security.jwt;

import juja.microservices.gamification.security.exception.JwtTokenMissingException;
import juja.microservices.gamification.security.model.JwtAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter that orchestrates authentication by using supplied JWT token.
 *
 * @author olga kulykova email o.kulykova@gmail.com
 */
public final class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    private static final String TOKEN_HEADER = "Autorization";

    public JwtAuthenticationTokenFilter() {
        super("/**");
    }

    /**
     * Attempt to authenticate request - pass over to another method to authenticate request headers.
     *
     * @param request HTTP request
     * @param response HTTP response
     * @return Authentication
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response)
                                                throws AuthenticationException, IOException, ServletException {
        final String header = request.getHeader(TOKEN_HEADER);
        if (header == null || !header.startsWith("Bearer ")) {
            throw new JwtTokenMissingException("No JWT token found in request headers");
        }
        final String authToken = header.substring(7);
        final JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);
        return getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * Make sure the rest of the filter chain is satisfied.
     *
     * @param request HTTP request
     * @param response HTTP response
     * @param chain Filter chain
     * @param authResult Authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
                                            final FilterChain chain, final Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
