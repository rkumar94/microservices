package juja.microservices.gamification.jersey;

import juja.microservices.gamification.model.entity.User;
import juja.microservices.gamification.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    public final String createUser(@PathParam("username") final String username) {
        return this.service.createUser(username);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/find/{UUID}")
    @Override
    public final Response getUser( @PathParam("UUID") final String uuid) {
        final User user = this.service.getUser(uuid);
        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(String.format("Can't find user {%S} uuid", uuid))
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    @Override
    public final List<User> getUsers() {
        return this.service.getUsers();
    }

}
