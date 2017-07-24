package servlets;

import repository.FlightRepository;
import util.AdminValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    private AdminValidator validator = new AdminValidator();

    private FlightRepository repository = new FlightRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (!validator.validate(request))
            request.getRequestDispatcher("login").forward(request, response);

        int id = Integer.parseInt(request.getParameter("id"));
        repository.remove(id);
        response.sendRedirect("admin");
    }
}