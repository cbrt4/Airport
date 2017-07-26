<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="storage" scope="request" type="java.util.Map"/>
<jsp:useBean id="list" scope="request" type="java.util.List"/>
<jsp:useBean id="flight" scope="request" class="entities.FlightEntity"/>
<html>
<head>
    <title>Airport Admin</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>"/>
    <style type="text/css">
        table {
            border-collapse: collapse;
            margin: auto;
        }

        th, td {
            text-align: center;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2
        }

        th {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
<h1 align="center"><a href="admin">Admin page</a></h1>
<hr>
<form action="admin">
    <table>
        <tr style="background-color: white">
            <td><b>Direction:</b></td>
            <td>
                <label>
                    <select name="directionFilter">
                        <option value="${storage.get("directionFilter")}">
                            ${storage.get("directionFilter") == "all" ? 'All' : (storage.get("directionFilter") == "arrive" ? 'Arrival' : 'Leaving')}
                        </option>
                        <option value="all">All</option>
                        <option value="arrive">Arrival</option>
                        <option value="leave">Leaving</option>
                    </select>
                </label>
            </td>
        </tr>
        <tr style="background-color: white">
            <td><b>Begin date:</b></td>
            <td>
                <label>
                    <input type="date" name="beginDate" value="${storage.get("beginDate")}">
                </label>
            </td>
        </tr>
        <tr style="background-color: white">
            <td><b>End date:</b></td>
            <td>
                <label>
                    <input type="date" name="endDate" value="${storage.get("endDate")}">
                </label>
            </td>
        </tr>
    </table>
    <p align="center">
        <button class="btn btn-primary">
            Apply filter
        </button>
    </p>
</form>
<hr>
<table>
    <thead align="center">
    <tr>
        <th><a href="admin?sort=id"
               style="color: white">ID</a></th>
        <th><a href="admin?sort=date"
               style="color: white">Date</a></th>

        <th><a href="admin?sort=time"
               style="color: white">Time</a></th>
        <th><a href="admin?sort=flightNumber"
               style="color: white">Flight Number</a></th>

        <th><a href="admin?sort=direction"
               style="color: white">Direction</a></th>
        <th><a href="admin?sort=waypoint"
               style="color: white">Waypoint</a></th>

        <th><a href="admin?sort=terminal"
               style="color: white">Terminal</a></th>
        <th><a href="admin?sort=gate"
               style="color: white">Gate</a></th>

        <th><a href="admin?sort=board"
               style="color: white">Board ID</a></th>

        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <c:forEach var="flight" items="${list}">
        <tr align="center">
            <td>${flight.id}</td>
            <td>${flight.date}</td>

            <td>${flight.time}</td>
            <td>${flight.flightNumber}</td>

            <td>${flight.directionType == 1 ? 'arrival from' : 'living to'}</td>
            <td>${flight.waypoint}</td>

            <td>${flight.terminal}</td>
            <td>${flight.gate}</td>

            <td>${flight.boardId}</td>

            <td><a href="update?id=${flight.id}" class="btn btn-primary">Update</a></td>
            <td><a href="delete?id=${flight.id}" class="btn btn-danger">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<hr>

<p align="center">
    <a href="add" class="btn btn-primary">Add new flight</a>
    <a href="info" class="btn btn-default">Back to info</a>
</p>

<hr>

<p align="center">
    <a href="change-password" class="btn btn-primary">Change password</a>
    <a href="login?action=logout" class="btn btn-danger">Logout</a>
</p>

<hr>

</body>
</html>
