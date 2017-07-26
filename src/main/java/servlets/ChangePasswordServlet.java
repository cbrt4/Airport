package servlets;

import util.AdminValidator;
import util.PasswordChanger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/change-password")
public class ChangePasswordServlet extends HttpServlet {

    private AdminValidator validator = new AdminValidator();
    private PasswordChanger passwordChanger = new PasswordChanger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!validator.validate(request))
            request.getRequestDispatcher("login").forward(request, response);

        request.getRequestDispatcher("change-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();

        if ("change".equals(request.getParameter("action")) &&
                httpSession.getAttribute("login").equals(request.getParameter("oldLogin")) &&
                httpSession.getAttribute("password").equals(request.getParameter("oldPassword")) &&
                request.getParameter("newPassword").equals(request.getParameter("newPasswordConfirm"))) {
            httpSession.setAttribute("login", request.getParameter("newLogin"));
            httpSession.setAttribute("password", request.getParameter("newPassword"));
            passwordChanger.change(request);
        } else doGet(request, response);

        if (!validator.validate(request))
            request.getRequestDispatcher("login").forward(request, response);
        else response.sendRedirect("admin");
    }
}
