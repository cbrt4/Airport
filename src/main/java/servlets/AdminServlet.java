package servlets;

import entities.FlightEntity;
import repository.FlightRepository;
import util.AdminValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private AdminValidator validator = new AdminValidator();

    private FlightRepository repository = new FlightRepository();

    private Comparator<FlightEntity> sortByTime = Comparator.comparing(FlightEntity::getTime);
    private Comparator<FlightEntity> sortByFlightNumber = Comparator.comparing(FlightEntity::getFlightNumber);
    private Comparator<FlightEntity> sortByWaypoint = Comparator.comparing(FlightEntity::getWaypoint);
    private Comparator<FlightEntity> sortByTerminal = Comparator.comparing(FlightEntity::getTerminal);

    private Predicate<FlightEntity> directionFilter(final int directionType) {
        return flightEntity -> flightEntity.getDirectionType() == directionType;
    }
    private Predicate<FlightEntity> dateFilter(final LocalDate beginDate, final LocalDate endDate) {
        return flightEntity -> beginDate.minusDays(1).isBefore(flightEntity.getDate()) && endDate.plusDays(1).isAfter(flightEntity.getDate());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!validator.validate(request))
            request.getRequestDispatcher("login.jsp").forward(request, response);

        List<FlightEntity> flightList = repository.getAll();

        if (request.getParameter("sort") != null) sort(request, response);

        request.setAttribute("list", flightList);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("filter".equals(request.getParameter("action")) && !"".equals(request.getParameter("directionType")) ||
                "filter".equals(request.getParameter("action"))) filter(request, response);
        else doGet(request, response);
    }

    private void filter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FlightEntity> flightList = repository.getAll();

        String direction = request.getParameter("directionType");
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");

        flightList = flightList
                .stream()
                .filter(!direction.equals("") ? directionFilter(Byte.parseByte(direction)) : dateFilter(LocalDate.parse(beginDate), LocalDate.parse(endDate)))
                .filter(!(beginDate.equals("") && endDate.equals("")) ? dateFilter(LocalDate.parse(beginDate), LocalDate.parse(endDate)) : directionFilter(Byte.parseByte(direction)))
                .collect(Collectors.toList());

        request.setAttribute("list", flightList);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    private void sort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sort = request.getParameter("sort");
    }
}
