<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Airport Login</title>
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
    </style>
</head>
<body>
<h1 align="center">Authorization required</h1>
<hr>
<form action="login" method="post">
    <table>
        <tr>
            <td><b>Username: </b></td>
            <td>
            <label>
                <input name="login">
            </label>
        </td>
        </tr>
        <tr>
            <td><b>Password: </b></td>
            <td>
                <label>
                    <input type="password" name="password">
                </label>
            </td>
        </tr>
    </table>
    <p align="center">
        <button name="action" value="login" class="btn btn-primary" style="width: 50pt">
            Login
        </button>

        <button name="action" value="skip" class="btn btn-danger" style="width: 50pt">
            Skip
        </button>
    </p>
</form>
<hr>
<p align="center">
    <a href="info" class="btn btn-default">Back to info</a>
</p>
<hr>
</body>
</html>
