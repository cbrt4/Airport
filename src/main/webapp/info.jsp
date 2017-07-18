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

        th {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body style="font-family: 'Noto Sans',serif">
<H1 align="center"><a href="info">FLIGHT LIST</a></H1>
<hr>

<form action="info" method="post">
    <table cellpadding="10" cellspacing="0" align="center" style="border-collapse: collapse">
        <tr align="center">
            <td width=50%>
                <button name="filter" value="false" class="btn btn-danger">
                    Leaving
                </button>
            </td>
            <td width=50%">
                <button name="filter" value="true" class="btn btn-primary">
                    Arriving
                </button>
            </td>
        </tr>
    </table>
</form>
<hr>
<%------------------------------------<<    T A B L E    >>-------------------------------------------------%>
<table>
    <thead>
    <tr>
        <th>Flight Number</th>
        <th>Direction</th>

        <th>From</th>
        <th><a href="info?&sort=leaving" style="color: white">Leaving time</a></th>

        <th>To</th>
        <th><a href="info?&sort=arrival" style="color: white">Arrival time</a></th>

        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>

    <jsp:useBean id="list" scope="request" type="java.util.List"/>
    <c:forEach var="flight" items="${list}">
        <jsp:useBean id="flight" type="entities.FlightEntity"/>

        <tr align="center" style="background-color:${flight.directionType == 1 ? '#f2f2f2' : 'white'}">
            <td>${flight.flightNumber}</td>
            <td>
                <b style="color:${flight.directionType ==1 ? '#337ab7' : '#d9534f'}">${flight.directionType == 1 ? 'Arriving': 'Leaving'}</b>
            </td>

            <td><b>${flight.leavingFrom}</b></td>
            <td>${flight.leavingTime}</td>

            <td><b>${flight.arrivalTo}</b></td>
            <td>${flight.arrivalTime}</td>

            <td><a href="update?&id=${flight.id}" class="btn btn-primary">Update</a></td>
            <td><a href="delete?&id=${flight.id}" class="btn btn-danger">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<br>

<p align="center">
    <a href="add" class="btn btn-primary">Add new flight</a>
</p>

<hr>

</body>
</html>