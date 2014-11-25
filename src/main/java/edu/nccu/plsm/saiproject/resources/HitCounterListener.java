package edu.nccu.plsm.saiproject.resources;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @version 0.0.3
 * @since 0.0.3
 */
@WebListener
public class HitCounterListener implements HttpSessionListener {

    private static AtomicLong totalActiveSessions = new AtomicLong(0L);
    private static AtomicLong totalCount = new AtomicLong(0L);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        totalActiveSessions.incrementAndGet();
        totalCount.incrementAndGet();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        totalActiveSessions.decrementAndGet();
    }

    public static long getTotalActiveSessions() {
        return totalActiveSessions.get();
    }

    public static long getTotalCount() {
        return totalCount.get();
    }
}
