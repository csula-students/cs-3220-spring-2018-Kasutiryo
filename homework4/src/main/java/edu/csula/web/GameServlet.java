package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.models.Event;
import edu.csula.models.Generator;
import edu.csula.models.State;
import edu.csula.models.User;
import edu.csula.storage.mysql.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
	
	private static final long serialVersionUID = 93927219734009546L;

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		// PrintWriter out = response.getWriter();

		// User u = new User(0, "username", "password");

		Integer counter = 0;
		List<Generator> generators = new GeneratorsDAOImpl(new Database()).getAll();
		List<Event> story = new EventsDAOImpl(new Database()).getAll();

		State state = new State(counter, generators, story);

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String stateString = gson.toJson(state);

		request.setAttribute("state", stateString);

		request.getRequestDispatcher("/WEB-INF/game.jsp").forward(request, response);

		// out.println(jsonString);
	}
}
