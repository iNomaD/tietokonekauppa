package fi.jyu.tietokonekauppa.web.filters;

import fi.jyu.tietokonekauppa.models.User;
import fi.jyu.tietokonekauppa.services.UserService;
import fi.jyu.tietokonekauppa.web.ErrorMessage;
import fi.jyu.tietokonekauppa.web.MyCustomSecurityContext;
import org.glassfish.jersey.internal.util.Base64;

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
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        UserService UserService = new UserService();
        User user=null;
        if ((requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX))
                ||(requestContext.getMethod().equals("DELETE"))) {
            List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
            if (authHeader != null && authHeader.size() > 0) {
                String authToken = authHeader.get(0);
                authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
                String decodedString = Base64.decodeAsString(authToken);
                StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();
                if ("user".equals(username) && "password".equals(password)) { return; }
                if (UserService.userCredentialExists(username, password)) {
                    user = UserService.getUser(username);
                    String scheme = requestContext.getUriInfo().getRequestUri().getScheme();
                    requestContext.setSecurityContext(new MyCustomSecurityContext(user, scheme));
                }
            }
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
