package edu.nccu.plsm.saiproject.resources;

import edu.nccu.plsm.saiproject.entities.Message;
import edu.nccu.plsm.saiproject.exceptions.NotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class MessageBoardResourceBean {

    @EJB
    MessageHolderSingletonBean singleton;
    @Context
    private UriInfo ui;

    @GET
    public List<Message> getMessages() {
        return singleton.getMessages();
    }

    @POST
    public Response addMessage(@FormParam("name") String name,
                               @FormParam("content") String msg,
                               @FormParam("email") String email,
                               @HeaderParam("Referer") String from) throws URISyntaxException {
        if(name == null || name.length() == 0
                || msg == null || msg.length() == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Message m = singleton.addMessage(name, email, msg);
        URI msgURI = ui.getRequestUriBuilder().path(Long.toString(m.getUniqueId())).build();
        return Response.created(msgURI).build();
    }

    @Path("all")
    @GET
    public List<String> getMessage() throws NotFoundException {
        return singleton.getMessages().stream().map(Message::toHtml).collect(Collectors.toList());
    }

    @Path("{msgNum}")
    @GET
    public Message getMessage(@PathParam("msgNum") long msgNum) throws NotFoundException {
        Message m = singleton.getMessage(msgNum);
        if (m == null) {
            throw new NotFoundException();
        }
        return m;
    }

    @Path("{msgNum}")
    @DELETE
    public void deleteMessage(@PathParam("msgNum") int msgNum) throws NotFoundException {
        boolean deleted = singleton.deleteMessage(msgNum);
        if (!deleted) {
            throw new NotFoundException();
        }
    }
}





