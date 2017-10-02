package fi.jyu.tietokonekauppa.web.controllers.common;


import fi.jyu.tietokonekauppa.models.User;
import fi.jyu.tietokonekauppa.services.JWTService;
import fi.jyu.tietokonekauppa.services.UserService;
import fi.jyu.tietokonekauppa.web.controllers.common.models.StringStatus;
import fi.jyu.tietokonekauppa.web.controllers.common.models.UserToken;
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

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

@Path("/users")
public class UserResource {

    @Autowired
    UserService userService;
    @Autowired
    JWTService jwtService;

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(@FormParam("login") String login, @FormParam("password") String password,
                               @FormParam("first_name") String firstName, @FormParam("last_name") String lastName,
                               @FormParam("email") String email){
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

        User user = userService.getUser(login);
        String token = jwtService.issueToken(login);
        UserToken userToken = new UserToken(user.getId(), login,
                user.getEmail(), user.getFirst_name(), user.getLast_name(), user.getRole(), token);
        System.out.println("Authorized: "+userToken);

        return Response.ok().header(AUTHORIZATION, "Bearer " + token).entity(userToken).build();
    }

    @POST
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(String token){
        jwtService.disableToken(token);
        return Response.ok().entity(new StringStatus("ok")).build();
    }
}
