    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib prefix="ws" uri="wsTag" %>

        <html>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

        </head>
        <body>
        <details>
        <summary><ws:entity-name type="ejb_info"/></summary>
        <ul>
        <c:forEach items="${requestScope.ejb_info}" var="ejb_info">
            <li><a href="${pageContext.request.contextPath}/java_beans?command=GET_ENTITY&id=${ejb_info.id}
            &type=ejb_info">${ejb_info.name}</a></li>
        </c:forEach>
        <li style="list-style: none;"><a href="${pageContext.request.contextPath}
        /java_beans?command=MODIFY&action=ADD_ENTITY&typeOld=ejb_info">+ Добавить</a></li>
        </ul>
        </details>
        <details>
        <summary><ws:entity-name type="ejb_type"/></summary>
        <ul>
        <c:forEach items="${requestScope.ejb_type}" var="ejb_type">
            <li><a href="${pageContext.request.contextPath}/java_beans?command=GET_ENTITY&id=${ejb_type.id}
            &type=ejb_type">${ejb_type.name}</a></li>
        </c:forEach>
        <li style="list-style: none;"><a href="${pageContext.request.contextPath}
        /java_beans?command=MODIFY&action=ADD_ENTITY&typeOld=ejb_type">+ Добавить</a></li>
        </ul>
        </details>
        <details>
        <summary><ws:entity-name type="role"/></summary>
        <ul>
        <c:forEach items="${requestScope.role}" var="role">
            <li><a href="${pageContext.request.contextPath}/java_beans?command=GET_ENTITY&id=${role.id}
            &type=role">${role.name}</a></li>
        </c:forEach>
        <li style="list-style: none;"><a href="${pageContext.request.contextPath}
        /java_beans?command=MODIFY&action=ADD_ENTITY&typeOld=role">+ Добавить</a></li>
        </ul>
        </details>
        </body>
        </html>