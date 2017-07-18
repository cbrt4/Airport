<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Add new flight</title>
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
<h1 align="center">Add new flight</h1>
<%------------------------------------<<    T A B L E    >>-------------------------------------------------%>
<form action="add" method="post">
    <table>
        <thead align="center">
        <tr>
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
            <td><label>
                <input name="flightNumber" value="">
            </label></td>

            <td>
                <label>
                    <select name="type">
                        <option value="1">Arriving</option>
                        <option value="0">Leaving</option>
                    </select>
                </label>
            </td>

            <td><b><label>
                <input name="leavingFrom" value="">
            </label></b></td>
            <td><b><label>
                <input type="time" name="leavingTime" value="">
            </label></b></td>

            <td><b><label>
                <input name="arrivalTo">
            </label></b></td>
            <td><label>
                <input type="time" name="arrivalTime">
            </label></td>
            <td>
                <button name="action" value="add" class="btn btn-primary">
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

<p align="center"><a href="info" class="btn btn-primary">Back to info</a></p>


</body>
</html>
