package hello;

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

@WebServlet("/helloJSP")
public class HelloJSP extends HttpServlet {

    @Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int a = parseIntSafe(request.getParameter("a"));
        int b = parseIntSafe(request.getParameter("b"));
        int sum = a + b;

        request.setAttribute("a", a);
        request.setAttribute("b", b);
        request.setAttribute("sum", sum);

        request.getRequestDispatcher("WEB-INF/sum.jsp").forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
 
    private int parseIntSafe(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            //TODO: handle exception
            return 0;
        }
    }
}