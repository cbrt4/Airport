<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<hr>
<%------------------------------------<<    T A B L E    >>-------------------------------------------------%>
<form action="add" method="post">
    <table>
        <thead align="center">
        <tr>
            <th>Flight Number</th>
            <th>Direction</th>

            <th>Waypoint</th>
            <th>Time</th>

            <th>Terminal</th>
            <th>Gate</th>

            <th>Board ID</th>

            <th>Confirm</th>
            <th>Skip all</th>
        </tr>
        </thead>
        <%------------------------------------<<    R O W    >>-------------------------------------------------%>
        <tr>
            <td><label>
                <input size="10" name="flightNumber" value="">
            </label></td>

            <td>
                <label>
                    <select name="directionType">
                        <option value="1">Arrive</option>
                        <option value="0">Leave</option>
                    </select>
                </label>
            </td>

            <td><b><label>
                <input size="10" name="waypoint" value="">
            </label></b></td>
            <td><b><label>
                <input type="time" name="time" value="">
            </label></b></td>

            <td><b><label>
                <input size="1" name="terminal" value="">
            </label></b></td>
            <td><label>
                <input type="number" name="gate" value="">
            </label></td>
            <td><label>
                <input type="number" name="boardId" value="">
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

<hr>

<p align="center"><a href="info" class="btn btn-primary">Back to info</a></p>

</body>
</html>
