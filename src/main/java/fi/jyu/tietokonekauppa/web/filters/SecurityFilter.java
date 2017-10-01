package fi.jyu.tietokonekauppa.web.filters;

import fi.jyu.tietokonekauppa.models.User;
import fi.jyu.tietokonekauppa.services.JWTService;
import fi.jyu.tietokonekauppa.services.UserService;
import fi.jyu.tietokonekauppa.web.controllers.common.models.ErrorMessage;
import fi.jyu.tietokonekauppa.web.ApplicationSecurityContext;
import org.glassfish.jersey.internal.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX_BASIC = "Basic";
    private static final String AUTHORIZATION_HEADER_PREFIX_JWT = "Bearer";
    private static final String SECURED_URL_PREFIX = "admin/";

    @Autowired
    UserService userService;
    @Autowired
    JWTService jwtService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        User user = null;
        String authorizationHeader = requestContext.getHeaderString(AUTHORIZATION_HEADER_KEY);
        if(authorizationHeader != null) {
            if (authorizationHeader.contains(AUTHORIZATION_HEADER_PREFIX_BASIC)) {
                user = authBasic(authorizationHeader.substring(AUTHORIZATION_HEADER_PREFIX_BASIC.length()).trim());

            } else if (authorizationHeader.contains(AUTHORIZATION_HEADER_PREFIX_JWT)) {
                user = authJWT(authorizationHeader.substring(AUTHORIZATION_HEADER_PREFIX_JWT.length()).trim());
            }
        }

        String scheme = requestContext.getUriInfo().getRequestUri().getScheme();
        requestContext.setSecurityContext(new ApplicationSecurityContext(user, scheme));

        if ((requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX))){
            // TODO implement tokens for admin panel
            /*
            if(user == null || !user.getRole().contains(User.ADMIN)){
                List<String> errors = new ArrayList<String>(){{ add("Access to "+SECURED_URL_PREFIX+" not allowed");}};
                ErrorMessage errorMessage = new ErrorMessage("error", errors, null);
                Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
                        .entity(errorMessage)
                        .build();
                requestContext.abortWith(unauthorizedStatus);
            }
            */
        }
    }

    private User authBasic(String token){
        String decodedString = Base64.decodeAsString(token);
        StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
        String username = tokenizer.hasMoreElements() ? tokenizer.nextToken() : "";
        String password = tokenizer.hasMoreElements() ? tokenizer.nextToken() : "";
        if (userService.userCredentialExists(username, password)) {
            return userService.getUser(username);
        }
        System.out.println("Verification Basic failed: "+decodedString);
        return null;
    }

    private User authJWT(String token){
        try{
            String login = jwtService.verifyToken(token);
            return userService.getUser(login);
        }
        catch (Exception e){
            System.out.println("Verification JWT failed: "+token);
        }
        return null;
    }
}
