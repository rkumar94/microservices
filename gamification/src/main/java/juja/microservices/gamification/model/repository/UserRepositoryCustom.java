package juja.microservices.gamification.model.repository;

import juja.microservices.gamification.model.entity.User;
import java.util.List;

public interface UserRepositoryCustom {

    String createUser(String username);

    User getUser(String uuid);

    List<User> getUsers();
}
