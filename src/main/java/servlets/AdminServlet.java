package servlets;

import entities.FlightEntity;
import repository.FlightRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private FlightRepository repository = new FlightRepository();

    private Comparator<FlightEntity> sortByTime = Comparator.comparing(FlightEntity::getTime);
    private Comparator<FlightEntity> sortByFlightNumber = Comparator.comparing(FlightEntity::getFlightNumber);
    private Comparator<FlightEntity> sortByWaypoint = Comparator.comparing(FlightEntity::getWaypoint);
    private Comparator<FlightEntity> sortByTerminal = Comparator.comparing(FlightEntity::getTerminal);
    private Comparator<FlightEntity> sortByGate = Comparator.comparing(FlightEntity::getGate);

    private Predicate<FlightEntity> directionFilter(final int directionType) {
        return flightEntity -> flightEntity.getDirectionType() == directionType;
    }
    private Predicate<FlightEntity> dateFilter(final LocalDate localDate) {
        return flightEntity -> flightEntity.getDate().equals(localDate);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FlightEntity> flightList = repository.getAll();

        if (request.getParameter("sort") != null) sort(request, response);

        request.setAttribute("list", flightList);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action").equals("filter") && !request.getParameter("directionType").equals("") && request.getParameter("directionType") != null ||
                request.getParameter("action").equals("filter") && !request.getParameter("date").equals("") && request.getParameter("date") != null) filter(request, response);
        else doGet(request, response);
    }

    private void filter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FlightEntity> flightList = repository.getAll();

        String direction = request.getParameter("directionType");
        String date = request.getParameter("date");

        flightList = flightList
                .stream()
                .filter(!direction.equals("") ? directionFilter(Integer.parseInt(direction)) : dateFilter(LocalDate.parse(date)))
                .filter(!date.equals("") ? dateFilter(LocalDate.parse(date)) : directionFilter(Integer.parseInt(direction)))
                .collect(Collectors.toList());

        request.setAttribute("list", flightList);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    private void sort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sort = request.getParameter("sort");
    }
}
