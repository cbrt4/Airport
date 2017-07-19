package servlets;

import entities.FlightEntity;
import repository.FlightRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    //TODO: Spring!
    private FlightRepository repository = new FlightRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/add.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("action").equals("add") &&
                !Objects.equals(request.getParameter("flightNumber"), "") &&
                !Objects.equals(request.getParameter("directionType"), "") &&
                !Objects.equals(request.getParameter("waypoint"), "") &&
                !Objects.equals(request.getParameter("time"), "") &&
                !Objects.equals(request.getParameter("terminal"), "") &&
                !Objects.equals(request.getParameter("gate"), "") &&
                !Objects.equals(request.getParameter("boardId"), "")) {

            FlightEntity flightEntity = new FlightEntity();

            flightEntity.setFlightNumber(request.getParameter("flightNumber"));
            flightEntity.setDirectionType(Byte.parseByte(request.getParameter("directionType")));
            flightEntity.setWaypoint(request.getParameter("waypoint"));
            flightEntity.setTime(LocalTime.parse(request.getParameter("time")));
            flightEntity.setTerminal(request.getParameter("terminal"));
            flightEntity.setGate(Integer.parseInt(request.getParameter("gate")));
            flightEntity.setBoardId(Integer.parseInt(request.getParameter("boardId")));

            /*LocalDateTime now = LocalDateTime.now();
            preparedStatement.setTimestamp(5, Timestamp.valueOf(now.with(flightEntity.getLeavingTime())));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(now.with(flightEntity.getArrivalTime())));*/

            repository.add(flightEntity);
        } else doGet(request, response);

        response.sendRedirect("info");
    }
}
