package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {
	
	private static final long serialVersionUID = -3514842077727662933L;

	private	EventsDAOImpl dao;
	private Collection<Event> events;
	
	private final String ADD = "add";
	private final String EDIT = "edit";
	private final String DELETE = "delete";

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO: render the events page HTML
		dao = new EventsDAOImpl(getServletContext());
		events = dao.getAll();
		
		//DELETE event that was clicked and update event table
		if (DELETE.equals(request.getParameter("action"))) {
			int id =  Integer.parseInt(request.getParameter("id"));
			dao.remove(id);
			response.sendRedirect(request.getContextPath() + "/admin/events");	
		}

		//RENDER HTML
		request.getRequestDispatcher("/WEB-INF/admin-events.jsp").forward(request, response);
		
		
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO: handle upsert transaction
		//ADD new event
		String event_name = request.getParameter("event_name");
		String event_description = request.getParameter("event_description");
		int trigger_at = Integer.parseInt(request.getParameter("trigger_at"));
		int id = -1;

		try {
			id = Integer.parseInt(request.getParameter("event_id"));
		} catch (Exception e) {
			System.out.println("empty id");
		}
		
		if(ADD.equals(request.getParameter("post"))) {
			try {
				dao.add(new Event(events.size(), event_name, event_description, trigger_at));
				events = dao.getAll();
				response.sendRedirect(request.getContextPath() + "/admin/events");
			} catch (Exception e) {

				System.out.println("Values for new event are blank!");
			}
		} else if (EDIT.equals(request.getParameter("post"))) {
			try {
				dao.set(id, new Event(id, event_name, event_description, trigger_at));
				events = dao.getAll();
				response.sendRedirect(request.getContextPath() + "/admin/events");
			} catch (Exception e) {
				System.out.println("Values for new event are blank!");
			}
		}
			
	}
}
