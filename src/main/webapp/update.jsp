<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="item" scope="request" type="entities.FlightEntity"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        tr:nth-child(even){background-color: #f2f2f2}
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
            <input type="hidden" name="id" value="${item.id}">
            <td>
                <label>
                    <input type="text" name="flightNumber" value="${item.flightNumber}">
                </label>
            </td>

            <td style="color:${item.directionType == 1 ? 'green' : 'red'}">
                <label>
                    <select name="type">
                        <option value="${item.directionType}">${item.directionType == 1 ? 'Arriving' : 'Leaving'}</option>
                        <option value="${item.directionType == 1 ? 0 : 1}">${item.directionType == 1 ? 'Leaving' : 'Arriving'}</option>
                    </select>
                </label>
            </td>

            <td><b><label>
                <input type="text" name="leavingFrom" value="${item.leavingFrom}">
            </label></b></td>
            <td><b><label>
                <input type="time" name="leavingTime" value="${item.leavingTime}">
            </label></b></td>

            <td><b><label>
                <input type="text" name="arrivalTo" value="${item.arrivalTo}">
            </label></b></td>
            <td><label>
                <input type="time" name="arrivalTime" value="${item.arrivalTime}">
            </label></td>
            <td>
                <button type="submit" name="action" value="update" class="btn btn-primary">
                    Update
                </button>
            </td>
            <td>
                <button type="submit" name="action" value="skip" class="btn btn-danger">
                    Skip all
                </button>
            </td>
        </tr>
    </table>
</form>

<p align="center"><a href="info" class="btn btn-primary">Back to info</a></p>


</body>
</html>
