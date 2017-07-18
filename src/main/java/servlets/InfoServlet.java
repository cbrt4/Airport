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

        List<FlightEntity> flightList = repository.getAll();

        String sort = request.getParameter("sort");

        if (sort != null) {
            flightList = flightList.stream()
                    .sorted(Objects.equals(sort, "arrival") ? sortByArrival : sortByLeaving)
                    .collect(Collectors.toList());
        }

        request.setAttribute("list", flightList);
        request.getRequestDispatcher("info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("'/info'  ->  'DO POST' method:");

        if (request.getParameter("filter") != null) filterSort(request, response);
    }

    private void filterSort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FlightEntity> flightList = repository.getAll();

        String filter = request.getParameter("filter");

        if (filter != null)
            flightList = flightList.stream()
                    .filter(flightEntity ->
                            flightEntity.getDirectionType() == (Boolean.parseBoolean(filter) ? 1 : 0))
                    .sorted(Boolean.parseBoolean(filter) ? sortByArrival : sortByLeaving)
                    .collect(Collectors.toList());

        request.setAttribute("list", flightList);
        request.getRequestDispatcher("info.jsp").forward(request, response);
    }

    private Comparator<FlightEntity> sortByArrival = Comparator.comparing(FlightEntity::getArrivalTime);

    private Comparator<FlightEntity> sortByLeaving = Comparator.comparing(FlightEntity::getLeavingTime);
}