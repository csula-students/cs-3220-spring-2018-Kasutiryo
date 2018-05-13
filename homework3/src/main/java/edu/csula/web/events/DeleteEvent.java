package edu.csula.web.events;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.servlet.UsersDAOImpl;
//import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

@WebServlet("/admin/events/delete")
public class DeleteEvent extends HttpServlet {

    private static final long serialVersionUID = -1609230339897944145L;

	@Override
    public void doGet( HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            
    	UsersDAOImpl user = new UsersDAOImpl(request.getSession());

		if (!user.getAuthenticatedUser().isPresent()) {
			response.sendRedirect(request.getContextPath() + "/admin/auth");
		}
    	EventsDAOImpl dao = new EventsDAOImpl(getServletContext());
        Collection<Event> events = dao.getAll();
        int id = parseIntSafe(request.getParameter("id"));    

        dao.remove(id);

        events = dao.getAll();

        request.getRequestDispatcher("/WEB-INF/admin-events.jsp")
            .forward(request, response);
    }

    private int parseIntSafe(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return 0;
		}
	}
}