package juja.microservices.gamification.model.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.juja.gamification.model.entity.User;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom{

    String createUser(String userName);

    User getUser(String UUID);

    List<User> getUsers();
}
