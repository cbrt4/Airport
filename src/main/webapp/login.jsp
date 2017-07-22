<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Airport Login</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>"/>
</head>
<body>
<h1 align="center">Provide following details:</h1>
<form action="login" method="post">
<div align="center">
    <br>
    <b>Username: </b>
    <label>
        <input name="login">
    </label>
    <br>
    <b>Password: </b>
    <label>
        <input type="password" name="password">
    </label>
    <br>
    <button name="action" value="login" class="btn btn-primary">
        Login
    </button>
    <button name="action" value="skip" class="btn btn-danger">
        Skip
    </button>
</div>
</form>
</body>
</html>
