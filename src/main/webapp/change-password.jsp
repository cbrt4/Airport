<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Airport Password Change</title>
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
<h1 align="center">Provide following details</h1>
<hr>
<form action="change-password" method="post">
    <table>
        <tr align="center">
            <td><b>Old username: </b></td>
            <td>
                <label>
                    <input name="oldLogin" value="">
                </label>
            </td>
        </tr>
        <tr>
            <td><b>Old password: </b></td>
            <td>
                <label>
                    <input type="password" name="oldPassword" value="">
                </label>
            </td>
        </tr>
        <tr>
            <td><b>New username: </b></td>
            <td>
                <label>
                    <input name="newLogin" value="">
                </label>
            </td>
        </tr>
        <tr>
            <td><b>New password: </b></td>
            <td>
                <label>
                    <input type="password" name="newPassword" value="">
                </label>
            </td>
        </tr>
        <tr>
            <td><b>Confirm password: </b></td>
            <td>
                <label>
                    <input type="password" name="newPasswordConfirm" value="">
                </label>
            </td>
        </tr>
    </table>
    <p align="center">
        <button name="action" value="change" class="btn btn-primary" style="width: 60pt">
            Change
        </button>
        <button name="action" value="skip" class="btn btn-danger" style="width: 60pt">
            Skip
        </button>
    </p>
</form>
<hr>
<p align="center">
    <a href="admin" class="btn btn-default">Back to admin</a>
</p>
<hr>
</body>
</html>
