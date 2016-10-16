package juja.microservices.gamification.security.jwt;

import juja.microservices.gamification.security.exception.JwtTokenMissingException;
import juja.microservices.gamification.security.model.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter that orchestrates authentication by using supplied JWT token
 *
 * @author olga kulykova email o.kulykova@gmail.com
 */
public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    private String tokenHeader = "Autorization";

    public JwtAuthenticationTokenFilter() {
        super("/**");
    }

    /**
     * Attempt to authenticate request - pass over to another method to authenticate request headers
     *
     * @param request HTTP request
     * @param response HTTP response
     * @return Authentication
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
                                                throws AuthenticationException, IOException, ServletException {
        String header = request.getHeader(this.tokenHeader);
        if (header == null || !header.startsWith("Bearer ")) {
            throw new JwtTokenMissingException("No JWT token found in request headers");
        }
        String authToken = header.substring(7);
        JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);
        return getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * Make sure the rest of the filter chain is satisfied
     *
     * @param request HTTP request
     * @param response HTTP response
     * @param chain Filter chain
     * @param authResult Authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
