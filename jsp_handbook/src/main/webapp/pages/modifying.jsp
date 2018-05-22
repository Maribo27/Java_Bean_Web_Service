<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ws" uri="wsTag" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <c:import url="/WEB-INF/jsp/style.jsp"/>

    <title>Java Beans Handler | Update</title>
</head>
<body>
<c:if test = "${empty sessionScope.user}">
    <jsp:forward page="${pageContext.request.contextPath}" />
</c:if>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
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
    <h1 itemprop="Text">${requestScope.nameOld}</h1>
    <form itemtype="http://schema.org/ItemPageForm" action="${pageContext.request.contextPath}/java_beans" method="post">
        <input type="hidden" name="command" value="${requestScope.action}"/>
        <label itemprop="Text">
            Имя:
            <input itemprop="Text" type="text" name="name" value="${requestScope.nameOld}" required>
        </label>
        <label itemprop="Text">
            Описание:
            <textarea itemprop="Text" name="description" required>${requestScope.descriptionOld}</textarea>
        </label>
        <input itemprop="Text" type="hidden" name="id" value="${requestScope.idOld}"/>
        <c:choose>
            <c:when test="${requestScope.typeOld.toString() eq 'method'}">
                <div itemtype="http://schema.org/PageSelection" class="selection-form">
                    <label itemprop="Text">Наличие:
                        <select name="necessity" class="chosen-select" tabindex="1">
                            <option itemprop="Text" value="NECESSARILY">обязателен</option>
                            <option itemprop="Text" value="FIELD">если присутствует соответствующее поле</option>
                            <option itemprop="Text" value="INDEX">если присутствует соответствующее индексированное поле</option>
                        </select>
                    </label>
                </div>
            </c:when>
            <c:otherwise>
                <input itemprop="Text" type="hidden" name="type" value="${requestScope.typeOld}"/>
            </c:otherwise>
        </c:choose>
        <hr>
        <input itemprop="url" type="submit" value="Подтвердить"/>
    </form>
</div>
<script src="${pageContext.request.contextPath}/assets/js/support/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/support/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/support/init.js" type="text/javascript"
        charset="utf-8"></script>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
