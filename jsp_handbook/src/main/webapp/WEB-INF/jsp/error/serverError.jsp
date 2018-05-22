<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <c:import url="/WEB-INF/jsp/style.jsp"/>
    <title>Java Beans Handbook | 503</title>
</head>

<body>
<jsp:useBean id="error" class="by.maribo.java_beans_handbook.structure.bean.Error"/>
<jsp:setProperty name="error" property="code" value="503"/>
<jsp:setProperty name="error" property="description" value="Внутренняя ошибка сервера"/>
<div class="error">
    <p class="code">
        <jsp:getProperty name="error" property="code"/>
    </p>
    <p class="message">
        <jsp:getProperty name="error" property="description"/>
    </p>
    <a href="${pageContext.request.contextPath}/index.jsp">На главную страницу</a>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
