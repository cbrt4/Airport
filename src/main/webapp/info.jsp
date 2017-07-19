<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<%--<div>
    <span><ins></ins>

    </span>
    <span><ins></ins>

    </span>
</div>--%>
<table cellpadding="10" cellspacing="0" align="center" style="border-collapse: collapse">
    <tr align="center">
        <td width=50%>
            <a href="info?filter=leave" class="btn btn-danger">Leaving</a>
        </td>
        <td width=50%">
            <a href="info?filter=arrive" class="btn btn-primary">Arriving</a>
        </td>
    </tr>
</table>
<hr>
<%------------------------------------<<    T A B L E    >>-------------------------------------------------%>
<table>
    <thead>
    <jsp:useBean id="direction" scope="request" type="java.lang.String"/>
    <tr>
        <th>Flight Number</th>
        <th>${direction == "leaving" ? 'Destination' : 'Departure'}</th>

        <th>Time</th>
        <th><a href="info?sort=leaving" style="color: white">Terminal</a></th>
        <th><a href="info?sort=arrival" style="color: white">Gate</a></th>

        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>

    <jsp:useBean id="list" scope="request" type="java.util.List"/>
    <c:forEach var="flight" items="${list}">
        <jsp:useBean id="flight" type="entities.FlightEntity"/>
        <tr align="center">
            <td>${flight.flightNumber}</td>
            <td>${flight.waypoint}</td>

            <td>${flight.time}</td>
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