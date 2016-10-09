package juja.microservices.gamification.user;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author viktor email kuchin.victor@gmail.com
 */
@RestController
@RequestMapping(consumes = "application/json", produces = "application/json")
public class AdminController {

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    @ResponseBody
    public String authenticate(@RequestBody final LoginPassword credentials) {
        return "OK";
    }
}
