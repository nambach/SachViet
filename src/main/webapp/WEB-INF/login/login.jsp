<%--
  Created by IntelliJ IDEA.
  User: NAMBACH
  Date: 10/2/2018
  Time: 7:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet"/>
    <style>
        .hidden {
            display: none;
        }
    </style>
</head>
<body>
<form name="login-form">
    Username <input type="text" name="username"> <br/>
    Password <input type="password" name="password"> <br/>
    <span class="loginMessage hidden" style="color: red">Invalid username or password</span> <br/>
    <input type="button" value="Login" name="btnLogin">
    <input type="reset" value="Reset">
</form>
</body>
<script src="${pageContext.request.contextPath}/resources/js/constant.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/login/login.js"></script>
<script>
    ready(() => {
        pageContext = "${pageContext.request.contextPath}";
        loginView.init();
        loginView.bindElements();
    });
</script>
</html>
