<%--
  Created by IntelliJ IDEA.
  User: likun
  Date: 27/4/2024
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Veloce Rentals</title>
    <link href="estilo.css" ref="stylesheet"/>
</head>
<body>
    <div id="contenedor1">
        <form id="form1" action="loginSession" method="post">
            <input type="text" name="" placeholder="username"/>
            <hr>
            <input type="password" name="" placeholder="password"/>
            <hr>
            <input type="submit" value="Sign in"/>
        </form>
    </div>
    <div id="contenedor2">
        <form id="form2">
            <input type="submit" value="Create Account"/>
        </form>
        <div id="referencias">
            <a href="">Term of Use</a>
            <a href="">Privacy Policy</a>
        </div>
    </div>
</body>


</html>