package fi.jyu.tietokonekauppa.web.controllers.common;

import org.springframework.context.annotation.Profile;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
    private ProfileService profileService = new ProfileService();
    @Context
    private SecurityContext securityContext;
    @GET
    @Path("/{profileName}")
    public Profile getProfile(@PathParam("profileName") String profileName){
        if (!securityContext.isUserInRole("admin")){
            throw new WebApplicationException("Not authorized", 401);
        }
        return profileService.getProfile(profileName);
    }
}