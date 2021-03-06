package edu.csula.web.generators;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.GeneratorsDAOImpl;
import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;


@WebServlet("/admin/generators/delete")
public class DeleteGenerator extends HttpServlet {

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            
        UsersDAOImpl user = new UsersDAOImpl(request.getSession());

        if (!user.getAuthenticatedUser().isPresent()) {
            response.sendRedirect(request.getContextPath() + "/admin/auth");
        }
    
    	GeneratorsDAOImpl dao = new GeneratorsDAOImpl(getServletContext());
        Collection<Generator> generators = dao.getAll();
        int id = parseIntSafe(request.getParameter("id"));    

        dao.remove(id);

        generators = dao.getAll();

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