<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ws" uri="wsTag" %>

<details itemscope itemtype="http://schema.org/ItemList">
    <summary itemtype="Text"><ws:entity-name type="ejb_info"/></summary>
    <ul>
        <c:forEach items="${requestScope.ejb_info}" var="ejb_info">
            <li>
                <a itemprop="url" href="${pageContext.request.contextPath}/java_beans?command=GET_ENTITY&id=${ejb_info.id}&type=ejb_info">${ejb_info.name}</a>
            </li>
        </c:forEach>
        <li style="list-style: none;"><a itemprop="url" href="${pageContext.request.contextPath}/java_beans?command=MODIFY&action=ADD_ENTITY&typeOld=ejb_info">+ Добавить</a></li>
    </ul>
</details>
<details itemscope itemtype="http://schema.org/ItemList">
    <summary itemtype="Text"><ws:entity-name type="ejb_type"/></summary>
    <ul>
        <c:forEach items="${requestScope.ejb_type}" var="ejb_type">
            <li>
                <a itemprop="url" href="${pageContext.request.contextPath}/java_beans?command=GET_ENTITY&id=${ejb_type.id}&type=ejb_type">${ejb_type.name}</a>
            </li>
        </c:forEach>
        <li style="list-style: none;"><a itemprop="url" href="${pageContext.request.contextPath}/java_beans?command=MODIFY&action=ADD_ENTITY&typeOld=ejb_type">+ Добавить</a></li>
    </ul>
</details>
<details itemscope itemtype="http://schema.org/ItemList">
    <summary itemtype="Text"><ws:entity-name type="role"/></summary>
    <ul>
        <c:forEach items="${requestScope.role}" var="role">
            <li>
                <a itemprop="url" href="${pageContext.request.contextPath}/java_beans?command=GET_ENTITY&id=${role.id}&type=role">${role.name}</a>
            </li>
        </c:forEach>
        <li style="list-style: none;"><a itemprop="url" href="${pageContext.request.contextPath}/java_beans?command=MODIFY&action=ADD_ENTITY&typeOld=role">+ Добавить</a></li>
    </ul>
</details>