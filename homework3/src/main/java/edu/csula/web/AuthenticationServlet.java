package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.DomainLoadStoreParameter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.models.User;
import edu.csula.storage.UsersDAO;

@WebServlet("/admin/auth")
public class AuthenticationServlet extends HttpServlet {

	private static final long serialVersionUID = -5782467606525808080L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doDelete(request, response);
		
		request.getRequestDispatcher("/WEB-INF/admin-authentication.jsp")
			.forward(request, response);
	}

	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// TODO: handle login
		UsersDAOImpl dao = new UsersDAOImpl(request.getSession());

		String username = request.getParameter("username"),
			password = request.getParameter("password");

		if(dao.authenticate(username, password)) {
			request.getRequestDispatcher("/WEB-INF/admin-events.jsp")
				.forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/admin-authentication-error.jsp")
				.forward(request, response);
		}


	}

    @Override
	public void doDelete( HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// TODO: handle logout
		UsersDAOImpl dao = new UsersDAOImpl(request.getSession());

		dao.logout();

		request.getRequestDispatcher("/WEB-INF/login.jsp")
			.forward(request, response);

    }
}
