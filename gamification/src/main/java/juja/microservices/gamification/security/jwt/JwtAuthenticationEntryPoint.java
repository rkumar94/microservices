package juja.microservices.gamification.security.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * REST gives a HTTP 401 if user cannot be authenticated. There is no
 * login page top redirect to.
 *
 * @author olga kulykova email o.kulykova@gmail.com
 */
@Component
public final class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * This method is invoked when user tries to access a secured REST resource without supplying any credentials.
     *
     * @param request HTTP request
     * @param response HTTP response
     * @param authException Authentication exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
