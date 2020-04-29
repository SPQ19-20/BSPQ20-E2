package es.deusto.BSPQ20_E2.Netflix.server;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;

import es.deusto.BSPQ20_E2.Netflix.client.Client;
import es.deusto.BSPQ20_E2.Netflix.pojo.User;
/**
 * 
 * @author Jorge
 *
 */
@Path("/users")
public class Users {
	private final static Logger LOGGER = Logger.getLogger(Users.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        users.add(new User("jorge", "Jorge", "El Busto", "password123", 90));
        return users;    
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user) {
    	LOGGER.info("Received new user: " + user);
    }

    @DELETE
    @Path("/{code}")
    public Response deleteUser(@PathParam("code") int code) {
        if (code == 10) {
           LOGGER.info("Deleting user...");
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}