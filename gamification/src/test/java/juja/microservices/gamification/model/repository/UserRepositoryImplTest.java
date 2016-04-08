package juja.microservices.gamification.model.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.Assert.*;

public class UserRepositoryImplTest {

    @InjectMocks
    UserRepositoryImpl repository;

    @Mock
    MongoTemplate mongoTemplate;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUser() throws Exception {

    }

    @Test
    public void testGetUsers() throws Exception {

    }
}