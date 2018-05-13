package edu.csula.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.mysql.Database;
import edu.csula.storage.mysql.GeneratorsDAOImpl;
import edu.csula.storage.servlet.UsersDAOImpl;
// import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;


@WebServlet("/admin/generators")
public class AdminGeneratorsServlet extends HttpServlet {

	private static final long serialVersionUID = 3075787500147170531L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UsersDAOImpl user = new UsersDAOImpl(request.getSession());

		if (!user.getAuthenticatedUser().isPresent()) {
			response.sendRedirect(request.getContextPath() + "/admin/auth");
		}

		GeneratorsDAOImpl dao = new GeneratorsDAOImpl(new Database());
		Collection<Generator> generators = dao.getAll();
		request.setAttribute("generators", generators);

		request.getRequestDispatcher("/WEB-INF/admin-generators.jsp")
			.forward(request, response);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		String gen_name = request.getParameter("generator_name"), 
			gen_description = request.getParameter("description");

		int gen_rate = parseIntSafe(request.getParameter("generator_rate")),
			gen_base_cost = parseIntSafe(request.getParameter("base_cost")),
			gen_unlock = parseIntSafe(request.getParameter("unlocks_at"));

		GeneratorsDAOImpl dao = new GeneratorsDAOImpl(new Database());
		Collection<Generator> generators = dao.getAll();
		
		dao.add(new Generator(generators.size() + 1, 
								gen_name, 
								gen_description, 
								gen_rate, 
								gen_base_cost, 
								gen_unlock));		
		
		generators = dao.getAll();
		request.setAttribute("generators", generators);

		request.getRequestDispatcher("/WEB-INF/admin-generators.jsp")
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

