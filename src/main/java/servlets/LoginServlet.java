package servlets;

import util.AdminValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AdminValidator validator = new AdminValidator();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if ("logout".equals(request.getParameter("action"))) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("login", null);
            httpSession.setAttribute("password", null);
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        httpSession.setMaxInactiveInterval(120);
        if ("login".equals(request.getParameter("action"))) {
            httpSession.setAttribute("login", request.getParameter("login"));
            httpSession.setAttribute("password", request.getParameter("password"));
        } else doGet(request, response);

        if (!validator.validate(request))
            request.getRequestDispatcher("login.jsp").forward(request, response);
        else response.sendRedirect("admin");
    }
}
