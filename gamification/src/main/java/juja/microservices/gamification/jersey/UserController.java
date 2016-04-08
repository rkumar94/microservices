package juja.microservices.gamification.jersey;


import juja.microservices.gamification.model.entity.User;

import javax.ws.rs.core.Response;
import java.util.List;

public interface UserController {

    String createUser(String userName);

    Response getUser(String UUID);

    List<User> getUsers();

}
