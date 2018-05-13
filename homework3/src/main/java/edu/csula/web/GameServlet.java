package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.models.Event;
import edu.csula.models.Generator;
import edu.csula.models.State;
import edu.csula.models.User;

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

		Generator[] generators = {new Generator(0, "FARMER", "You hire a worker to tend to your crops. Your " + 
												"worker will then harvest crops that are ready to sell to people." + 
												"But they don't sell for much.", 1, 10, 10),
								new Generator(1, "HUNTER", "You hire a experienced hunter to go out and kill monsters " + 
								"and other wild entities. The hunter will gather their spoils and sell " +
								"them in village for you. They are work a reasonable amount of coins.", 2, 15, 20),
								new Generator(2, "THIEVE", "You hire a theive to go out to villages and steal from any " +
								"civilian they can find. A big risk for a big win.", 3, 25, 30)};

		Event[] story = {new Event(0, "FARMER", "Picking crops...", 10),
						new Event(1, "HUNTER", "Hunting for wild animals...", 15),
						new Event(2, "THIEVE", "Pickpocketing random people with heavy pockets...", 25)};


		State state = new State(counter, generators, story);

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String stateString = gson.toJson(state);

		request.setAttribute("state", stateString);

		request.getRequestDispatcher("/WEB-INF/game.jsp").forward(request, response);

		// out.println(jsonString);
	}
}
