package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.Component;
import fi.jyu.tietokonekauppa.models.Link;
import fi.jyu.tietokonekauppa.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

@Service
public class LinkService {
    @Autowired
    private LinkRepository linkRepository;

    public List<Link> getAll() {
        List<Link> result = new ArrayList<>();
        linkRepository.findAll().forEach(result::add);
        return result;
    }

    public Link get(long id) {
        return linkRepository.findOne(id);
    }

    public boolean isLinkExist(Link item) {
        return linkRepository.exists(item.getId());
    }

    public Link add(Link item) {
        item.setId(new Long(0));
        return linkRepository.save(item);
    }

    public Link update(Link item) {
        return linkRepository.save(item);
    }

    public void remove(long id) {
        linkRepository.delete(id);
    }

    public void addLinks(Component item, UriInfo uriInfo, Class adminController, Class commonController){
        String uri = uriInfo.getBaseUriBuilder().path(adminController)
                .path(Long.toString(item.getId()))
                .build()
                .toString();
        addLink(item, uri,"self");

        uri = uriInfo.getBaseUriBuilder().path(adminController)
                .path(commonController, "getCommentResource")
                .resolveTemplate("id", item.getId())
                .build()
                .toString();
        addLink(item, uri, "comments");
    }

    public void addLink(Component component, String url, String rel) {
        Link link = new Link(component, url, rel);
        link = linkRepository.save(link);
        List<Link> links = component.getLinks();
        if(links == null){
            links = new ArrayList<>();
        }
        links.add(link);
    }
}
