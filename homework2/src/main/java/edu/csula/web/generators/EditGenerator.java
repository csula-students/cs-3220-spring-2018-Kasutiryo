package edu.csula.web.generators;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;


@WebServlet("/admin/generators/edit")
public class EditGenerator extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setAttribute("id", request.getParameter("id"));

            request.getRequestDispatcher("/WEB-INF/edit-generator.jsp")
                .forward(request, response);
        }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

         String gen_name = request.getParameter("generator_name"), 
			gen_description = request.getParameter("description");

		int gen_rate = parseIntSafe(request.getParameter("generator_rate")),
			gen_base_cost = parseIntSafe(request.getParameter("base_cost")),
            gen_unlock = parseIntSafe(request.getParameter("unlocks_at"));

		GeneratorsDAOImpl dao = new GeneratorsDAOImpl(getServletContext());
        Collection<Generator> generators = dao.getAll();
        int id = parseIntSafe(request.getParameter("id"));
        
        dao.set(id, new Generator(id, 
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