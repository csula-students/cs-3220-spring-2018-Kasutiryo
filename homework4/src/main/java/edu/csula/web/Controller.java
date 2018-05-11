package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.models.Event;
import edu.csula.models.Generator;
import edu.csula.models.State;
import edu.csula.models.User;
import edu.csula.storage.mysql.Database;
import edu.csula.storage.mysql.EventsDAOImpl;
import edu.csula.storage.mysql.GeneratorsDAOImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/game")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 3567078508349447048L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
        
        response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		Integer counter = 0;
		List<Generator> generators = new GeneratorsDAOImpl(new Database()).getAll();
		List<Event> story = new EventsDAOImpl(new Database()).getAll(); 

		State state = new State(counter, generators, story);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String jsonString = gson.toJson(state);

		request.setAttribute("state", jsonString);

		request.getRequestDispatcher("WEB-INF/game.jsp")
			.forward(request, response);
    }
    
}