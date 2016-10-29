package juja.microservices.gamification.user;

import juja.microservices.gamification.security.jwt.JwtUtil;
import org.springframework.http.HttpStatus;
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

    private final UserService userService;
    private final LoginPasswordService loginPasswordService;
    private final JwtUtil util;

    @Inject
    public AdminController(final UserService userService, final LoginPasswordService loginPasswordService,
                           final JwtUtil util) {
        this.userService = userService;
        this.loginPasswordService = loginPasswordService;
        this.util = util;
    }

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> authenticate(@RequestBody final LoginPassword credentials) {
        final ResponseEntity<?> response;
        final String login = credentials.getLogin();
        final LoginPassword loginPassword = loginPasswordService.getLoginPassword(login);
        if (loginPassword == null) {
            response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            final User user = userService.getUserByUsername(login);
            final String token = util.generateToken(user);
            response = ResponseEntity.ok(token);
        }
        return response;
    }

    @RequestMapping(value = "/admin/user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createUser(@RequestBody final User user) {
        final User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }
}
