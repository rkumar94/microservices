package juja.microservices.gamification.jersey;

import juja.microservices.gamification.model.entity.User;
import juja.microservices.gamification.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/user")
public class UserControllerImpl implements UserController {

    @Autowired
    private Service service;

    @GET
    @Path("/create/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String createUser(@PathParam("username")String userName) {
        return this.service.createUser(userName);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/find/{UUID}")
    @Override
    public Response getUser(@PathParam("UUID")String UUID) {
        final User user = this.service.getUser(UUID);
        if(user != null){
            return Response.ok(user).build();
        } else {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(String.format("Can't find user with {%S} UUID", UUID))
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    @Override
    public List<User> getUsers() {
        return this.service.getUsers();
    }

}
