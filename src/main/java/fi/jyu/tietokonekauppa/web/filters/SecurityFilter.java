package fi.jyu.tietokonekauppa.web.filters;

import fi.jyu.tietokonekauppa.models.User;
import fi.jyu.tietokonekauppa.services.UserService;
import fi.jyu.tietokonekauppa.web.ErrorMessage;
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
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
    private static final String SECURED_URL_PREFIX = "secured";
    @Autowired
    private UserService userService;
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        User user=null;
        List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
        if (authHeader != null && authHeader.size() > 0) {
            String authToken = authHeader.get(0);
            authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
            String decodedString = Base64.decodeAsString(authToken);
            StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
            String username = tokenizer.nextToken();
            String password = tokenizer.nextToken();
            if (userService.userCredentialExists(username, password)) {
                user = userService.getUser(username);
            }
        }
        String scheme = requestContext.getUriInfo().getRequestUri().getScheme();
        requestContext.setSecurityContext(new ApplicationSecurityContext(user, scheme));

        if ((requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX))) {
            if(user!=null) return;
            List<String> errors = new ArrayList<>();
            errors.add("User cannot access the resource.");
            ErrorMessage errorMessage = new ErrorMessage( "401",errors,null);
            Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
                    .entity(errorMessage)
                    .build();
            requestContext.abortWith(unauthorizedStatus);
        }
    }
}
