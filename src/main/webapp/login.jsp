<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Airport Login</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>"/>
</head>
<body>
<h1 align="center">Authorization required</h1>
<hr>
<form action="login" method="post">
    <p align="center">
        <b>Username: </b>
        <label>
            <input name="login">
        </label>
        <br>
        <b>Password: </b>
        <label>
            <input type="password" name="password">
        </label>
    </p>
    <p align="center">
        <button name="action" value="login" class="btn btn-primary">
            Login
        </button>
        <button name="action" value="skip" class="btn btn-danger">
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
