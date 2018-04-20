package edu.csula.web.events;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.EventsDAOImpl;
// import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;


@WebServlet("/admin/events/edit")
public class EditEvent extends HttpServlet {

    private static final long serialVersionUID = -4319014623425198898L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setAttribute("id", request.getParameter("id"));

            request.getRequestDispatcher("/WEB-INF/edit-event.jsp")
                .forward(request, response);
        }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

            String event_name = request.getParameter("event_name"),
			event_description = request.getParameter("event_description");

		int trigger_at = parseIntSafe(request.getParameter("trigger_at"));

		EventsDAOImpl dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
        int id = parseIntSafe(request.getParameter("id"));
        
        dao.set(id, new Event(id, 
                                event_name, 
                                event_description, 
                                trigger_at));

        events = dao.getAll();

        request.setAttribute("events", events);

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