<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ws" uri="wsTag" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/input_form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/icon.ico">

    <title>Java Beans Handler</title>
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
        <c:choose>
            <c:when test="${requestScope.method != null}">
                <h1>${requestScope.method.name}</h1>
                <ws:description description="${requestScope.method.description}"/><br>
                <b>Наличие:</b> ${requestScope.method.necessity}
                <hr>
                <form action="${pageContext.request.contextPath}/java_beans" method="post" style="display: inline;padding-right: 5px;">
                    <input type="hidden" name="idOld" value="${requestScope.method.id}"/>
                    <input type="hidden" name="nameOld" value="${requestScope.method.name}"/>
                    <input type="hidden" name="descriptionOld" value="${requestScope.method.description}"/>
                    <input type="hidden" name="necessityOld" value="${requestScope.method.necessity}"/>
                    <input type="hidden" name="typeOld" value="method"/>
                    <input type="hidden" name="action" value="MODIFY_METHOD"/>
                    <input type="hidden" name="command" value="MODIFY"/>
                    <input type="submit" value="Изменить"/>
                </form>
                <form action="${pageContext.request.contextPath}/java_beans" method="post" style="display: inline;padding-left: 5px;">
                    <input type="hidden" name="command" value="DELETE_METHOD"/>
                    <input type="hidden" name="id" value="${requestScope.method.id}"/>
                    <input type="submit" value="Удалить"/>
                </form>
            </c:when>
            <c:when test="${requestScope.entity != null}">
                <h1>${requestScope.entity.name}</h1>
                <ws:description description="${requestScope.entity.description}"/>
                <hr>
                <form action="${pageContext.request.contextPath}/java_beans" method="post">
                    <input type="hidden" name="command" value="MODIFY"/>
                    <input type="hidden" name="action" value="MODIFY_ENTITY"/>
                    <input type="hidden" name="idOld" value="${requestScope.entity.id}"/>
                    <input type="hidden" name="nameOld" value="${requestScope.entity.name}"/>
                    <input type="hidden" name="descriptionOld" value="${requestScope.entity.description}"/>
                    <input type="hidden" name="typeOld" value="${requestScope.entity.type}"/>
                    <input type="submit" value="Изменить"/>
                </form>
                <c:set var="typeOne" value="${requestScope.entity.type.name()}"/>
                <c:set var="typeTwo" value="TYPE"/>
                <c:choose>
                    <c:when test="${typeOne.toString() eq typeTwo.toString()}">

                    </c:when>
                    <c:otherwise>
                        <form action="${pageContext.request.contextPath}/java_beans" method="post">
                            <input type="hidden" name="command" value="DELETE_ENTITY"/>
                            <input type="hidden" name="id" value="${requestScope.entity.id}"/>
                            <input type="hidden" name="type" value="${requestScope.entity.type}"/>
                            <input type="submit" value="Удалить"/>
                        </form>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
