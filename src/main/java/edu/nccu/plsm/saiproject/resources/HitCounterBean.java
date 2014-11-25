package edu.nccu.plsm.saiproject.resources;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @version 0.0.3
 * @since 0.0.3
 */
@Stateless
public class HitCounterBean {

    @Path("count")
    @GET
    public long getTotalCount() {
        return HitCounterListener.getTotalCount();
    }

    @Path("active")
    @GET
    public long getActiveCount() {
        return HitCounterListener.getTotalActiveSessions();
    }
}
