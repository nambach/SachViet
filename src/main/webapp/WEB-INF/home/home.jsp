<%--
  Created by IntelliJ IDEA.
  User: NAMBACH
  Date: 10/2/2018
  Time: 9:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<%
    if(session.getAttribute("username") == null || session.getAttribute("username").toString().trim().equals("")) {
        response.sendRedirect("/");
    }
%>
<h1>Welcome, ${sessionScope.username}</h1>
${sessionScope.number}
</body>
<script src="${pageContext.request.contextPath}/resources/js/constant.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script>
    ready(() => {
        pageContext = "${pageContext.request.contextPath}";
        callAjax("/books/search", GET, { searchValue: "5" }, true, (data) => { console.log(JSON.parse(data))});
    });
</script>
</html>
