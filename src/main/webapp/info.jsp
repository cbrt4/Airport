<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="direction" scope="request" class="java.lang.String"/>
<jsp:useBean id="list" scope="request" type="java.util.List"/>
<jsp:useBean id="flight" scope="request" class="entities.FlightEntity"/>
<html>
<head><title>Airport-Title</title>
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
<body style="font-family: 'Noto Sans',serif">
<H1 align="center"><a href="info">FLIGHT LIST</a></H1>
<hr>

<table cellpadding="10" cellspacing="0" align="center" style="border-collapse: collapse">
    <tr align="center">
        <td width=50%">
            <a href="info?filter=arrive" class="${direction == "arriving" ? 'btn btn-primary' : 'btn btn-default'}">Arriving</a>
        </td>
        <td width=50%>
            <a href="info?filter=leave" class="${direction == "leaving" ? 'btn btn-primary' : 'btn btn-default'}">Leaving</a>
        </td>

    </tr>
</table>
<hr>
<%------------------------------------<<    T A B L E    >>-------------------------------------------------%>
<table>
    <thead>
    <tr>
        <th>Time</th>
        <th><a href="info?filter=${direction == "leaving" ? 'leave' : 'arrive'}&sort=flightNumber" style="color: white">Flight Number</a></th>
        <th><a href="info?filter=${direction == "leaving" ? 'leave' : 'arrive'}&sort=waypoint" style="color: white">${direction == "leaving" ? 'Destination' : 'Departure'}</a></th>
        <th><a href="info?filter=${direction == "leaving" ? 'leave' : 'arrive'}&sort=terminal" style="color: white">Terminal</a></th>
        <th><a href="info?filter=${direction == "leaving" ? 'leave' : 'arrive'}&sort=gate" style="color: white">Gate</a></th>

        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>

    <c:forEach var="flight" items="${list}">
        <tr align="center">
            <td>${flight.time}</td>
            <td>${flight.flightNumber}</td>
            <td><b>${flight.waypoint}</b></td>
            <td>${flight.terminal}</td>
            <td>${flight.gate}</td>

            <td><a href="update?id=${flight.id}" class="btn btn-primary">Update</a></td>
            <td><a href="delete?id=${flight.id}" class="btn btn-danger">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<hr>

<p align="center"><a href="add" class="btn btn-primary">Add new flight</a>
</p>

<hr>

</body>
</html>