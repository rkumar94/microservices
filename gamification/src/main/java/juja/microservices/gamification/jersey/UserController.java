package juja.microservices.gamification.jersey;

import ua.com.juja.gamification.model.entity.User;

import java.util.List;

public interface UserController {

    String createUser(String userName);

    User getUser(String UUID);

    List<User> getUsers();

}
