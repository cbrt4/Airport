package servlets;

import entities.FlightEntity;
import repository.FlightRepository;
import util.AdminValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

    private AdminValidator validator = new AdminValidator();

    private FlightRepository repository = new FlightRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (!validator.validate(request))
            request.getRequestDispatcher("login.jsp").forward(request, response);

        int id = Integer.parseInt(request.getParameter("id"));

        FlightEntity flightEntity = repository.get(id);
        request.setAttribute("flight", flightEntity);
        request.getRequestDispatcher("update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("action").equals("update") &&
                !Objects.equals(request.getParameter("date"), "") &&
                !Objects.equals(request.getParameter("time"), "") &&
                !Objects.equals(request.getParameter("flightNumber"), "") &&
                !Objects.equals(request.getParameter("directionType"), "") &&
                !Objects.equals(request.getParameter("waypoint"), "") &&
                !Objects.equals(request.getParameter("terminal"), "") &&
                !Objects.equals(request.getParameter("boardId"), "")) {

            FlightEntity flightEntity = new FlightEntity();

            flightEntity.setId(Integer.parseInt(request.getParameter("id")));
            flightEntity.setDate(LocalDate.parse(request.getParameter("date")));
            flightEntity.setTime(LocalTime.parse(request.getParameter("time")));
            flightEntity.setFlightNumber(request.getParameter("flightNumber"));
            flightEntity.setDirectionType(Byte.parseByte(request.getParameter("directionType")));
            flightEntity.setWaypoint(request.getParameter("waypoint"));
            flightEntity.setTerminal(request.getParameter("terminal"));
            flightEntity.setBoardId(Integer.parseInt(request.getParameter("boardId")));

            repository.update(flightEntity);
        } else doGet(request, response);

        response.sendRedirect("admin");
    }
}