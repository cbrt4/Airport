<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="flight" scope="request" type="entities.FlightEntity"/>
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
<%------------------------------------<<    T A B L E    >>-------------------------------------------------%>
<form action="update" method="post">
    <table>
        <thead align="center">
        <tr style="background-color: aquamarine">
            <th>Flight Number</th>
            <th>Direction</th>

            <th>From</th>
            <th>Leaving time</th>

            <th>To</th>
            <th>Arrival time</th>

            <th></th>
            <th></th>
        </tr>
        </thead>
        <%------------------------------------<<    R O W    >>-------------------------------------------------%>
        <tr>
            <input type="hidden" name="id" value="${flight.id}">
            <td>
                <label>
                    <input name="flightNumber" value="${flight.flightNumber}">
                </label>
            </td>

            <td>
                <label>
                    <select name="type">
                        <option value="${flight.directionType}" style="color: ${flight.directionType == 1 ? '#337ab7' : '#d9534f'}">
                            ${flight.directionType == 1 ? 'Arriving' : 'Leaving'}
                        </option>
                        <option value="${flight.directionType == 1 ? 0 : 1}" style="color: ${flight.directionType == 1 ? '#d9534f' : '#337ab7'}">
                            ${flight.directionType == 1 ? 'Leaving' : 'Arriving'}
                        </option>
                    </select>
                </label>
            </td>

            <td><b><label>
                <input name="leavingFrom" value="${flight.leavingFrom}">
            </label></b></td>
            <td><b><label>
                <input type="time" name="leavingTime" value="${flight.leavingTime}">
            </label></b></td>

            <td><b><label>
                <input name="arrivalTo" value="${flight.arrivalTo}">
            </label></b></td>
            <td><label>
                <input type="time" name="arrivalTime" value="${flight.arrivalTime}">
            </label></td>
            <td>
                <button name="action" value="update" class="btn btn-primary">
                    Update
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

<p align="center"><a href="info" class="btn btn-primary">Back to info</a></p>


</body>
</html>
