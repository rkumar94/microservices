package juja.microservices.gamification.service;

import juja.microservices.gamification.model.entity.User;
import java.util.List;

public interface Service {

    String createUser(String username);

    User getUser(String uuid);

    List<User> getUsers();
}
