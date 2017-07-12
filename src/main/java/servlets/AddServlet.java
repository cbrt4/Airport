package servlets;

import entities.FlightEntity;
import repository.FlightRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
                !Objects.equals(request.getParameter("type"), "") &&
                !Objects.equals(request.getParameter("leavingFrom"), "") &&
                !Objects.equals(request.getParameter("arrivalTo"), "") &&
                !Objects.equals(request.getParameter("leavingTime"), "") &&
                !Objects.equals(request.getParameter("arrivalTime"), "")) {

            FlightEntity flightEntity = new FlightEntity();

            flightEntity.setFlightNumber(request.getParameter("flightNumber"));
            flightEntity.setDirectionType(Byte.parseByte(request.getParameter("type")));
            flightEntity.setLeavingFrom(request.getParameter("leavingFrom"));
            flightEntity.setArrivalTo(request.getParameter("arrivalTo"));
            flightEntity.setLeavingTime(LocalTime.parse(request.getParameter("leavingTime")));
            flightEntity.setArrivalTime(LocalTime.parse(request.getParameter("arrivalTime")));

            /*LocalDateTime now = LocalDateTime.now();
            preparedStatement.setTimestamp(5, Timestamp.valueOf(now.with(flightEntity.getLeavingTime())));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(now.with(flightEntity.getArrivalTime())));*/

            repository.add(flightEntity);
        } else doGet(request, response);

        response.sendRedirect("info");
    }
}
