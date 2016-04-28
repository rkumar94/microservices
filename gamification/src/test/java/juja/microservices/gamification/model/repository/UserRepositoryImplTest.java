package juja.microservices.gamification.model.repository;

import juja.microservices.gamification.model.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoAction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.testng.annotations.BeforeMethod;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

public class UserRepositoryImplTest {

    @InjectMocks
    private UserRepositoryImpl repository;

    @Mock
    private MongoTemplate template;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createUser() throws Exception {
        String name = "name";
        User user = new User(name);
        String result = repository.createUser(name);
        Assert.assertEquals(user.getUsername(), result);
    }

    @Test
    public void getUserVerify(){
        String uuid = "uuid";
        Query query = Query.query(Criteria.where("uuid").is(uuid));
        repository.getUser(uuid);
        Mockito.verify(template, times(1)).findOne(query, User.class);
    }

    @Test
    public void getUsersVerify(){
        repository.getUsers();
        Mockito.verify(template, times(1)).findAll(User.class);
    }


}