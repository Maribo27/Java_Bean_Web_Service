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
<div itemscope itemtype="http://schema.org/ItemList" class="sidebar-menu">
    <c:forEach items="${requestScope.type}" var="type">
        <h2>
            <a itemprop="url" href="${pageContext.request.contextPath}/java_beans?command=GET_ENTITY&id=${type.id}&type=type">${type.name}</a>
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

<div itemscope itemtype="http://schema.org/ItemPage" class="data-form">
    <c:choose>
        <c:when test="${requestScope.method != null}">
            <h1 itemprop="Text">${requestScope.method.name}</h1>
            <ws:description description="${requestScope.method.description}"/><br>
            <b itemprop="Text">Наличие:</b> ${requestScope.method.necessity}
            <hr>
            <form itemtype="http://schema.org/ItemPageForm" action="${pageContext.request.contextPath}/java_beans" method="post" style="display: inline;padding-right: 5px;">
                <input itemprop="Text" type="hidden" name="idOld" value="${requestScope.method.id}"/>
                <input itemprop="Text" type="hidden" name="nameOld" value="${requestScope.method.name}"/>
                <input itemprop="Text" type="hidden" name="descriptionOld" value="${requestScope.method.description}"/>
                <input itemprop="Text" type="hidden" name="necessityOld" value="${requestScope.method.necessity}"/>
                <input itemprop="Text" type="hidden" name="typeOld" value="method"/>
                <input itemprop="Text" type="hidden" name="action" value="MODIFY_METHOD"/>
                <input itemprop="Text" type="hidden" name="command" value="MODIFY"/>
                <input itemprop="url" type="submit" value="Изменить"/>
            </form>
            <form itemtype="http://schema.org/ItemPageForm" action="${pageContext.request.contextPath}/java_beans" method="post" style="display: inline;padding-left: 5px;">
                <input itemprop="Text" type="hidden" name="command" value="DELETE_METHOD"/>
                <input itemprop="Text" type="hidden" name="id" value="${requestScope.method.id}"/>
                <input itemprop="url" type="submit" value="Удалить"/>
            </form>
        </c:when>
        <c:when test="${requestScope.entity != null}">
            <h1 itemprop="Text">${requestScope.entity.name}</h1>
            <ws:description description="${requestScope.entity.description}"/>
            <hr>
            <form itemtype="http://schema.org/ItemPageForm" action="${pageContext.request.contextPath}/java_beans" method="post">
                <input itemprop="Text" type="hidden" name="command" value="MODIFY"/>
                <input itemprop="Text" type="hidden" name="action" value="MODIFY_ENTITY"/>
                <input itemprop="Text" type="hidden" name="idOld" value="${requestScope.entity.id}"/>
                <input itemprop="Text" type="hidden" name="nameOld" value="${requestScope.entity.name}"/>
                <input itemprop="Text" type="hidden" name="descriptionOld" value="${requestScope.entity.description}"/>
                <input itemprop="Text" type="hidden" name="typeOld" value="${requestScope.entity.type}"/>
                <input itemprop="url" type="submit" value="Изменить"/>
            </form>
            <c:set var="typeOne" value="${requestScope.entity.type.name()}"/>
            <c:set var="typeTwo" value="TYPE"/>
            <c:choose>
                <c:when test="${typeOne.toString() eq typeTwo.toString()}">
                </c:when>
                <c:otherwise>
                    <form itemtype="http://schema.org/ItemPageForm" action="${pageContext.request.contextPath}/java_beans" method="post">
                        <input itemprop="Text" type="hidden" name="command" value="DELETE_ENTITY"/>
                        <input itemprop="Text" type="hidden" name="id" value="${requestScope.entity.id}"/>
                        <input itemprop="Text" type="hidden" name="type" value="${requestScope.entity.type}"/>
                        <input itemprop="url" type="submit" value="Удалить"/>
                    </form>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
