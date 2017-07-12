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

        tr:nth-child(even){background-color: #f2f2f2}
        th {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body style="font-family: 'Noto Sans',serif">
<H1 align="center"><a href="info">FLIGHT LIST</a></H1>
<hr>
<%------------------------------~~~~~~<<    F I L T E R S    >>~~~---------------------------------------------%>
<h2 align="center">Filters:</h2>
<form action="info" method="post">
    <table cellpadding="10" cellspacing="0" align="center" style="border-collapse: collapse">
        <tr>
            <%--left--%>
            <td width=50%>
                <p align="center">Type:</p>
                <label>
                    <input type="radio" name="typeFilter" value="all">
                </label> All <br>
                <label>
                    <input type="radio" name="typeFilter" value="true">
                </label> Arriving <br>
                <label>
                    <input type="radio" name="typeFilter" value="false">
                </label> Leaving
            </td>
            <%--right--%>
            <td width=50%>
                <p align="center">Sort:</p>
                <label>
                    <input type="radio" name="sortFilter" value="LeavingTime">
                </label> Leaving time <br>
                <label>
                    <input type="radio" name="sortFilter" value="ArrivalTime">
                </label> Arrival time
            </td>
        <tr align="center">
            <%--s k i p--%>
            <td width=50%>
                <button type="submit" name="action" value="skip" class="btn btn-danger">
                    Skip
                </button>
            </td>
            <%--s u b m i t--%>
            <td width=50%">
                <button type="submit" name="action" value="filter-sort" class="btn btn-primary">
                    Confirm
                </button>
            </td>
        </tr>
    </table>
</form>
<%------------------------------------<<  A D D       N E W  >>-------------------------------------------------%>
<hr>
<p align="center">
    <a href="add" class="btn btn-primary">Add new flight</a>
</p>
<%------------------------------------<<    T A B L E    >>-------------------------------------------------%>
<table>
    <thead><%--  head --%>
    <tr>
        <th>Flight Number</th>
        <th>Direction</th>

        <th>From</th>
        <th>Leaving time</th>

        <th>To</th>
        <th>Arrival time</th>

        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <%------------------------------------<<    R O W    >>-------------------------------------------------%>
    <jsp:useBean id="list" scope="request" type="java.util.List"/>
    <c:forEach var="flight" items="${list}">
        <jsp:useBean id="flight" scope="page" type="entities.FlightEntity"/>

        <tr align="center" style="background-color:${flight.directionType == 1 ? '#dcdcdc' : ''}">
            <td><p>${flight.flightNumber}</p></td>
            <td>
                <p style="color:${flight.directionType ==1 ? 'green' : 'red'}">${flight.directionType == 1 ? 'Arriving': 'Leaving'}</p>
            </td>

            <td><p><b>${flight.leavingFrom}</b></p></td>
            <td><p>${flight.leavingTime}</p></td>

            <td><b>${flight.arrivalTo}</b></td>
            <td><p>${flight.arrivalTime}</p></td>

            <td><a href="update?&id=${flight.id}" class="btn btn-primary">Update</a></td>
            <td><a href="delete?&id=${flight.id}" class="btn btn-danger">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<%--  << TABLE  --%>
<br>
<hr>
</body>
</html>