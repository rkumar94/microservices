package juja.microservices.gamification.jersey;

import javax.ws.rs.core.Response;
import java.util.List;
import juja.microservices.gamification.model.entity.User;

public interface UserController {

    String createUser(String name);

    Response getUser(String uuid);

    List<User> getUsers();

}
