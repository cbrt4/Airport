<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="flight" scope="request" class="entities.FlightEntity"/>
<html>
<head><title>Update current flight</title>
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
<h1 align="center">Update current flight</h1>
<hr>
<%------------------------------------<<    T A B L E    >>-------------------------------------------------%>
<form action="update" method="post">
    <table>
        <thead align="center">
        <tr>
            <th>Date</th>
            <th>Time</th>

            <th>Flight Number</th>
            <th>Direction</th>

            <th>Waypoint</th>
            <th>Terminal</th>

            <th>Gate</th>
            <th>Board ID</th>

            <th>Confirm</th>
            <th>Skip all</th>
        </tr>
        </thead>
        <%------------------------------------<<    R O W    >>-------------------------------------------------%>
        <tr>
            <input type="hidden" name="id" value="${flight.id}">

            <td>
                <label>
                    <input type="date" name="date" value="${flight.date}">
                </label>
            </td>
            <td>
                <label>
                    <input type="time" name="time" value="${flight.time}">
                </label>
            </td>
            <td>
                <label>
                    <input size="10" name="flightNumber" value="${flight.flightNumber}">
                </label>
            </td>
            <td>
                <label>
                    <select name="directionType">
                        <option value="${flight.directionType}">
                            ${flight.directionType == 1 ? 'Arriving' : 'Leaving'}
                        </option>
                        <option value="1">Arrival</option>
                        <option value="0">Leaving</option>
                    </select>
                </label>
            </td>

            <td>
                <label>
                    <input size="10" name="waypoint" value="${flight.waypoint}">
                </label>
            </td>
            <td>
                <label>
                    <select name="terminal">
                        <option value="${flight.terminal}">${flight.terminal}</option>
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                        <option value="D">D</option>
                    </select>
                </label>
            </td>
            <td>
                <label>
                    <input type="number" min="1" max="999" name="gate" value="${flight.gate}">
                </label>
            </td>
            <td>
                <label>
                    <input type="number" min="1" max="999" name="boardId" value="${flight.boardId}">
                </label>
            </td>

            <td>
                <button name="action" value="update" class="btn btn-primary">
                    Confirm
                </button>
            </td>
            <td>
                <button name="action" value="skip" class="btn btn-danger">
                    Skip all
                </button>
            </td>
        </tr>
    </table>
</form>

<hr>

<p align="center">
    <a href="admin" class="btn btn-primary">Back to admin page</a>
</p>

<hr>

</body>
</html>
