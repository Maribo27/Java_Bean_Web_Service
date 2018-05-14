<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ws" uri="wsTag" %>

<html>
<head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body>
        <details>
                <summary><ws:entity-name type="method"/></summary>
                <ul>
                        <c:forEach items="${requestScope.methods}" var="method">
                                <li><a href="${pageContext.request.contextPath}/java_beans?command=GET_METHOD&id=${method.id}">${method.name}</a></li>
                        </c:forEach>
                        <li style="list-style: none;"><a href="${pageContext.request.contextPath}/java_beans?command=MODIFY&action=ADD_METHOD&typeOld=method">+ Добавить</a></li>
                </ul>
        </details>
        <details>
                <summary><ws:entity-name type="property"/></summary>
                <ul>
                        <c:forEach items="${requestScope.property}" var="property">
                                <li><a href="${pageContext.request.contextPath}/java_beans?command=GET_ENTITY&id=${property.id}&type=property">${property.name}</a></li>
                        </c:forEach>
                        <li style="list-style: none;"><a href="${pageContext.request.contextPath}/java_beans?command=MODIFY&action=ADD_ENTITY&typeOld=property">+ Добавить</a></li>
                </ul>
        </details>
        <details>
                <summary><ws:entity-name type="rule"/></summary>
                <ul>
                        <c:forEach items="${requestScope.rule}" var="rule">
                                <li><a href="${pageContext.request.contextPath}/java_beans?command=GET_ENTITY&id=${rule.id}&type=rule">${rule.name}</a></li>
                        </c:forEach>
                        <li style="list-style: none;"><a href="${pageContext.request.contextPath}/java_beans?command=MODIFY&action=ADD_ENTITY&typeOld=rule">+ Добавить</a></li>
                </ul>
        </details>
</body>
</html>