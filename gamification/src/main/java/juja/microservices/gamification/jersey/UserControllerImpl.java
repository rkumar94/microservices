package juja.microservices.gamification.jersey;

import com.sun.jersey.core.spi.factory.ResponseImpl;
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
public class UserControllerImpl implements UserController{

    @Autowired
    private Service service;

    @GET
    @Path("/create/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String createUser(@PathParam("username")String userName) {
        if(userName!= null && !userName.isEmpty()){
            return service.createUser(userName);
        } else {
            return "userName is empty";
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/find/{UUID}")
    @Override
    public Response getUser(@PathParam("UUID")String UUID) {
        if(UUID!= null && !UUID.isEmpty()){
            return Response.ok(service.getUser(UUID)).build();
        } else {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("User UUID is empty")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    @Override
    public List<User> getUsers() {
        return service.getUsers();
    }

}
