package edu.nccu.plsm.saiproject.resources;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @version 0.0.3
 * @since 0.0.3
 */
@WebServlet(name = "Session", urlPatterns = {"/session"})
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(5 * 60);
        PrintWriter w = resp.getWriter();
        w.println(session.getId());
        w.close();
    }
}
