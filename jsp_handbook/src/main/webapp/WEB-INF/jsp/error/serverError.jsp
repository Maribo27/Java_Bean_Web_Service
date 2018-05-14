<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/error.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/icon.ico">
    <title>Java Beans Handbook | 500</title>
</head>

<body>
<div class="error">
    <p class="code">500</p>
    <p class="message">Внутренняя ошибка.</p>
    <a href="${pageContext.request.contextPath}/index.jsp">На главную страницу</a>
</div>
</body>
</html>
