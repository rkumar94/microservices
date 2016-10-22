package juja.microservices.gamification.user;

import juja.microservices.gamification.security.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
@RestController
@RequestMapping(consumes = "application/json", produces = "application/json")
public final class AdminController {

    private final UserService service;

    private final JwtUtil util;

    @Inject
    public AdminController(final UserService service, final JwtUtil util) {
        this.service = service;
        this.util = util;
    }

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> authenticate(@RequestBody final LoginPassword credentials) {
        //todo check credentials from database
        final String login = credentials.login();
        final User user = service.getUserByUsername(login);
        final String token = util.generateToken(user);

        return ResponseEntity.ok(token);
    }

    @RequestMapping(value = "/admin/user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createUser(@RequestBody final User user) {
        final User createdUser = service.createUser(user);
        return ResponseEntity.ok(createdUser);
    }
}
