package juja.microservices.gamification.jersey;

import juja.microservices.gamification.model.entity.User;
import juja.microservices.gamification.service.Service;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.times;

public class UserControllerImplTest {

    @InjectMocks
    private UserControllerImpl controller;

    @Mock
    private Service service;

    @BeforeMethod
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserWithWrongUuid() throws Exception {
        String uuid = "1234";
        Mockito.when(service.getUser(uuid)).thenReturn(null);

        Response resp = controller.getUser(uuid);
        Response expected = Response.status(Response.Status.BAD_REQUEST)
                .entity(String.format("no user with %s uuid", uuid)).build();

        Assert.assertEquals(expected.getStatus(), resp.getStatus());
        Assert.assertEquals(expected.getEntity(), resp.getEntity());
    }

    @Test
    public void getUserWithCorrectUuid() throws Exception {
        String uuid = "1234";
        User user = new User();
        user.setUuid(uuid);
        Mockito.when(service.getUser(uuid)).thenReturn(user);

        Response resp = controller.getUser(uuid);
        Response expected = Response.ok(user).build();

        Assert.assertEquals(expected.getStatus(), resp.getStatus());
        Assert.assertEquals(expected.getEntity(), resp.getEntity());
    }

    @Test
    public void createUserVerify(){
        String userToString = "user{name:name}";
        String name = "name";
        Mockito.when(service.createUser(name)).thenReturn(userToString);
        controller.createUser(name);
        Mockito.verify(service, times(1)).createUser(name);
    }

    @Test
    public void getUsersVerify(){
        List<User> users = new ArrayList<>(Collections.singletonList(new User()));
        Mockito.when(service.getUsers()).thenReturn(users);
        List<User> result = controller.getUsers();
        Mockito.verify(service, times(1)).getUsers();
        Assert.assertEquals(users, result);
    }

}