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

	private	String events_HTML = "";
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
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		//DELETE event that was clicked and update event table
		if (DELETE.equals(request.getParameter("action"))) {
			int id =  Integer.parseInt(request.getParameter("id"));
			dao.remove(id);
			renderEvents(request, response);
		}

		//RENDER HTML
		PrintWriter out = response.getWriter();
			 out.append("<!DOCTYPE html>") 
				.append("<html lang='eng'>") 
				.append("<head>") 
				.append(	"<meta charset='UTF-8'>") 
				.append(	"<title>admin-events</title>")
				.append(	"<link rel='stylesheet' href='" + request.getContextPath() + "/app.css'>")
				.append(	"<h1>")
				.append(		"World of Coins Framework - Events")
				.append(	"</h1>")
				.append("</head>")
				.append("<nav class='nav'>")
				.append(	"<a href='admin-info.html'>Information</a> | ")
				.append(	"<a href='/admin/events'>Events</a> | ")
				.append(	"<a href='admin-generators.html'>Generators</a>")
				.append("</nav>")
				.append("<main>")
				.append(	"<div class='content'>")
				.append(		"<div class='forms'>")
				.append(			"<form action='"+request.getContextPath()+"/admin/events' method='post'>")
				.append(				"<label>Event ID(USE ONLY IF EDITTING AN EVENT): </label>")
				.append(				"<input type='number' name='event_id'>")
				.append(				"<label>Event Name: </label>")
				.append(				"<input type='text' name='event_name'>")
				.append(				"<label>Event Description: </label>")
				.append(				"<input type='text' name='event_description'>")
				.append(				"<label>Trigger At: </label>")
				.append(				"<input type='number' name='trigger_at'>")
				.append(				"<input type='submit' name='post' value='add'>")
				.append(				"<input type='submit' name='post' value='edit'>")
				.append(			"</form>")		
				.append(		"</div>")
				.append(		"<table class='table'>")
				.append(			"<tr class='header'>")
				.append(				"<th>ID</th>") 
				.append(				"<th>Event Name</th>") 
				.append(				"<th>Event Description</th>")
				.append(				"<th>Triggered At</th>")		
				.append(				"<th>Actions</th>") 
				.append(			"</tr>") 
				.append(			events_HTML) 
				.append(		"</table>") 
				.append(	"</div>" )
				.append("</main>")
				.append("</html>");
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO: handle upsert transaction
		//ADD new event
		String event_name = request.getParameter("event_name");
		String event_description = request.getParameter("event_description");
		int id = -1;
		try {
			id = Integer.parseInt(request.getParameter("event_id"));
		} catch (Exception e) {
			System.out.println("empty id");
		}
		int trigger_at = Integer.parseInt(request.getParameter("trigger_at"));
		if(ADD.equals(request.getParameter("post"))) {
			System.out.println(ADD + " = " + request.getParameter("post"));
			try {
				dao.add(new Event(events.size(), event_name, event_description, trigger_at));
				events = dao.getAll();
				renderEvents(request, response);
				response.sendRedirect(request.getContextPath() + "/admin/events");
			} catch (Exception e) {

				System.out.println("Values for new event are blank!");
			}
		} else if (EDIT.equals(request.getParameter("post"))) {
			System.out.println(EDIT + " = " + request.getParameter("post"));
			try {
				dao.set(id, new Event(id, event_name, event_description, trigger_at));
				events = dao.getAll();
				renderEvents(request, response);
				response.sendRedirect(request.getContextPath() + "/admin/events");	
			} catch (Exception e) {
				//TODO: handle exception
				System.out.println("Values for new event are blank!");
			}
		}
			
	}

	private void renderEvents( HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		events_HTML = "";
		for(Event item: events) {
			events_HTML +=  
			"<tr>" +
				"<td>" + item.getId() + "</td>" +
				"<td>" + item.getName() +  "</td>"  +
				"<td>" + item.getDescription() + "</td>" +  
				"<td>" + item.getTriggerAt() + "</td>"  +
				"<td><a href='"+request.getContextPath()+"/admin/events?id=" + item.getId() + "&action=delete'>Delete</a>" +
			"</tr>";
		}
	}
}
