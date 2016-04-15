package juja.microservices.gamification.model.repository;

import juja.microservices.gamification.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String createUser(String username) {
        User user = new User(username);
        mongoTemplate.save(user);
        return user.toString();
    }

    @Override
    public User getUser(String uuid) {
        Query query = Query.query(Criteria.where("uuid").is(uuid));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public List<User> getUsers() {
        return mongoTemplate.findAll(User.class);
    }
}
