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
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@WebServlet("/info")
public class InfoServlet extends HttpServlet {

    private HashMap<String, String> filterSortStorage = new HashMap<>(5);

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
        request.setCharacterEncoding("UTF-8");

        List<FlightEntity> flightList = repository.getAll();

        filterSort(request, response);

        request.setAttribute("storage", filterSortStorage);
        request.setAttribute("list", flightList);
        request.getRequestDispatcher("info.jsp").forward(request, response);

    }

    private void filterSort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FlightEntity> flightList = repository.getAll();

        String directionFilter = request.getParameter("directionFilter");
        directionFilter = directionFilter != null ? directionFilter : "leave";

        String dateFilter = request.getParameter("dateFilter");
        dateFilter = dateFilter != null ? dateFilter : "today";

        String sort = request.getParameter("sort");
        sort = sort != null ? sort : "time";

        filterSortStorage.put("directionFilter", directionFilter);
        filterSortStorage.put("dateFilter", dateFilter);
        filterSortStorage.put("sort", sort);

        flightList = flightList
                .stream()
                .filter(directionFilter.equals("arrive") ? directionFilter(1) :
                        directionFilter(0))
                .filter(dateFilter.equals("today") ? dateFilter(LocalDate.now()) :
                        (dateFilter.equals("yesterday") ? dateFilter(LocalDate.now().minusDays(1)) :
                                dateFilter(LocalDate.now().plusDays(1))))
                .sorted(sort.equals("time") ? sortByTime : (sort.equals("flightNumber") ? sortByFlightNumber :
                        (sort.equals("waypoint") ? sortByWaypoint :
                                (sort.equals("gate") ? sortByGate :
                                        sortByTerminal))))
                .collect(Collectors.toList());

        request.setAttribute("storage", filterSortStorage);
        request.setAttribute("list", flightList);
        request.getRequestDispatcher("info.jsp").forward(request, response);
    }
}