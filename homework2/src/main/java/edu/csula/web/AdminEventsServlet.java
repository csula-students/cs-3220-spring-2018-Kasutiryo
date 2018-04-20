package edu.csula.web;

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

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {
	
	private static final long serialVersionUID = -3514842077727662933L;

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		EventsDAOImpl dao = new EventsDAOImpl(getServletContext());
		// Collection<Event> events = dao.getAll();

		request.getRequestDispatcher("/WEB-INF/admin-events.jsp").forward(request, response);
		
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String event_name = request.getParameter("event_name"),
			event_description = request.getParameter("event_description");

		int trigger_at = parseIntSafe(request.getParameter("trigger_at"));

		EventsDAOImpl dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
		
		dao.add(new Event(events.size(), 
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
