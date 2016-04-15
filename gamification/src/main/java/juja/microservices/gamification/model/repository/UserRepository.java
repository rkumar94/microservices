package juja.microservices.gamification.model.repository;

import juja.microservices.gamification.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom{

    String createUser(String username);

    User getUser(String uuid);

    List<User> getUsers();
}
