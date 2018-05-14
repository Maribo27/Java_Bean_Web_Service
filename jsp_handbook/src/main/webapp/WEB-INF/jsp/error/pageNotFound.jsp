<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <c:import url="/WEB-INF/jsp/style.jsp"/>
    <title>Java Beans Handbook | 404</title>
</head>

<body>
<div class="error">
    <p class="code">404</p>
    <p class="message">Страница не найдена.</p>
    <a href="${pageContext.request.contextPath}/index.jsp">На главную страницу</a>
</div>
</body>
</html>