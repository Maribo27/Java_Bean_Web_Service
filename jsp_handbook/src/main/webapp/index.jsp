<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ws" uri="wsTag" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <c:import url="/WEB-INF/jsp/style.jsp"/>

    <title>Java Beans Handler</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div itemscope itemtype="http://schema.org/WebPage" class="error">
    <p itemtype="Text" class="message">Добро пожаловать. Это справочник по JavaBeans.</p>
    <a itemtype="url" href="${pageContext.request.contextPath}/java_beans?command=GET_ENTITY&id=1&type=type">Что это такое?</a>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>