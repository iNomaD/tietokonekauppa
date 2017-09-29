package fi.jyu.tietokonekauppa.web.controllers.common;


import fi.jyu.tietokonekauppa.models.User;
import fi.jyu.tietokonekauppa.services.UserService;
import fi.jyu.tietokonekauppa.web.StringStatus;
import fi.jyu.tietokonekauppa.web.exceptions.DataExistsException;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;
import fi.jyu.tietokonekauppa.web.exceptions.FormException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/users")
public class UserResource {

    @Autowired
    UserService userService;

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(@FormParam("login") String login, @FormParam("password") String password,
                               @FormParam("first_name") String firstName, @FormParam("last_name") String lastName,
                               @FormParam("email") String email){
        System.out.println(login);
        System.out.println(password);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        if(login == null || password == null || firstName == null || lastName == null || email == null){
            List<String> errors = new ArrayList<String>() {{ add("form exception"); }};
            Map<String, String[]> fields = new HashMap<String, String[]>() {{
                if(login == null) put("login", new String[]{"not provided"});
                if(password == null) put("password", new String[]{"not provided"});
                if(firstName == null) put("first_name", new String[]{"not provided"});
                if(lastName == null) put("last_name", new String[]{"not provided"});
                if(email == null) put("email", new String[]{"not provided"});
            }};
            throw new FormException(errors, fields);
        }

        try {
            User user = userService.signUp(login, password, firstName, lastName, email);
            if (user == null) {
                throw new DataNotFoundException("Could not create user");
            }
        } catch (DataExistsException e){
            throw e;
        }

        return Response.ok().entity(new StringStatus("ok")).build();
    }

    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signIn(@FormParam("login") String login, @FormParam("password") String password){
        System.out.println(login);
        System.out.println(password);
        if(login == null || password == null){
            List<String> errors = new ArrayList<String>() {{ add("form exception"); }};
            Map<String, String[]> fields = new HashMap<String, String[]>() {{
                if(login == null) put("login", new String[]{"not provided"});
                if(password == null) put("password", new String[]{"not provided"});
            }};
            throw new FormException(errors, fields);
        }

        if(!userService.userCredentialExists(login, password)){
            throw new DataNotFoundException("Wrong username or password");
        }

        return Response.ok().entity(new StringStatus("ok")).build();
    }

    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(){
        // TODO implement business logic

        return Response.ok().entity(new StringStatus("ok")).build();
    }
}
