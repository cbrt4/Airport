package servlets;

import entities.FlightEntity;
import repository.FlightRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet("/info")
public class InfoServlet extends HttpServlet {

    private FlightRepository repository = new FlightRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("filter") != null ||
                request.getParameter("sort") != null) filterSort(request, response);
        else {
            List<FlightEntity> flightList = repository.getAll();
            request.setAttribute("list", flightList);
            request.getRequestDispatcher("info.jsp").forward(request, response);
        }
    }

    private void filterSort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FlightEntity> flightList = repository.getAll();

        String filter = request.getParameter("filter");

        if (filter != null)
            flightList = flightList.stream()
                    .filter(flightEntity ->
                            flightEntity.getDirectionType() == (Boolean.parseBoolean(filter) ? 1 : 0))
                    .sorted(Boolean.parseBoolean(filter) ? sortByArrivalTime : sortByLeavingTime)
                    .collect(Collectors.toList());

        String sort = request.getParameter("sort");

        if (sort != null) {
            flightList = flightList.stream()
                    .sorted(Objects.equals(sort, "arrival") ? sortByArrivalTime : sortByLeavingTime)
                    .collect(Collectors.toList());
        }

        request.setAttribute("list", flightList);
        request.getRequestDispatcher("info.jsp").forward(request, response);
    }

    private Comparator<FlightEntity> sortByArrivalTime = Comparator.comparing(FlightEntity::getArrivalTime);

    private Comparator<FlightEntity> sortByLeavingTime = Comparator.comparing(FlightEntity::getLeavingTime);

    private Comparator<FlightEntity> sortByFlightNumber = Comparator.comparing(FlightEntity::getFlightNumber);
}