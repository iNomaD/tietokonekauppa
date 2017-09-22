package fi.jyu.tietokonekauppa.web;

import fi.jyu.tietokonekauppa.models.Component;

import javax.ws.rs.core.UriInfo;

public class Utils {
    public static void addLinks(Component item, UriInfo uriInfo, Class adminController, Class commonController){
        String uri = uriInfo.getBaseUriBuilder().path(adminController)
                .path(Long.toString(item.getId()))
                .build()
                .toString();
        item.addLink(uri,"self");

        uri = uriInfo.getBaseUriBuilder().path(adminController)
                .path(commonController, "getCommentResource")
                .resolveTemplate("id", item.getId())
                .build()
                .toString();
        item.addLink(uri, "comments");
    }
}
