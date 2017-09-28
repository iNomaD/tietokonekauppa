package fi.jyu.tietokonekauppa.web.controllers.common;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {

    //@Autowired
    //UserService userService;

    @GET
    @Path("/signup")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(@QueryParam("login") String login, @QueryParam("password") String password,
                               @QueryParam("first_name") String firstName, @QueryParam("last_name") String lastName,
                               @QueryParam("email") String email){
        // TODO implement
        return null;
    }

    @GET
    @Path("/signin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signIn(@QueryParam("login") String login, @QueryParam("password") String password){
        // TODO implement
        return null;
    }

    @GET
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(){
        // TODO implement
        return null;
    }
}
