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

@WebServlet("/info")
public class InfoServlet extends HttpServlet {

    private FlightRepository repository = new FlightRepository();

    private Comparator<FlightEntity> sortByTime = Comparator.comparing(FlightEntity::getTime);
    private Comparator<FlightEntity> sortByFlightNumber = Comparator.comparing(FlightEntity::getFlightNumber);
    private Comparator<FlightEntity> sortByWaypoint = Comparator.comparing(FlightEntity::getWaypoint);
    private Comparator<FlightEntity> sortByTerminal = Comparator.comparing(FlightEntity::getTerminal);
    private Comparator<FlightEntity> sortByGate = Comparator.comparing(FlightEntity::getGate);

    private Predicate<FlightEntity> arrivingFilter() {
        return flightEntity -> flightEntity.getDirectionType() == 1;
    }
    private Predicate<FlightEntity> leavingFilter() {
        return flightEntity -> flightEntity.getDirectionType() == 0;
    }
    private Predicate<FlightEntity> yesterdayFilter() {
        return flightEntity -> flightEntity.getDate().equals(LocalDate.now().minusDays(1));
    }
    private Predicate<FlightEntity> todayFilter() {
        return flightEntity -> flightEntity.getDate().equals(LocalDate.now());
    }
    private Predicate<FlightEntity> tomorrowFilter() {
        return flightEntity -> flightEntity.getDate().equals(LocalDate.now().plusDays(1));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("directionFilter") != null ||
                request.getParameter("dateFilter") != null ||
                request.getParameter("sort") != null) filterSort(request, response);
        else {
            List<FlightEntity> flightList = repository.getAll()
                    .stream()
                    .filter(leavingFilter())
                    .sorted(sortByTime)
                    .collect(Collectors.toList());
            request.setAttribute("direction", "leaving");
            request.setAttribute("date", "today");
            request.setAttribute("list", flightList);
            request.getRequestDispatcher("info.jsp").forward(request, response);
        }
    }

    private void filterSort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FlightEntity> flightList = repository.getAll();

        String directionFilter = request.getParameter("directionFilter");
        directionFilter = directionFilter != null ? directionFilter : "leave";

        String dateFilter = request.getParameter("dateFilter");
        dateFilter = dateFilter != null ? dateFilter : "today";

        String sort = request.getParameter("sort");
        sort = sort != null ? sort : "time";

        flightList = flightList
                .stream()
                .filter(directionFilter.equals("arrive") ? arrivingFilter() : leavingFilter())
                .filter(dateFilter.equals("today") ? todayFilter() : (dateFilter.equals("yesterday") ? yesterdayFilter() : tomorrowFilter()))
                .sorted(sort.equals("time") ? sortByTime : (sort.equals("flightNumber") ? sortByFlightNumber : (sort.equals("waypoint") ? sortByWaypoint : (sort.equals("terminal") ? sortByTerminal : sortByGate))))
                .collect(Collectors.toList());
        request.setAttribute("direction", directionFilter.equals("arrive") ? "arriving" : "leaving");
        request.setAttribute("date", dateFilter.equals("today") ? "today" : (dateFilter.equals("yesterday") ? "yesterday" : "tomorrow"));

        request.setAttribute("list", flightList);
        request.getRequestDispatcher("info.jsp").forward(request, response);
    }
}