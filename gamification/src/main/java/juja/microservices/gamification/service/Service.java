package juja.microservices.gamification.service;

import ua.com.juja.gamification.model.entity.User;

import java.util.List;

public interface Service {

    String createUser(String userName);

    User getUser(String UUID);

    List<User> getUsers();
}
