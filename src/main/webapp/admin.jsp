<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<form action="admin" method="post">
    <%--left--%>
    <p align="center"><b>Direction filter: </b>
        <label>
            <select name="directionType">
                <option value="-1">All</option>
                <option value="0">Arrival</option>
                <option value="1">Leaving</option>
            </select>
        </label>
    </p>
    <%--right--%>
    <p align="center">
        <b>Begin date: </b>
        <label>
            <input type="date" name="beginDate" value="">
        </label>
        <b>End date: </b>
        <label>
            <input type="date" name="endDate" value="">
        </label>
    </p>
    <p align="center">
        <button name="action" value="filter" class="btn btn-primary">
            Confirm
        </button>
        <button name="action" value="skip" class="btn btn-danger">
            Skip all
        </button>
    </p>
</form>
<hr>
<table>
    <thead align="center">
    <tr>
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

        <th><a href="admin?sort=board"
               style="color: white">Board ID</a></th>

        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <c:forEach var="flight" items="${list}">
        <tr align="center">
            <td>${flight.date}</td>
            <td>${flight.time}</td>

            <td>${flight.flightNumber}</td>
            <td>${flight.directionType == 1 ? 'arrival from' : 'living to'}</td>

            <td>${flight.waypoint}</td>
            <td>${flight.terminal}</td>

            <td>${flight.boardId}</td>

            <td><a href="update?id=${flight.id}" class="btn btn-primary">Update</a></td>
            <td><a href="delete?id=${flight.id}" class="btn btn-danger">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<hr>

<p align="center">
    <a href="add" class="btn btn-primary">Add new flight</a>
</p>

<hr>

<p align="center">
    <a href="info" class="btn btn-default">Back to info</a>
    <a href="login?action=logout" class="btn btn-danger">Logout</a>
</p>

<hr>

</body>
</html>
