package juja.microservices.gamification.jersey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.juja.gamification.model.entity.User;
import ua.com.juja.gamification.service.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/user")
public class UserControllerImpl implements UserController{

    @Autowired
    private Service service;

    @GET
    @Path("/create/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String createUser(@PathParam("username")String userName) {
        return service.createUser(userName);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/find/{UUID}")
    @Override
    public User getUser(@PathParam("UUID")String UUID) {
        return service.getUser(UUID);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    @Override
    public List<User> getUsers() {
        return service.getUsers();
    }

}
