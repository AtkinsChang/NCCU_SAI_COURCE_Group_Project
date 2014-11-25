package edu.nccu.plsm.saiproject.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Stateless
@Path("/")
public class MessageBoardRootResource {

    @EJB
    MessageBoardResourceBean r;

    @Path("messages")
    public MessageBoardResourceBean getMessageBoardResourceBean() {
        return r;
    }
}

