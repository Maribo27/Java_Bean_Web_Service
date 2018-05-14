<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ws" uri="wsTag" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/input_form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/chosen.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/icon.ico">

    <title>Java Beans Handler | Update</title>
</head>
<body>
<div class="sidebar-menu">
    <c:forEach items="${requestScope.type}" var="type">
        <h2>
            <a href="${pageContext.request.contextPath}/java_beans?command=GET_ENTITY&id=${type.id}&type=type">${type.name}</a>
        </h2>
        <c:choose>
            <c:when test="${type.id eq 1}">
                <jsp:include page="/WEB-INF/jsp/sidebar_menu/JB.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="/WEB-INF/jsp/sidebar_menu/EJB.jsp"/>
            </c:otherwise>
        </c:choose>
        <hr>
    </c:forEach>
</div>
<div class="information">
    <div class="data-form">
        <h1>${requestScope.nameOld}</h1>
        <form action="${pageContext.request.contextPath}/java_beans" method="post">
            <input type="hidden" name="command" value="${requestScope.action}"/>
            <label>
                Имя:
                <input type="text" name="name" value="${requestScope.nameOld}" required>
            </label>
            <label>
                Описание:
                <textarea name="description" required>${requestScope.descriptionOld}</textarea>
            </label>
            <input type="hidden" name="id" value="${requestScope.idOld}"/>
            <c:choose>
                <c:when test="${requestScope.typeOld.toString() eq 'method'}">
                    <div class="selection-form">
                        <label>Наличие:
                            <select name="necessity" class="chosen-select" tabindex="1">
                                <option value="NECESSARILY">обязателен</option>
                                <option value="FIELD">если присутствует соответствующее поле</option>
                                <option value="INDEX">если присутствует соответствующее индексированное поле</option>
                            </select>
                        </label>
                    </div>
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="type" value="${requestScope.typeOld}"/>
                </c:otherwise>
            </c:choose>
            <hr>
            <input type="submit" value="Подтвердить"/>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/assets/js/support/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/support/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/support/init.js" type="text/javascript" charset="utf-8"></script>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
