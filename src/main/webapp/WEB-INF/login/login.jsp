<%--
  Created by IntelliJ IDEA.
  User: NAMBACH
  Date: 10/2/2018
  Time: 7:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css"/>
</head>
<body>
<div class="loader hidden"><div></div></div>
<div class="form box-shadow">
    <div class="title center"><h3>Hệ thống quản trị Sách Việt</h3></div>
    <div class="input center">
        <input type="text" name="username" placeholder="Tên đăng nhập"/>
        <input type="password" name="password" placeholder="Mật khẩu"/>
        <button name="btnLogin">Đăng nhập</button>
    </div>
</div>
<div class="loginMessage hidden">Tài khoản hoặc mật khẩu không hợp lệ</div>
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
